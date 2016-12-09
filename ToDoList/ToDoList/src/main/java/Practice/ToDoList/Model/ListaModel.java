package Practice.ToDoList.Model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListaModel {
	
	private String title;
	private String text;
	private String author;
	private String date;
	private int id;
	private int userId;
	
	public ListaModel(){
		
	}
	public ListaModel(String title,String text,String author,String date,int id,int userID){
		this.id = id;
		this.text = text;
		this.title = title;
		this.author = author;
		this.userId = userID;
		this.date = date;
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getData() {
		return date;
	}
	public void setData(String data) {
		this.date = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	

}
