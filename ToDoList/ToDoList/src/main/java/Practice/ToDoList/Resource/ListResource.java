package Practice.ToDoList.Resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
		
		return listservice.getListFoUser(id);
	}
	
	
	
	@GET
	@Path("/{idofuser}/listitem/{itemoflist}")
	@Produces(MediaType.APPLICATION_JSON)
	public ListaModel getItemFromList(@PathParam("itemoflist") int id){
		return listservice.getItemFromList(id);
	}
	
	
	

}
