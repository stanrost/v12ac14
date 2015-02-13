package nl.hu.v2iac1.rest.resource;

import java.io.IOException;

import nl.hu.v2iac1.Configuration;

import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/secret")
@Produces(MediaType.TEXT_PLAIN)
public class SecretRestService extends AbstractRestService{
	
    @GET
    @Path("/")
    public Response getSecret() {
        String output = "This is the secret: " + configuration.getValue(Configuration.Key.SECRET);
        return Response.status(200).entity(output).build();

    }

}