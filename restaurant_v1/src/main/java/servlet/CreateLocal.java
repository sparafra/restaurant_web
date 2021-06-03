package servlet;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;


import model.Ingredient;
import model.Product;
import model.Restaurant;
import model.User;
import service.RestaurantService;
import service.UserService;
import utils.PasswordUtil;

@MultipartConfig

public class CreateLocal extends HttpServlet implements ServletContextListener{
	
	/**
     * Directory where uploaded files will be saved, its relative to
     * the web application directory.
     */
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
    	
    
    	User user = new User();
    	Restaurant Rest = new Restaurant();

		RestaurantService restaurant_service = new RestaurantService();
		UserService user_service = new UserService();

		
		String NomeUser = null;
		String CognomeUser = null;
    	String NumeroUser = null;
    	String PasswordUser = null;
    	String ConfermaPasswordUser = null;
    	String MailUser = null;


    	String ViaUser = null;
    	String NCivicoUser = null;
    	String CittaUser = null;
    	String CapUser = null;
    	
    	
    	String NomeLocale = null;
    	String MailLocale = null;
    	String NumeroLocale = null;

    	String ViaLocale = null;
    	String NCivicoLocale = null;
    	String CittaLocale = null;
    	String CapLocale = null;    	
    	Boolean VisibleLocale = false;
    	
    	
    	try {
	    	//GET DATA PRODUCT 
    		NomeUser = request.getParameter("NomeUser");
    		CognomeUser = request.getParameter("CognomeUser");
    		NumeroUser = request.getParameter("TelefonoUser");
    		PasswordUser = request.getParameter("PasswordUser");
    		ConfermaPasswordUser = request.getParameter("ConfermaPasswordUser");
    		MailUser = request.getParameter("MailUser");
    		
    		ViaUser = request.getParameter("ViaUser");
    		NCivicoUser = request.getParameter("nCivicoUser");
    		CittaUser = request.getParameter("CittaUser");
    		CapUser = request.getParameter("CapUser");
    		
    		
    		NomeLocale = request.getParameter("NomeLocale");
    		MailLocale = request.getParameter("MailLocale");
    		NumeroLocale = request.getParameter("TelefonoLocale");
    		
    		ViaLocale = request.getParameter("ViaLocale");
    		NCivicoLocale = request.getParameter("CivicoLocale");
    		CittaLocale = request.getParameter("CittaLocale");
    		CapLocale = request.getParameter("CapLocale");
    		
    	}catch(Exception e) {System.out.println(e.getMessage());}
    	
    	System.out.println(NomeUser);
    	System.out.println(CognomeUser);
    	System.out.println(NumeroUser);
    	System.out.println(PasswordUser);
    	System.out.println(ConfermaPasswordUser);
    	System.out.println(MailUser);
    	
    	System.out.println(ViaUser);
    	System.out.println(NCivicoUser);
    	System.out.println(CittaUser);
    	System.out.println(CapUser);
    	
    	System.out.println(NomeLocale);
    	System.out.println(MailLocale);
    	System.out.println(NumeroLocale);

    	System.out.println(ViaLocale);
    	System.out.println(NCivicoLocale);
    	System.out.println(CittaLocale);
    	System.out.println(CapLocale);

    	
    	
    	
    	
    	if(user_service.findByMail(MailUser) != null)
    	{
    		System.out.println("mail presente");
    		request.setAttribute("message", "Errore! Risulta già iscritto alla piattaforma un utente con questa mail");
            getServletContext().getRequestDispatcher("/Dashboard/landingpage/result.jsp").forward(
                    request, response);
    	}
    	else if(restaurant_service.findByMail(MailLocale) != null)
    	{
    		request.setAttribute("message", "Errore! Risulta gi� iscritto alla piattaforma un locale con questa mail");
            getServletContext().getRequestDispatcher("/Dashboard/landingpage/result.jsp").forward(
                    request, response);
    	}
    	else
    	{
    		Rest.setActive(false);
    		Rest.setAddress(ViaLocale+", " + NCivicoLocale + ", " + CittaLocale + ", " + CapLocale);
    		Rest.setMail(MailLocale);
    		Rest.setName(NomeLocale);
    		Rest.setTelephone(NumeroLocale);
    		
    		
    		user.setAdmin(true);
    		user.setSurname(CognomeUser);
    		user.setApproved(true);
    		user.setAddress(ViaUser+", " + NCivicoUser + ", " + CittaUser + ", " + CapUser);
    		user.setMail(MailUser);
    		user.setName(NomeUser);
    		user.setTelephone(NumeroUser);
    		user.setDisabled(false);
    		
    		String salt = PasswordUtil.generateSalt(256).get();
    		String hash_key = PasswordUtil.hashPassword(PasswordUser, salt).get();
    		user.setPassword(hash_key);
    		user.setSalt(salt);
    		
    		
    		
    	}
    	
 	
    	
