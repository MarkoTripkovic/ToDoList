package Practice.ToDoList.Resource;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Practice.ToDoList.DAO.LoginAuthenticationDAO;
import Practice.ToDoList.DAO.UserDAO;
import Practice.ToDoList.Exception.NothingFoundException;
import Practice.ToDoList.Model.ErrorMessages;
import Practice.ToDoList.Service.ListService;
import Practice.ToDoList.Service.LoginAuthenticationService;
import Practice.ToDoList.Service.LoginSecurity;
import Practice.ToDoList.Service.UserService;
@Path("/user")
public class UserResource{

	LoginSecurity loginsecurity = new LoginSecurity();
	LoginAuthenticationService service = new LoginAuthenticationService();
	LoginAuthenticationDAO dao = new LoginAuthenticationDAO();
	UserDAO userdao = new UserDAO();
	ListService listservice = new ListService();
	UserService userservice =new UserService();
	
	
	@GET
	@Path("/{idofuser}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserInfo(@PathParam("idofuser")int id){
		if(userservice.getUser(id)==null){
			throw new NothingFoundException("Userwiht id: "+id+" not found");
		}
		return Response.status(Status.ACCEPTED).entity(userservice.getUser(id)).build();	
	}
	
	@DELETE
	@Path("/{idofuser}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUsere(@PathParam("idofuser") int id){
		if(userservice.deleteUsere(id)==false){
			ErrorMessages message = new ErrorMessages("Nothing to delete",417);
			Response response = Response.status(Status.BAD_REQUEST).entity(message).build();
			throw new WebApplicationException(response);		
		}else{
			
			listservice.deleteAllItemsFromList(id);
			System.out.println("else");
			ErrorMessages message = new ErrorMessages("Deleted",200);			
			return Response.status(Status.OK).entity(message).build();
		}
	}
	
	

	
	
}
