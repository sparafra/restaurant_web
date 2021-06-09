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

import model.Product;
import model.Restaurant;
import service.RestaurantService;
import model.Error;


public class AllProducts extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				
				
				HttpSession session = req.getSession(false);
				if(session != null)
				{
					Restaurant Rest = (Restaurant)session.getAttribute("Restaurant");
					
					
					
					if(Rest != null)
					{
						System.out.println("PRODUCT: " + Rest.getId());
						RestaurantService restaurant_service = new RestaurantService();
						Restaurant restaurant_session = restaurant_service.findById(Rest.getId());

						Set<Product> products = restaurant_session.getListProducts();
						
						JSONArray jArray = new JSONArray();
						
						if(products != null)
						{
							for(Product p: products)
							{
								jArray.put(p.getJson());
							}
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
