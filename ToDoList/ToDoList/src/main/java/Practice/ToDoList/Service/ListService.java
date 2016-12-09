package Practice.ToDoList.Service;

import java.util.List;

import Practice.ToDoList.DAO.ListDAO;
import Practice.ToDoList.Model.ListaModel;

public class ListService {
	 
	ListDAO listdao = new ListDAO();
	
	public List<ListaModel> getListFoUser(int id){
		return listdao.getListForuser(id);
		
	}
	public ListaModel insertItemInList(ListaModel model){
		
		return listdao.insertItemInList(model);
	}
	public ListaModel getItemFromList(int id){
		
		return listdao.getItemFromList(id);
	}
		
		
	
	
	

}
