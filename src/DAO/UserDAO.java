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
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;
import model.User;
import utils.DBConnection;
import static model.User.activeUser;
import utils.DBQuery;

/**
 *
 * @author schesser
 */
public class UserDAO {
    public static int currentUser = 0;
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
           currentUser = result.getInt("userId");
           AppointmentDAO.getAppointmentsAlert();
           return true;
           
           
       }          
    } 
    return false;
    
    }
    
    //need to change
    public static ObservableList<User> getUsers() throws SQLException{
        ObservableList<User> users=FXCollections.observableArrayList();
        String query = "SELECT user.userId, user.userName, user.password FROM user WHERE user.active = 1";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
                
         while(result.next()) {
               User user = new User();
               user.setUserId(result.getInt("user.userId)"));
               user.setUserName(result.getString("user.userName"));
               user.setPassword(result.getString("user.password"));
               users.add(user);
          
       }
        return users;
    }
    
    
}
