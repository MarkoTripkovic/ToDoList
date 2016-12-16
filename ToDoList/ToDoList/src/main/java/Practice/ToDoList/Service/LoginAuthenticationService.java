package Practice.ToDoList.Service;
import io.jsonwebtoken.Jwts;

import java.io.IOException;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Practice.ToDoList.Model.ErrorMessages;
import Practice.ToDoList.Resource.LoginAuthenticationResource;






//@Provider
@Produces(MediaType.APPLICATION_JSON)
public class LoginAuthenticationService implements ContainerRequestFilter {
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("loginAuth");
		String authenticationheader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authenticationheader == null ) {
			ErrorMessages message = new ErrorMessages("Emty header",401);
			Response response = Response.status(401).entity(message).build();
			throw new WebApplicationException(response);
			
		} 
		
		
		try {
			 // Validate the token
            validateToken(authenticationheader);
			
		} catch (Exception e) {
			ErrorMessages message = new ErrorMessages("Token expired",401);
			requestContext.abortWith (Response.status(Response.Status.UNAUTHORIZED).entity(message).build());
		}			
		
	}
	private void validateToken(String token) {
		Jwts.parser().setSigningKey(LoginAuthenticationResource.secret).parseClaimsJws(token);
		
	}

	
}