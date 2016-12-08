package Practice.ToDoList.Resource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import Practice.ToDoList.DAO.LoginAuthenticationDAO;
import Practice.ToDoList.DAO.UserDAO;
import Practice.ToDoList.Model.LoginAuthenticationModel;
import Practice.ToDoList.Model.UserModel;
import Practice.ToDoList.Service.LoginAuthenticationService;
import Practice.ToDoList.Service.LoginSecurity;
import Practice.ToDoList.Token.Token;
@Path("/login")
public class UserResource {

	LoginSecurity loginsecurity = new LoginSecurity();
	LoginAuthenticationService service = new LoginAuthenticationService();
	LoginAuthenticationDAO dao = new LoginAuthenticationDAO();
	UserDAO userdao = new UserDAO();
	
	
	
    private String secret = "secret";
    private static String stoken;
   
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Token checkUsernamePassword(LoginAuthenticationModel postModel){
		
		String username=dao.getUsernamePassword(postModel.getUsername(), postModel.getPassword()).getUsername();
		String password = dao.getUsernamePassword(postModel.getUsername(), postModel.getPassword()).getPassword();
		int id = dao.getUsernamePassword(postModel.getUsername(), postModel.getPassword()).getId();
		String email = dao.getUsernamePassword(postModel.getUsername(), postModel.getPassword()).getEmail();
		Claims claims = Jwts.claims().setSubject("info");
		claims.put("id", id);
		claims.put("username", username);
		claims.put("password", password);
		claims.put("email", email);
	    Token token = new Token(Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,secret).compact());
		
		return token;
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserModel getUserInfo(String token){
		try{
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(stoken).getBody();
		UserModel model = new UserModel();
		model.setUsername(body.getSubject());
		model.setPassword((String) body.get("password"));
		model.setId((int) body.get("id"));
		return model;
		
		}catch (JwtException | ClassCastException e) {
            return null;
        }
		
	}
	
}
