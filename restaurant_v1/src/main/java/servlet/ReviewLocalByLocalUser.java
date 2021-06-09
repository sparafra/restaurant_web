package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;


import model.Restaurant;
import model.Review;
import model.ReviewRestaurant;
import model.User;
import model.Error;

import service.RestaurantService;
import service.UserService;



public class ReviewLocalByLocalUser extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	

				
				
				JSONObject obj = new JSONObject();
				
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				
				HttpSession session = req.getSession(false);
				if(session != null)
				{
					User user = (User)session.getAttribute("UserLogged");
					Restaurant Rest = (Restaurant)session.getAttribute("Restaurant");

					
					if(Rest != null && user != null)
					{
						RestaurantService restaurant_service = new RestaurantService();
						UserService user_service = new UserService();
						
						Restaurant restaurant_session = restaurant_service.findById(Rest.getId());
						User user_session = user_service.findById(user.getTelephone());
						
						Set<ReviewRestaurant> reviews = restaurant_session.getListReviewRestaurant();
						if(reviews != null)
						{
							reviews.retainAll(user_session.getListReviewRestaurant());
						
							if(reviews.size() > 0)
							{
								for(Review R: reviews)
								{
									resp.getWriter().write(R.getJson().toString());
								}
							}
							else
								resp.getWriter().write(Error.NOT_FOUNDED.toString());
						}
						else
							resp.getWriter().write(Error.GENERIC_ERROR.toString());

					}
				}
				else
					resp.getWriter().write(Error.BLANK_SESSION.toString());	
		
	}
}
