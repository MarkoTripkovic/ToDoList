package Practice.ToDoList.Model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessages {
	private String text;
	private int status;
	
	public ErrorMessages(){
		
		
	}
public ErrorMessages(String text,int status){
		this.text = text;
		this.status = status;
		
	}		
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
