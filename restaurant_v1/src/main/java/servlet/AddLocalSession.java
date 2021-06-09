package servlet;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Restaurant;
import model.Error;
import model.Order;
import model.ProductOrder;
import service.RestaurantService;



public class AddLocalSession extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				Long id = Long.valueOf(req.getParameter("id"));

				RestaurantService restaurant_service = new RestaurantService();
				
				Restaurant restaurant = restaurant_service.findById(id);

				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				if(restaurant == null)
				{
					resp.getWriter().write(Error.NOT_FOUNDED.toString());
				}
				else
				{
					HashSet<ProductOrder> set = new HashSet<ProductOrder>();
					Order order = new Order();
					order.setListProductOrder(set);
					
					HttpSession session = req.getSession(true);
					session.setAttribute("Restaurant", restaurant);
					session.setAttribute("Cart", order);
					session.setAttribute("UserLogged", null);
					
					resp.getWriter().write(Error.COMPLETED.toString());
				}
	}
}
