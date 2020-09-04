/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;
import utils.DBConnection;
import static model.User.activeUser;
import utils.DBQuery;

/**
 *
 * @author schesser
 */
public class UserDAO {
    static PreparedStatement ps;
    //Attempt to login
    public static Boolean attemptLogin(String userName, String password) throws SQLException {
        
        String query = "SELECT userId, userName, password FROM user WHERE userName = ? AND password = ?";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setString(1, userName);
        ps.setString(2, password);
        ps.execute();
        
    
    
    
    ResultSet result = ps.getResultSet();
    while(result.next()) {
       if (result.getString("userName").equals(userName) && result.getString("password").equals(password))
       {
           System.out.println("Match");
           return true;
       }
              
                
    }
    
    return false;
            }
    
}
