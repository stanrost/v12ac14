package nl.hu.v2iac1.rest.resource;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.hu.v2iac1.Configuration;

public class InlogMailService extends HttpServlet{
	private Configuration con;
	
	public void init(ServletConfig config) throws ServletException {
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		String fillInUsername  = req.getParameter("input1");
		String fillInPassword = req.getParameter("input2");
		con = new Configuration();
		
		
		if(req.getParameter("inloggen")!= null){
			String name = con.getValue(Configuration.Key.USERNAME);
			String password = con.getValue(Configuration.Key.PASSWORD);
			
			if (fillInUsername.equals(name) && fillInPassword.equals(password)){
				String sessionAttribute = con.getValue(Configuration.Key.SESSIONATTRIBUTE);
				req.getSession().setAttribute("attribute", sessionAttribute);
				
				this.sendAMail();
				resp.sendRedirect("Emailcode.html");
				
			}
			else{
				resp.sendRedirect("helaas.html");
			}
		}
		else if(req.getParameter("registreren")!= null){
			resp.sendRedirect("helaas.html");
		}
		
	}
	
	public void sendAMail(){
		final String vanEmail = con.getValue(Configuration.Key.VANEMAIL);
		final String emailPassword = con.getValue(Configuration.Key.EMAILPASSWORD);
		String naarEmail = con.getValue(Configuration.Key.NAAREMAIL);
		String emailCode = con.getValue(Configuration.Key.EMAILCODE);
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(vanEmail, emailPassword);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(vanEmail));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(naarEmail));
			message.setSubject("OKIDOKI ");
			message.setText(emailCode + "");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
