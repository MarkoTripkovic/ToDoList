package Practice.ToDoList.Exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import Practice.ToDoList.Model.ErrorMessages;
@Provider
public class GenericExceptionMaper implements ExceptionMapper<Throwable> {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response toResponse(Throwable ex) {
		ErrorMessages  message = new ErrorMessages(ex.getMessage(),404);
		return Response.status(Status.NOT_FOUND).entity(message).build();
				
	}
	

}
