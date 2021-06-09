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
import model.Type;
import model.Error;
import service.RestaurantService;
import service.TypeService;



public class ProductsByType extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				//Long type_id = Long.valueOf(req.getParameter("Type"));
				String type_id = req.getParameter("Type");

				Restaurant Rest = null;
				
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				
				HttpSession session = req.getSession(false);
				if(session != null)
				{
					Rest = (Restaurant)session.getAttribute("Restaurant");
					RestaurantService restaurant_service = new RestaurantService();
					Restaurant restaurant_session = restaurant_service.findById(Rest.getId());
					
					TypeService type_service = new TypeService();
					Type type = type_service.findByName(type_id);
					
					if(Rest != null)
					{
						Set<Product> products = restaurant_session.getListProducts();
						products.retainAll(type.getListProducts());

						JSONArray jArray = new JSONArray();
						
						for(Product p: products)
						{
							jArray.put(p.getJson());
						}
						
						resp.getWriter().write(jArray.toString());

						/*
						for(int k=0; k<products.size(); k++)
						{
							JSONObject obj = new JSONObject();
							try
							{
								obj.put("id", products.get(k).getId());
								obj.put("Name", products.get(k).getNome());
								obj.put("Price", products.get(k).getPrezzo());
								obj.put("Type", products.get(k).getTipo());
								obj.put("ImageURL", products.get(k).getImageURL());
								obj.put("Quantity", products.get(k).getQuantita());
								
								JSONArray jArrayI = new JSONArray();
								for(int i=0; i<products.get(k).getListIngredienti().size(); i++)
								{
									JSONObject objI = new JSONObject();
									try
									{
										objI.put("id", products.get(k).getListIngredienti().get(i).getId());
										objI.put("Name", products.get(k).getListIngredienti().get(i).getNome());
										objI.put("Price", products.get(k).getListIngredienti().get(i).getPrezzo());
										jArrayI.put(objI);
										
									}catch(Exception e) {e.printStackTrace();}
								}
								obj.put("Ingredients", jArrayI);
								
								JSONArray jArrayR = new JSONArray();
								for(int i=0; i<products.get(k).getListReview().size(); i++)
								{
									JSONObject objR = new JSONObject();
									try
									{
										objR.put("idProdotto", products.get(k).getListReview().get(i).getIdProduct());
										objR.put("NumeroTelefono", products.get(k).getListReview().get(i).getNumeroTelefono());
										objR.put("Voto", products.get(k).getListReview().get(i).getVoto());
										objR.put("DataOra", products.get(k).getListReview().get(i).getDataOra());
										jArrayR.put(objR);
										
									}catch(Exception e) {e.printStackTrace();}
								}
								obj.put("Reviews", jArrayR);
								jArray.put(obj);
							}catch(Exception e) {e.printStackTrace();}
						}
						*/
						
					}
					else
					{
						resp.getWriter().write(Error.GENERIC_ERROR.toString());
					}
					
				}
				
				resp.getWriter().write(Error.BLANK_SESSION.toString());
	
	}
}
