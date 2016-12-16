package Practice.ToDoList.Resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Practice.ToDoList.Model.ErrorMessages;
import Practice.ToDoList.Model.ListaModel;
import Practice.ToDoList.Service.ListService;
@Path("/todolist")
public class ListResource {
	
	ListService listservice = new ListService();
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ListaModel insertItemInList(ListaModel model){
		return listservice.insertItemInList(model);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idofuser}")
	public List<ListaModel> getListForUser(@PathParam("idofuser") int id){
		
		if(listservice.getListFoUser(id)==null){
			ErrorMessages message = new ErrorMessages("No user with that id",400);
			Response response = Response.status(Status.BAD_REQUEST).entity(message).build();
			throw new WebApplicationException(response);
		}else{
		
		return listservice.getListFoUser(id);
		}
		
			
		
	}
	
	@GET
	@Path("/listitem/{itemoflist}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItemFromList(@PathParam("itemoflist") int id){
		if(listservice.getItemFromList(id)==null){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}else{
			return Response.status(Status.ACCEPTED).entity(listservice.getItemFromList(id)).build();
			
		}
		
			
			
		
	}
	
	@DELETE
	@Path("/listitem/{itemoflist}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItemFromList(@PathParam("itemoflist") int id){
		
		if(listservice.deleteItemFromList(id)==false){
			ErrorMessages message = new ErrorMessages("Nothing to delete",417);
			Response response = Response.status(Status.BAD_REQUEST).entity(message).build();
			throw new WebApplicationException(response);
		}else{
			
			ErrorMessages message = new ErrorMessages("Deleted",200);
		  return Response.status(Status.OK).entity(message).build();
			
		}
	}
	
	
	

}
