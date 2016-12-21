package Practice.ToDoList.Resource;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Practice.ToDoList.DAO.LoginAuthenticationDAO;
import Practice.ToDoList.DAO.UserDAO;
import Practice.ToDoList.Model.ErrorMessages;
import Practice.ToDoList.Model.LoginAuthenticationModel;
import Practice.ToDoList.Service.ListService;
import Practice.ToDoList.Service.LoginAuthenticationService;
import Practice.ToDoList.Service.LoginSecurity;
import Practice.ToDoList.Service.UserService;
import Practice.ToDoList.Token.Token;
@Path("/login")
public class LoginAuthenticationResource {
	
	

		LoginSecurity loginsecurity = new LoginSecurity();
		UserService userservice = new UserService();
		LoginAuthenticationService service = new LoginAuthenticationService();
		LoginAuthenticationDAO dao = new LoginAuthenticationDAO();
		UserDAO userdao = new UserDAO();
		ListService listservice = new ListService();
		
		
		
	    public static String secret = "secret";
	    
		@POST	
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response checkUsernamePassword(LoginAuthenticationModel postModel){
			try{
			String username=dao.getUsernamePassword(postModel.getUsername(), postModel.getPassword()).getUsername();
			String password = dao.getUsernamePassword(postModel.getUsername(), postModel.getPassword()).getPassword();
			int id = dao.getUsernamePassword(postModel.getUsername(), postModel.getPassword()).getId();
			String email = dao.getUsernamePassword(postModel.getUsername(), postModel.getPassword()).getEmail();
			System.out.println(email);
			Claims claims = Jwts.claims().setSubject("info");
			claims.put("id", id);
			claims.put("username", username);
			claims.put("password", password);
			claims.put("email", email);
		    Token token = new Token(Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,secret).compact());
			return Response.status(Status.ACCEPTED).entity(token).build();
			}catch(Exception ex){
				return Response.status(Status.UNAUTHORIZED).build();
			}
		}
		@GET
		@Path("/getuser")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getInfoFromtoken(ContainerRequestContext requestContex){
			ErrorMessages messages = new ErrorMessages("Token is not valid",401);
			Response responses = Response.status(Status.UNAUTHORIZED).entity(messages).build();
				try{
					return Response.status(Status.ACCEPTED).entity( UserService.getDataFromToken(requestContex.getHeaderString("Authorization"))).build();
				
				}catch(Exception ex){
					
					
					throw new  WebApplicationException(responses);
				}
		}
		
		
		
}