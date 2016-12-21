package Practice.ToDoList.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Practice.ToDoList.DataBase.DataBaseConnection;
import Practice.ToDoList.Model.UserModel;


public class LoginAuthenticationDAO {
	
	public UserModel getUsernamePassword(String user,String password) {

	    Connection connection = DataBaseConnection.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Users WHERE username=? AND password=?");
	        ps.setString(1, user);
	        ps.setString(2, password);
	        ResultSet rs = ps.executeQuery();
	        UserModel usermodel = new UserModel();
	        while(rs.next())
	        {
	        	
	        	usermodel.setId(rs.getInt("id"));
	        	usermodel.setUsername(rs.getString("username"));
	        	usermodel.setPassword(rs.getString("password"));
	        	usermodel.setEmail(rs.getString("email"));
	        	return usermodel;
	        }
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

}
