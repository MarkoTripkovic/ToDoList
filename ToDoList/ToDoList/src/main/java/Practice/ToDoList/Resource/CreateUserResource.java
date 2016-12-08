package Practice.ToDoList.Resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
			ErrorMessages message = new ErrorMessages("Username or Password or email = null",404);
			
			return Response.status(Status.NOT_FOUND).entity(message).build();
		}
			if(userdao.checkUsernameEmailExist(model)==false){
			return Response.status(Status.CREATED).entity(userdao.createUser(model)).build();
			}else{
		ErrorMessages message = new ErrorMessages("Username alredy exist",404);
		return Response.status(Status.NOT_FOUND).entity(message).build();
			}
}
	

}
