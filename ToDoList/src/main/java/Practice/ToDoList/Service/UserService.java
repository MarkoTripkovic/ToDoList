package Practice.ToDoList.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import Practice.ToDoList.DAO.UserDAO;
import Practice.ToDoList.Model.UserModel;
import Practice.ToDoList.Resource.LoginAuthenticationResource;

public class UserService {
	
	UserDAO userdao = new UserDAO();
	public UserModel getUser(int id){
		
		return userdao.getUser(id);
	}
	public boolean deleteUsere(int id){
		return userdao.deleteUser(id);
	}
	
public static UserModel getDataFromToken(String token){
		
		
		
		String authToken = token;
		authToken = authToken.substring("Bearer ".length()).trim();
		Claims body = Jwts.parser().setSigningKey(LoginAuthenticationResource.secret).parseClaimsJws(authToken).getBody();
		UserModel model = new UserModel();
		model.setId((int)body.get("id"));
		model.setUsername((String)body.get("username"));
		model.setPassword((String)body.get("password"));
		model.setEmail((String) body.get("email"));
		return model;
		
				
		
	
	
	}

	

}
