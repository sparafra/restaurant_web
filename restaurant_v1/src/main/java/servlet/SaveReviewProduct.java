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
import model.Error;
import model.Order;
import model.Product;
import model.Restaurant;
import model.ReviewProduct;
import model.State;
import model.User;
import service.ProductService;
import service.ReviewProductService;
import service.UserService;
import model.Error;



public class SaveReviewProduct extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				Long idProdotto = Long.valueOf(req.getParameter("idProdotto"));
				int Voto = Integer.valueOf(req.getParameter("Voto"));
				
				System.out.println("TEST");
				
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				
				ReviewProductService review_product_service = new ReviewProductService();
				ProductService product_service = new ProductService();
				UserService user_service = new UserService();
				
				HttpSession session = req.getSession(false);
				if(session != null)
				{
					User user = (User)session.getAttribute("UserLogged");
					
					ReviewProduct rev = new ReviewProduct();
					rev.setProduct(product_service.findById(idProdotto));
					rev.setUser(user_service.findById(user.getTelephone()));
					rev.setVote(Voto);
					
					Date currentTime = Calendar.getInstance().getTime();
					rev.setDate_time(currentTime);
					
					review_product_service.persist(rev);
					
					
					resp.getWriter().write(Error.COMPLETED.toString());
				}
				
					
				String Message = "Recensione al prodotto inviata correttamente";
					
				//Email mail = new Email();
				//mail.Send(user.getMail(), "Recensione Inviata!", Message);
					
			
				
				
				
				

			
		
	}
}
