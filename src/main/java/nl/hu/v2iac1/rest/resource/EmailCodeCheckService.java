package nl.hu.v2iac1.rest.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.hu.v2iac1.Configuration;

public class EmailCodeCheckService extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		Configuration con = new Configuration();
		String emailcode  = req.getParameter("input1");
		String localEmailCode = con.getValue(Configuration.Key.EMAILCODE);
		
		if (emailcode.equals(localEmailCode)){
			resp.sendRedirect("rest/topsecret/");
		}
		else
			resp.sendRedirect("helaas.html");
	}

}
