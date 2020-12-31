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
import java.sql.Timestamp;
import static java.time.LocalDate.now;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.User;
import utils.DBConnection;
import static model.User.activeUser;
import utils.DBQuery;

/**
 *
 * @author schesser
 */
public class UserDAO {
    public static String currentUser = "fake";
    static String apptTypes[] = {"Kickoff", "Checkin", "Retro", "Launch", "Training"};
    static LocalDateTime now = LocalDateTime.now();
    static ResultSet count;
    static int kickoffCount = 0;
    static int checkinCount = 0;
    static int retroCount = 0;
    static int launchCount = 0;
    static int trainingCount = 0;
    static int totalCount = 0;
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
           currentUser = result.getString("userName");
           AppointmentDAO.getAppointmentsAlert();
           return true;
           
           
       }          
    } 
    return false;
    
    }
    
    public static ObservableList<User> getUsers() throws SQLException{
        ObservableList<User> users=FXCollections.observableArrayList();
        String query = "SELECT user.userId, user.userName FROM user WHERE active = 1";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
                
         while(result.next()) {
               User user = new User();
               user.setUserId(result.getInt("user.userId"));
               user.setUserName(result.getString("user.userName"));
             
               users.add(user);
            
         }
        return users;
    }
    
    public static User getCurrentUser() {
        User user = new User();
        user.setUserName(currentUser);
        return user;
    }
    
    public static ObservableList<User> getConsultantMonthlyApts() throws SQLException {
        LocalDateTime filter = now.plusMonths(1);
        Timestamp filterTS = Timestamp.valueOf(filter);
        Timestamp todayTS = Timestamp.valueOf(now);
        
        ObservableList<User> userAppts=FXCollections.observableArrayList();
        String query = "SELECT DISTINCT user.userId, user.userName FROM user";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
        
        while(result.next()) {
            User user = new User();
            user.setUserId(result.getInt("user.userId"));
            user.setUserName(result.getString("user.userName"));
                
            String apptCountQuery = "SELECT * FROM appointment WHERE appointment.type = ? AND appointment.start <= ? AND appointment.start >= ? AND appointment.userId = ?";
            
            DBQuery.setPreparedStatement(apptCountQuery, DBConnection.getConnection());
            ps = DBQuery.getPreparedStatement();
            ps.setString(1, "Kickoff");
            ps.setTimestamp(2, filterTS);
            ps.setTimestamp(3, todayTS);
            ps.setInt(4, result.getInt("user.userId"));
            ps.execute();
            count = ps.getResultSet();
            count.last();
            kickoffCount = count.getRow();
            totalCount += kickoffCount;
            user.setKickoffCount(String.valueOf(kickoffCount));
            
            DBQuery.setPreparedStatement(apptCountQuery, DBConnection.getConnection());
            ps = DBQuery.getPreparedStatement();
            ps.setString(1, "Checkin");
            ps.setTimestamp(2, filterTS);
            ps.setTimestamp(3, todayTS);
            ps.setInt(4, result.getInt("user.userId"));
            ps.execute();
            count = ps.getResultSet();
            count.last();
            checkinCount = count.getRow();
            totalCount += checkinCount;
            user.setCheckinCount(String.valueOf(checkinCount));
            
            DBQuery.setPreparedStatement(apptCountQuery, DBConnection.getConnection());
            ps = DBQuery.getPreparedStatement();
            ps.setString(1, "Retro");
            ps.setTimestamp(2, filterTS);
            ps.setTimestamp(3, todayTS);
            ps.setInt(4, result.getInt("user.userId"));
            ps.execute();
            count = ps.getResultSet();
            count.last();
            retroCount = count.getRow();
            totalCount += retroCount;
            user.setRetroCount(String.valueOf(retroCount));
            
            
            DBQuery.setPreparedStatement(apptCountQuery, DBConnection.getConnection());
            ps = DBQuery.getPreparedStatement();
            ps.setString(1, "Launch");
            ps.setTimestamp(2, filterTS);
            ps.setTimestamp(3, todayTS);
            ps.setInt(4, result.getInt("user.userId"));
            ps.execute();
            count = ps.getResultSet();
            count.last();
            launchCount = count.getRow();
            totalCount += launchCount;
            user.setLaunchCount(String.valueOf(launchCount));
            
            
            DBQuery.setPreparedStatement(apptCountQuery, DBConnection.getConnection());
            ps = DBQuery.getPreparedStatement();
            ps.setString(1, "Training");
            ps.setTimestamp(2, filterTS);
            ps.setTimestamp(3, todayTS);
            ps.setInt(4, result.getInt("user.userId"));
            ps.execute();
            count = ps.getResultSet();
            count.last();
            trainingCount = count.getRow();
            totalCount += trainingCount;
            user.setTrainingCount(String.valueOf(trainingCount));
            user.setTotalCount(String.valueOf(totalCount));
            userAppts.add(user);    
        }
        return userAppts;
    }
    
    
    
    
    
}
