package nl.hu.v2iac1.rest.resource;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.hu.v2iac1.Configuration;

public class GmailService extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException {
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		String gmail  = req.getParameter("email");
		Configuration con = new Configuration();
		
		if(req.getParameter("inloggen")!= null){
			String gmailCheck = con.getValue(Configuration.Key.GMAILCHECK);
			if (gmail.equals(gmailCheck)){
				
				String sessionAttribute1 = con.getValue(Configuration.Key.SESSIONATTRIBUTE);
				
				req.getSession().setAttribute("attribute", sessionAttribute1);
				
				resp.sendRedirect("rest/verysecret");
				
			}
			else{
				
				resp.sendRedirect("helaas.html");
			}
		}
		
	}
}
