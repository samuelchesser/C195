/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;
import utils.DBConnection;
import static model.User.activeUser;

/**
 *
 * @author schesser
 */
public class UserDAO {
    
    //Attempt to login
    public static Boolean attemptLogin(String userName, String password) throws SQLException {
    Statement statement = DBConnection.getConnection().createStatement();
    String query = "SELECT userId, userName, password FROM user WHERE userName ='" + userName + "' AND password='" + password +"'";
    ResultSet result = statement.executeQuery(query);
    if(result.next()) {
        activeUser = new User(result.getInt("userId"),
                result.getString("userName"),
                result.getString("password"));
                statement.close();
                System.out.println("USER FOUND: " + activeUser);
                return true;
                
                
    }
    else {statement.close(); return false;}
    
            }
    
}
