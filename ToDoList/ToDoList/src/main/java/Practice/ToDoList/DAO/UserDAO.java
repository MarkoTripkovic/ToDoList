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
        PreparedStatement ps = connection.prepareStatement("insert into todolist.Users(username, password, email) VALUES(?,?,?)");
        ps.setString(1, model.getUsername());
        ps.setString(2, model.getPassword());
        ps.setString(3, model.getEmail());
        ps.execute();
        return model;
    }catch (SQLException ex) {
		    	System.out.println("Catch");
		        ex.printStackTrace();
		        return null;
		    }
    	
	}
	
	
	public boolean checkUsernameEmailExist(UserModel model){
		int count = 0;
		boolean check = false;
		Connection connection = DataBaseConnection.getConnection();
		try {
	        PreparedStatement ps = connection.prepareStatement("SELECT count(*) FROM WHERE todolist.Users(username) VALUES(?)");
	        ps.setString(1, model.getUsername());
	       ResultSet rs = ps.executeQuery();
	       if(rs.next()){
	    	   check = true;
	       }
	       
		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Brojac je :"+count);
		
		if(check==true){
			return true;
		}else{
			
			return false;
		}
	}
	

}