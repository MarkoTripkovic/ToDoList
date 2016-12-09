package Practice.ToDoList.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Practice.ToDoList.DataBase.DataBaseConnection;
import Practice.ToDoList.Model.UserModel;

public class UserDAO {
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
	

}