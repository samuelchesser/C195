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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.collections.FXCollections;
import model.Appointment;
import utils.DBConnection;
import static model.User.activeUser;
import utils.DBQuery;
import model.Customer;

/**
 *
 * @author schesser
 */
public class AppointmentDAO {
    public static String appointmentAlerts;
    static PreparedStatement ps;
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd");
    static LocalDateTime now = LocalDateTime.now();
    public static int foundAppts = 0;
    //static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd");
    
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
               appointment.setCustomerName(result.getString("customer.customerName"));
               appointment.setConsultantName(result.getString("user.userName"));
               appointment.setAppointmentType(result.getString("appointment.type"));
               appointment.setAppointmentTitle(result.getString("appointment.title"));
               appointment.setAppointmentDate(dateFormatter.format(result.getTimestamp("appointment.start").toLocalDateTime().toLocalDate()));
               appointment.setAppointmentStart(result.getTimestamp("appointment.start").toLocalDateTime().toLocalTime().toString());
               appointment.setAppointmentEnd(result.getTimestamp("appointment.end").toLocalDateTime().toLocalTime().toString());
               
               appointments.add(appointment);
             
             //System.out.println("Appointment: " + appointment);
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
    
    public static ObservableList<Appointment> filterDays(String length) throws SQLException{
        LocalDateTime filter = now.plusYears(100);
        if (length.equals("week")) { 
        filter = now.plusWeeks(1);
        }
        else if (length.equals("month")) {
        filter = now.plusMonths(1);
        }
        Timestamp filterTS = Timestamp.valueOf(filter);
        
        ObservableList<Appointment> appointments=FXCollections.observableArrayList();
        String query = "SELECT customer.customerName, user.userName, appointment.type, appointment.title, appointment.start, appointment.end FROM appointment INNER JOIN customer ON customer.customerId = appointment.customerId INNER JOIN user on user.userId = appointment.userId WHERE appointment.start <= ?";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setTimestamp(1, filterTS);
        ps.execute();
        ResultSet result = ps.getResultSet();
                
         while(result.next()) {
               Appointment appointment = new Appointment(); 
               appointment.setCustomerName(result.getString("customer.customerName"));
               appointment.setConsultantName(result.getString("user.userName"));
               appointment.setAppointmentType(result.getString("appointment.type"));
               appointment.setAppointmentTitle(result.getString("appointment.title"));
               appointment.setAppointmentDate(dateFormatter.format(result.getTimestamp("appointment.start").toLocalDateTime().toLocalDate()));
               appointment.setAppointmentStart(result.getTimestamp("appointment.start").toLocalDateTime().toLocalTime().toString());
               appointment.setAppointmentEnd(result.getTimestamp("appointment.end").toLocalDateTime().toLocalTime().toString());
               
               appointments.add(appointment);
               foundAppts = result.getRow();
         }
        return appointments;
    }
    
    public static ObservableList<Appointment> filterThirtyDays() throws SQLException{
        ObservableList<Appointment> appointments=FXCollections.observableArrayList();
        LocalDateTime filter = now.plusMonths(1);
        Timestamp filterTS = Timestamp.valueOf(filter);
        String query = "SELECT customer.customerName, user.userName, appointment.type, appointment.title, appointment.start, appointment.end FROM appointment INNER JOIN customer ON customer.customerId = appointment.customerId INNER JOIN user on user.userId = appointment.userId WHERE appointment.start <= ?";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setTimestamp(1, filterTS);
        ps.execute();
        ResultSet result = ps.getResultSet();
                
         while(result.next()) {
               Appointment appointment = new Appointment(); 
               appointment.setCustomerName(result.getString("customer.customerName"));
               appointment.setConsultantName(result.getString("user.userName"));
               appointment.setAppointmentType(result.getString("appointment.type"));
               appointment.setAppointmentTitle(result.getString("appointment.title"));
               appointment.setAppointmentDate(dateFormatter.format(result.getTimestamp("appointment.start").toLocalDateTime().toLocalDate()));
               appointment.setAppointmentStart(result.getTimestamp("appointment.start").toLocalDateTime().toLocalTime().toString());
               appointment.setAppointmentEnd(result.getTimestamp("appointment.end").toLocalDateTime().toLocalTime().toString());
               
               appointments.add(appointment);
               foundAppts = result.getRow();
               System.out.println("Appointment: " + appointment);
               System.out.println("Get Row call: " + result.getRow());
               System.out.println("Found Appts var value: " + foundAppts);
             //System.out.println("Appointment: " + appointment);
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
    
    //public static Boolean alertableAppointments() {
    
    public static String getAppointmentsAlert() throws SQLException {
        //Figure out conversion and query from here
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtIn = utczdt.toLocalDateTime();
        
        ZonedDateTime zdtOut = ldtIn.atZone(ZoneId.of("UTC"));
        ZonedDateTime zdtOutToLocalTZ = zdtOut.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime ldtOutFinal = zdtOutToLocalTZ.toLocalDateTime();
        LocalDate ldOut = ldtOutFinal.toLocalDate();
        System.out.println(ldt);
        System.out.println(zdt);
        System.out.println(utczdt);
        System.out.println(ldtIn);
        System.out.println(zdtOut);
        System.out.println(zdtOutToLocalTZ);
        System.out.println(ldtOutFinal);
        System.out.println(ldOut);
       /* String query = "SELECT count(*) FROM appointment WHERE ";
        String query = "SELECT customer.customerName, appointment.type, appointment.start, appointment.end FROM appointment INNER JOIN customer ON customer.customerId = appointment.customerId";
        String appointmentAlert = "Hello! The following appointments  are scheduled in the next 15 minutes: \n";
        Boolean alertableAppointments = false;
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
        while(result.next()) { 
               String customer = result.getString("customer.customerName");
               String appointmentType = result.getString("appointment.type");
               appointmentAlert = appointmentAlert + customer + " for a " + appointmentType + "\n";
               alertableAppointments = true;
        }
        return appointmentAlert;*/
    return ldt.toString();
    }
}
