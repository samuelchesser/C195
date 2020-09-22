/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javafx.collections.FXCollections;
import model.Appointment;
import utils.DBConnection;
import static model.User.activeUser;
import utils.DBQuery;

/**
 *
 * @author schesser
 */
public class AppointmentDAO {
    static PreparedStatement ps;
    
    public static ObservableList<Appointment> getAppointments() throws SQLException{
        ObservableList<Appointment> appointments=FXCollections.observableArrayList();
        String query = "SELECT customer.customerName, user.userName, appointment.type, appointment.title, appointment.start, appointment.end FROM appointment INNER JOIN customer ON customer.customerId = appointment.customerId INNER JOIN user on user.userId = appointment.userId";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
        ResultSetMetaData rsmd = result.getMetaData();
   
      /* int columnsNumber = rsmd.getColumnCount();
        System.out.println("Result Set: " + result.toString());
      */
                
         while(result.next()) {
               Appointment appointment = new Appointment(); 
              // customer.setCustomerName("customer.customerName");
              // user.setAppointmentConsultant("user.userName");
               appointment.setAppointmentType(result.getString("appointment.type"));
               appointment.setAppointmentTitle(result.getString("appointment.title"));
               //appointment.setAppointmentDate("appointment.start");
              // String startTime = result.getString("appointment.start");
               //Calendar startTimeUTC = DateTimeConverters.stringToCalendar(startTime);
              // String endTime = result.getString("appointment.end");
               //Calendar endTimeUTC = DateTimeConverters.stringToCalendar(endTime);
               
             appointments.add(appointment);
             
             System.out.println("Appointment: " + appointment);
             /*for (int i = 1; i <= columnsNumber; i++) {
           if (i > 1) System.out.print(",  ");
           String columnValue = result.getString(i);
           System.out.print(columnValue + " " + rsmd.getColumnName(i));
       }
       System.out.println("");
*/
         }
        return appointments;
    }
    
}
