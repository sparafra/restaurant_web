package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.Restaurant;
import service.RestaurantService;



public class AllLocals extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				RestaurantService restaurant_service = new RestaurantService();
				//Restaurant x = restaurant_service.findById(Long.valueOf(1));
				List<Restaurant> restaurants = restaurant_service.findAll();
				
				JSONArray jArray = new JSONArray();
				
				//jArray.put(x.getJson());
				
				for(Restaurant R: restaurants)
				{
					jArray.put(R.getJson());
				}
				
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(jArray.toString());
		
		
	}
}
