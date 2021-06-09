package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import model.User;
import service.RestaurantService;
import service.UserService;
import model.Error;



public class AllUsers extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				
				UserService user_service = new UserService();
				RestaurantService restaurant_service = new RestaurantService();
				
				HttpSession session = req.getSession(false);
				if(session != null)
				{
					Restaurant Rest = (Restaurant)session.getAttribute("Restaurant");
					User user = (User)session.getAttribute("UserLogged");
				
				
					if(Rest != null)
					{
						Restaurant restaurant_session = restaurant_service.findById(Rest.getId());
						User user_session = user_service.findById(user.getTelephone());
						
						Set<User> users = restaurant_session.getListUsers();
						
						JSONArray jArray = new JSONArray();
						
						users.remove(user_session);
						for(User u: users)
						{
							jArray.put(u.getJson());
						}
						
						resp.getWriter().write(jArray.toString());					
			
					}
					else
					{
						resp.getWriter().write(Error.GENERIC_ERROR.toString());	
					}
				}
				else
					resp.getWriter().write(Error.BLANK_SESSION.toString());	


	}
}
