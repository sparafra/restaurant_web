package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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

import model.Error;
import model.Ingredient;
import model.Product;
import model.Restaurant;
import service.RestaurantService;



public class AllIngredients extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				
				HttpSession session = req.getSession(false);
				if(session != null)
				{
					Restaurant Rest = (Restaurant)session.getAttribute("Restaurant");
					RestaurantService restaurant_service = new RestaurantService();
					
				
					if(Rest != null)
					{
						Restaurant restaurant_session = restaurant_service.findById(Rest.getId());
						Set<Product> products = restaurant_session.getListProducts();
						
						Set<Ingredient> ingredients = new HashSet<Ingredient>();
						
						for(Product p: products)
						{
							Set<Ingredient> ingredient_product = p.getListIngredients();
							ingredient_product.removeAll(ingredients);
							ingredients.addAll(ingredient_product);
						}
						
						JSONArray jArray = new JSONArray();

						for(Ingredient i: ingredients)
						{
							jArray.put(i.getJson());
						}
						
						resp.getWriter().write(jArray.toString());					
			
					}
					else
					{
						resp.getWriter().write(Error.GENERIC_ERROR.toString());	
					}
				}
				resp.getWriter().write(Error.BLANK_SESSION.toString());	
			
		
	}
}
