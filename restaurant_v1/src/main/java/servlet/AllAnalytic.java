package servlet;

import java.io.IOException;

import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;


import model.Analytic;
import model.Restaurant;
import service.RestaurantService;
import model.Error;


public class AllAnalytic extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				HttpSession session = req.getSession(false);
				
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				
				if(session != null)
				{
					Restaurant Rest = (Restaurant)session.getAttribute("Restaurant");
					
					if(Rest != null)
					{
						RestaurantService restaurant_service = new RestaurantService();
						Restaurant restaurant_session = restaurant_service.findById(Rest.getId());
						
						Set<Analytic> analytics = restaurant_session.getListAnalytics();
						
						JSONArray jArray = new JSONArray();
						
						for(Analytic a: analytics)
						{
							jArray.put(a.getJson());
						}
						
						resp.getWriter().write(jArray.toString());							
					}
					else
					{
						resp.getWriter().write(Error.GENERIC_ERROR.toString());	
					}
				}
				else
				{
					resp.getWriter().write(Error.BLANK_SESSION.toString());	
				}
	}
}
