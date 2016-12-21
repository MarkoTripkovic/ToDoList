package Practice.ToDoList.Exception;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NothingFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NothingFoundException(String message){
		super(message);
				
	}
	

}
