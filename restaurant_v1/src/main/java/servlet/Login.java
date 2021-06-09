package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import model.Restaurant;
import model.User;
import service.RestaurantService;
import service.UserService;
import utils.PasswordUtil;
import model.Error;
import model.Order;
import model.Product;
import model.ProductOrder;


public class Login extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				String Mail = req.getParameter("Mail");
				String Password = req.getParameter("Password");
				
				Long Local = null;
				try {
					Local = Long.valueOf(req.getParameter("idLocal"));
				}
				catch(Exception e) {}
				
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				
				HttpSession session = req.getSession(false);

				if(Local != null)
				{

					RestaurantService restaurant_service = new RestaurantService();
					UserService user_service = new UserService();
					
					Restaurant restaurant = restaurant_service.findById(Local);
					Set<User> users = restaurant.getListUsers();
					
					User user = user_service.findByMail(Mail);
					
		    		System.out.println(user.getSalt());

					
					if(!users.contains(user))
					{
						resp.getWriter().write(Error.NOT_FOUNDED.toString());
					}
					else if(PasswordUtil.verifyPassword(Password, user.getPassword(), user.getSalt()) && user.isApproved())
					{
						HashSet<ProductOrder> set = new HashSet<ProductOrder>();
						Order order = new Order();
						order.setListProductOrder(set);
						
						session.setAttribute("UserLogged", user);
						session.setAttribute("Cart", order);
						session.setAttribute("Restaurant", restaurant);
						resp.getWriter().write(Error.COMPLETED.toString());	

					}
					else if(!user.isApproved())
					{
						resp.getWriter().write(Error.NOT_APPROVED.toString());	
					}
					else
					{
						resp.getWriter().write(Error.WRONG_PASSWORD.toString());	
					}
				}
				else
				{
					Restaurant Rest = null;
					if(session != null)
					{
						Rest = (Restaurant)session.getAttribute("Restaurant");
						RestaurantService restaurant_service = new RestaurantService();
						UserService user_service = new UserService();

						Restaurant restaurant_session = restaurant_service.findById(Rest.getId());
						

						if(restaurant_session != null)
						{
							
							Set<User> users = restaurant_session.getListUsers();
							
							User user = user_service.findByMail(Mail);
							if(!users.contains(user))
							{
								resp.getWriter().write(Error.NOT_FOUNDED.toString());
							}
							else if(PasswordUtil.verifyPassword(Password, user.getPassword(), user.getSalt()) && user.isApproved())
							{
								HashSet<ProductOrder> set = new HashSet<ProductOrder>();
								Order order = new Order();
								order.setListProductOrder(set);
								
								session.setAttribute("UserLogged", user);
								session.setAttribute("Cart", order);
								session.setAttribute("Restaurant", restaurant_session);
								
			
								resp.getWriter().write(Error.LOGGED.toString());
							}
							else if(!user.isApproved())
							{
								resp.getWriter().write(Error.NOT_APPROVED.toString());	
							}
							else
							{
								resp.getWriter().write(Error.WRONG_PASSWORD.toString());	
							}
						}
						else
						{
							resp.getWriter().write(Error.GENERIC_ERROR.toString());	
						}
					}
				}
		
	}
}
