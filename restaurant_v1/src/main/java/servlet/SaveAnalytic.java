package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;


import model.Analytic;

import model.Restaurant;
import model.State;
import model.User;
import service.RestaurantService;
import service.UserService;
import model.Error;



public class SaveAnalytic extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				String Pagina = req.getParameter("Pagina");
							
				
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				

				HttpSession session = req.getSession(false);
				if(session != null)
				{
					User user = (User)session.getAttribute("UserLogged");
					Restaurant Rest = (Restaurant)session.getAttribute("Restaurant");
					
					RestaurantService restaurant_service = new RestaurantService();
					
					Restaurant restaurant_logged = restaurant_service.findById(Rest.getId());
					
					
					Analytic analytic = new Analytic();
					
					analytic.setPage(Pagina);
					Date currentTime = Calendar.getInstance().getTime();
					analytic.setDate_time(currentTime);
					
					restaurant_logged.getListAnalytics().add(analytic);
					restaurant_service.update(restaurant_logged);
					
					resp.getWriter().write(Error.COMPLETED.toString());
				}
								
			
				resp.getWriter().write(Error.GENERIC_ERROR.toString());
			
		
	}
}