    	// gets absolute path of the web application
        String applicationPath = request.getServletContext().getRealPath("");
        
        System.out.println(applicationPath);
        
        String fileName = null;
        //Get all the parts from request and write it to the file on server
        
        //Checking Images are available
        for (Part part : request.getParts()) {
        	//System.out.println(part.getName());
        	if(part.getName().equals("Logo[]") || part.getName().equals("Background[]"))
        	{
        		fileName = getFileName(part);
        		if(fileName.equals("") ||fileName.equals("null") || fileName == null)
        		{
        			request.setAttribute("message", "Errore! Controlla di aver inserito l'immagine. ");
                    getServletContext().getRequestDispatcher("/Dashboard/landingpage/result.jsp").forward(
                            request, response);
        		}
        	}
        }

        String uploadFilePath = "assets/images/Restaurants/"+ Rest.getName()+ "/Logo/";
        String uploadFilePathBackground = "assets/images/Restaurants/"+ Rest.getName()+ "/Background/";
        // creates the save directory if it does not exists
        File fileSaveDir = new File(applicationPath + uploadFilePath);
        File fileSaveDirBackground = new File(applicationPath + uploadFilePathBackground);

        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        if (!fileSaveDirBackground.exists()) {
        	fileSaveDirBackground.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        System.out.println("Upload File Directory="+fileSaveDirBackground.getAbsolutePath());

        for (Part part : request.getParts()) {
        	System.out.println(part.getName());
        	if(part.getName().equals("Logo[]"))
        	{
        		//System.out.println(part.getHeader("files[]"));
	            fileName = getFileName(part);
	            System.out.println(fileName);
	            System.out.println(part.getSubmittedFileName());
	            
	            String fName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	            InputStream fileContent = part.getInputStream();
	            
	            BufferedImage image = ImageIO.read(fileContent);
	            int height = image.getHeight();
	            int width = image.getWidth();
	            
	            BufferedImage ScaledImage = resize(image, 50, 100 );

	            System.out.println(height);
	            System.out.println(width);
	            
	            byte[] buffer = new byte[fileContent.available()];
	            fileContent.read(buffer);
	         
	            File fileDir = new File(applicationPath + uploadFilePath);

	            if (!fileDir.exists()) {
	            	fileDir.mkdirs();
	            }
	            
	            File targetFile = new File(applicationPath + uploadFilePath + "logo.png");

	
	            ImageIO.write(ScaledImage, "png", targetFile);

	            
	            Rest.setLogo_url(uploadFilePath + "logo.png");

        	}
        	else if(part.getName().equals("Background[]"))
        	{
        		//System.out.println(part.getHeader("files[]"));
	            fileName = getFileName(part);
	            System.out.println(fileName);
	            System.out.println(part.getSubmittedFileName());
	            
	            String fName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	            InputStream fileContent = part.getInputStream();
	            
	            BufferedImage image = ImageIO.read(fileContent);
	            int height = image.getHeight();
	            int width = image.getWidth();
	            
	            BufferedImage ScaledImage = resize(image, 1080, 1920 );

	            System.out.println(height);
	            System.out.println(width);
	            
	            byte[] buffer = new byte[fileContent.available()];
	            fileContent.read(buffer);
	         
	            File fileDir = new File(applicationPath + uploadFilePathBackground);

	            if (!fileDir.exists()) {
	            	fileDir.mkdirs();
	            }
	            
	            File targetFile = new File(applicationPath + uploadFilePathBackground + "background.png");

	     
	            ImageIO.write(ScaledImage, "png", targetFile);

	            
	            Rest.setBackground_url(uploadFilePathBackground + "background.png");
        	}
        	
        }
        
        
        System.out.println(user.getTelephone());
        
        
        Set<User> setUser = new HashSet<User>();
        setUser.add(user);
        Rest.setListUsers(setUser);
        restaurant_service.persist(Rest);

        
        //Invia mail con link di accesso alla dashboard ...Login.html?id=11
        String Message = "Registrazione effettuata con successo! \r\n" + "Mail: " + user.getMail() + "\r\n" + "Password: " + user.getPassword() +"\r\n"+ "Puoi accedere alla tua Dashboard dal seguente link localhost:8080/Restaurant/Dashboard/default/Login.html?id="+Rest.getId();
		
		//Email mail = new Email();
		//mail.Send(user.getMail(), "Registrazione effettuata!", Message);
        
        request.setAttribute("message", "Utente e Locale Creato Correttamente! ");
        getServletContext().getRequestDispatcher("/Dashboard/landingpage/result.jsp").forward(
                request, response);
               
              
                
    }
 
    /**
     * Utility method to get file name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }    
    private BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
}
