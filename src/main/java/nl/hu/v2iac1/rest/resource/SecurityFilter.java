package nl.hu.v2iac1.rest.resource;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import nl.hu.v2iac1.Configuration;

public class SecurityFilter implements Filter {
	public void init(FilterConfig arg0) throws ServletException {
		/* Filter is being placed into service, do nothing. */
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest r2 = (HttpServletRequest) req;
		
		Configuration con = new Configuration();
		String sessionAttribute = con.getValue(Configuration.Key.SESSIONATTRIBUTE);
		
		if(r2.getSession().getAttribute("attribute") == null){
			r2.getRequestDispatcher("/helaas.html").forward(req, resp);
			System.out.println("ik filter je hele gezicht onder");
		}
		else if (r2.getSession().getAttribute("attribute").equals(sessionAttribute)) {
			chain.doFilter(req, resp);
			r2.getSession().setAttribute("attribute", null);
		} 
		else {
			r2.getRequestDispatcher("/helaas.html").forward(req, resp);
			System.out.println("ik filter je hele gezicht onder");
		}
	}

	public void destroy() {
		/* Filter is being taken out of service, do nothing. */
	}
}
