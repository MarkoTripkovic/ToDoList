package Practice.ToDoList.Resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Practice.ToDoList.DAO.UserDAO;
import Practice.ToDoList.Model.ErrorMessages;
import Practice.ToDoList.Model.UserModel;
@Path("/signup")
public class CreateUserResource {
	
	UserDAO userdao = new UserDAO();
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(UserModel model){
		
		if(model.getEmail()==null||model.getPassword()==null||model.getUsername()==null){
			
			ErrorMessages message = new ErrorMessages("Username can't be null",417);
			Response response = Response.status(Status.BAD_REQUEST).entity(message).build();
			throw new WebApplicationException(response);
			
			
		}else{
		
			if(userdao.checkUsernameEmailExist(model)==false){
				
			return Response.status(Status.CREATED).entity(userdao.createUser(model)).build();
			}
			else{
		
				ErrorMessages message = new ErrorMessages("Username alredy exist",417);
				Response response = Response.status(Status.UNAUTHORIZED).entity(message).build();
				throw new WebApplicationException(response);
				
			}
		}
}
	

}
