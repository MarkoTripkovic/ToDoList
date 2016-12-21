package Practice.ToDoList.Exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import Practice.ToDoList.Model.ErrorMessages;

public class DataNotFoudExceptionMaper implements ExceptionMapper<NothingFoundException> {


	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response toResponse(NothingFoundException arg0) {
		ErrorMessages message = new ErrorMessages("Data not found",404);
		return Response.status(Status.NOT_FOUND).entity(message).build();
	
	}
	

}
