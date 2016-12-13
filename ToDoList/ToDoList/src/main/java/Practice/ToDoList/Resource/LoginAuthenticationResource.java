package Practice.ToDoList.Resource;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;

import Practice.ToDoList.DAO.LoginAuthenticationDAO;
import Practice.ToDoList.DAO.UserDAO;
import Practice.ToDoList.Model.LoginAuthenticationModel;
import Practice.ToDoList.Model.UserModel;
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
		public Token checkUsernamePassword(LoginAuthenticationModel postModel){
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
			return token;
		

		}
		@GET
		@Path("/getuser")
		@Produces(MediaType.APPLICATION_JSON)
		public UserModel getInfoFromtoken(ContainerRequestContext requestContex){
			
				return UserService.getDataFromToken(requestContex.getHeaderString("Authorization"));
			
			
		}
		
		
		
}