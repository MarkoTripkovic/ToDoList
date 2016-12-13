package Practice.ToDoList.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import Practice.ToDoList.Model.UserModel;
import Practice.ToDoList.Resource.LoginAuthenticationResource;





public class LoginAuthenticationService implements ContainerRequestFilter {
	
	@Override
	//****Provera tokena****//
	public void filter(ContainerRequestContext arg0) throws IOException {
		// TODO Auto-generated method stub
		
	
		
		
	}

	
}