package nl.hu.v2iac1.rest.resource;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.hu.v2iac1.Configuration;

public class InlogService extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException {
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		String fillInUsername  = req.getParameter("input1");
		String fillInPassword = req.getParameter("input2");
		Configuration con = new Configuration();
		
		
		if(req.getParameter("inloggen")!= null){
			String name = con.getValue(Configuration.Key.USERNAME);
			String password = con.getValue(Configuration.Key.PASSWORD);
			
			if (fillInUsername.equals(name) && fillInPassword.equals(password)){
				
				String sessionAttribute1 = con.getValue(Configuration.Key.SESSIONATTRIBUTE);
				
				req.getSession().setAttribute("attribute", sessionAttribute1);
				
				resp.sendRedirect("rest/secret");
				
			}
			else{
				
				resp.sendRedirect("helaas.html");
			}
		}
		else if(req.getParameter("registreren")!= null){
			resp.sendRedirect("http://www.google.nl");
		}
		
	}
}
