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

//import model.Email;
import model.Order;
import model.Product;

import model.Error;
import model.User;
import service.UserService;
import utils.PasswordUtil;


public class UpdateLoggedUser extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
				String Nome = req.getParameter("Nome");
				String Cognome = req.getParameter("Cognome");
				String NumeroTelefono = req.getParameter("NumeroTelefono");
				String Password = req.getParameter("Password");
				String Mail = req.getParameter("Mail");
				String Indirizzo = req.getParameter("Indirizzo");
				String Disabilitato = req.getParameter("Disabilitato");

				UserService user_service = new UserService();
				
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");
				
				User user = null;
				
				HttpSession session = req.getSession(false);
				if(session != null)
				{
					User userLogged = (User)session.getAttribute("UserLogged");
					user = user_service.findById(userLogged.getTelephone());
					user.setName(Nome);
					user.setSurname(Cognome);
					user.setTelephone(NumeroTelefono);
					user.setMail(Mail);
					user.setAddress(Indirizzo);
					user.setDisabled(Boolean.valueOf(Disabilitato));
					
					PasswordUtil password_generator = new PasswordUtil();
					String salt = password_generator.generateSalt(512).get();
					String hash_key = password_generator.hashPassword(Password, salt).get();
					
					user.setPassword(hash_key);
					
					user_service.update(user);
					session.setAttribute("UserLogged", user);

					String Message = "Utente aggiornato con successo! \r\n" + "Mail: " + user.getMail() + "\r\n" + "Password: " + user.getPassword() +"\r\n"+ "Controlla il tuo account: http://localhost:8080/Restaurant/MyAccount.html";
						
					//Email mail = new Email();
					//mail.Send(user.getMail(), "Utente aggiornato!", Message);
						
					resp.getWriter().write(Error.COMPLETED.toString());
				}
				else
				{
					resp.getWriter().write(Error.GENERIC_ERROR.toString());
				}
				
				
				

				
			
				
				
				
				

			
		
	}
}
