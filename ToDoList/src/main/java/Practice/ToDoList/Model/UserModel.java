package Practice.ToDoList.Model;

import java.util.List;

public class UserModel {
	private int id;
	private String username;
	private String password;
	private String email;
	private List<ListaModel> listToDo;
public UserModel(){
		
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<ListaModel> getListToDo() {
		return listToDo;
	}


	public void setListToDo(List<ListaModel> listToDo) {
		this.listToDo = listToDo;
	}
	

}
