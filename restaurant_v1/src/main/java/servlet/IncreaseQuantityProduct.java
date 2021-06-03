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
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Product;
import model.ProductOrder;
import service.ProductService;
import model.Error;
import model.Order;



public class IncreaseQuantityProduct extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				Long idProduct = Long.valueOf(req.getParameter("idProduct"));
				
				ProductService product_service = new ProductService();
				
				Product product = product_service.findById(idProduct);

				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				
				HttpSession session = req.getSession(false);
				if(session != null)
				{
					Order cart = (Order)session.getAttribute("Cart");
					
					boolean presente=false;
					for(ProductOrder PO: cart.getListProductOrder())
					{
						if(PO.getProduct().getId() == product.getId())
						{
							PO.setQuantity(PO.getQuantity()+1);
							presente=true;
						}
					}
					
					
					if(presente)
						resp.getWriter().write(Error.COMPLETED.toString());
					else
						resp.getWriter().write(Error.GENERIC_ERROR.toString());
				}
		
		
	}
}
