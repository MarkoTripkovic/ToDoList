package Practice.ToDoList.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Practice.ToDoList.DataBase.DataBaseConnection;
import Practice.ToDoList.Model.ListaModel;

public class ListDAO {
	
	public List<ListaModel> getListForuser(int id){
		Connection connection = DataBaseConnection.getConnection();
		try {
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Podaci WHERE userId = ?");
	        
	       ps.setInt(1,id);
	       ResultSet rs = ps.executeQuery();
	       List<ListaModel> listamodela = new ArrayList<ListaModel>();
	       while(rs.next()){
	    	  ListaModel model = new ListaModel();
	    	  model.setId(rs.getInt("id"));
	    	  model.setUserId(rs.getInt("UserId"));
	    	  model.setTitle(rs.getString("Title"));
	    	  model.setText(rs.getString("Text"));
	    	  model.setAuthor(rs.getString("Author"));
	    	  listamodela.add(model);
	    	  System.out.println(model.getTitle());
	       }
	       return listamodela;
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
		
	}
	public ListaModel insertItemInList(ListaModel model){
		
		Connection connection = DataBaseConnection.getConnection();
		System.out.println(model.getTitle());
		
	    try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO Podaci(Title, Text, Author, Date, UserId) VALUES(?,?,?,?,?)");
	        ps.setString(1, model.getTitle());
	        ps.setString(2, model.getText());
	        ps.setString(3, model.getAuthor());
	        ps.setString(4, model.getData());
	        ps.setInt(5, model.getUserId());
	        
	        ps.execute();
	        System.out.println("Executed querry");
	        return model;
	    }catch (SQLException ex) {
			        ex.printStackTrace();
			        System.out.println("Error!!!");
			        return null;
			        
			    }
		
	}
	public ListaModel getItemFromList(int id){
		
		Connection connection = DataBaseConnection.getConnection();
		try {
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Podaci WHERE id = ?");
	        
	       ps.setInt(1,id);
	       ResultSet rs = ps.executeQuery();
	       if(rs.next()){
	    	  ListaModel model = new ListaModel();
	    	  model.setId(rs.getInt("id"));
	    	  model.setUserId(rs.getInt("UserId"));
	    	  model.setTitle(rs.getString("Title"));
	    	  model.setText(rs.getString("Text"));
	    	  model.setAuthor(rs.getString("Author"));
	    	  System.out.println(model.getTitle());
	    	  return model;
	    	  
	    	   
	       }
	       
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error");
		}	
		return null;
		
	}
			
			
		
		
	

}
