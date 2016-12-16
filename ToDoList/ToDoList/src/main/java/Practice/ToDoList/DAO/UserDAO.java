package Practice.ToDoList.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Practice.ToDoList.DataBase.DataBaseConnection;
import Practice.ToDoList.Model.ListaModel;
import Practice.ToDoList.Model.UserModel;

public class UserDAO {
	ListDAO listdao = new ListDAO();
	public UserModel createUser(UserModel model){
	Connection connection = DataBaseConnection.getConnection();
    try {
        PreparedStatement ps = connection.prepareStatement("insert into Users(username, password, email) VALUES(?,?,?)");
        ps.setString(1, model.getUsername());
        ps.setString(2, model.getPassword());
        ps.setString(3, model.getEmail());
        ps.execute();
        return model;
    }catch (SQLException ex) {
		        ex.printStackTrace();
		        return null;
		    }
    	
	}
	
	
	public boolean checkUsernameEmailExist(UserModel model){
		Connection connection = DataBaseConnection.getConnection();
		try {
	        PreparedStatement ps = connection.prepareStatement("SELECT username FROM Users WHERE username = ?");
	        
	       ps.setString(1,model.getUsername());
	       ResultSet rs = ps.executeQuery();
	       if(rs.next()){
	    	  return true;
	       }
	       
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	public UserModel getUser(int id){
		Connection connection = DataBaseConnection.getConnection();
		try {
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
	        
	       ps.setInt(1,id);
	       ResultSet rs = ps.executeQuery();
	       if(rs.next()){
	    	   UserModel model = new UserModel();
	    	   model.setId(rs.getInt("id"));
	    	   model.setUsername(rs.getString("username"));
	    	   model.setPassword(rs.getString("password"));
	    	   model.setEmail(rs.getString("email"));
	    	  model.setListToDo(listdao.getListForuser(id));
	    	   
	    	   
	    	   
	    	  return model;
	    	  
	       }
	       
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
		
		
	}
	public boolean deleteUser(int id){
		
		Connection connection = DataBaseConnection.getConnection();
		try {
	        PreparedStatement ps = connection.prepareStatement("DELETE FROM Users WHERE id = ?");
	        
	       ps.setInt(1,id);
	       ps.execute();
	       return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

}