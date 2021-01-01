/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.UserDAO.ps;
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
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
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
    public static int appointmentTypeCount;
    public static double allApptsCount;
    static PreparedStatement ps;
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static LocalDateTime now = LocalDateTime.now();
    static Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    
    public static int foundAppts = 0;
    //static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd");
    
    public static ObservableList<Appointment> getAppointments() throws SQLException{
        ObservableList<Appointment> appointments=FXCollections.observableArrayList();
        String query = "SELECT appointment.appointmentId, appointment.customerId, customer.customerName, appointment.userId, user.userName, appointment.type, appointment.title, appointment.start, appointment.end FROM appointment INNER JOIN customer ON customer.customerId = appointment.customerId INNER JOIN user on user.userId = appointment.userId";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
        ResultSetMetaData rsmd = result.getMetaData();
   
      /* int columnsNumber = rsmd.getColumnCount();
        System.out.println("Result Set: " + result.toString());
      */
      
      
      //LocalDateTime ldt = LocalDateTime.now();
       // ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
       // ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        //LocalDateTime ldtIn = utczdt.toLocalDateTime();
        
        //ZonedDateTime zdtOut = ldtIn.atZone(ZoneId.of("UTC"));
        //ZonedDateTime zdtOutToLocalTZ = zdtOut.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        //LocalDateTime ldtOutFinal = zdtOutToLocalTZ.toLocalDateTime();
                
         while(result.next()) {
               Appointment appointment = new Appointment(); 
               appointment.setAppointmentId(result.getInt("appointment.appointmentId"));
               appointment.setCustomerId(result.getInt("appointment.customerId"));
               appointment.setCustomerName(result.getString("customer.customerName"));
               appointment.setConsultantName(result.getString("user.userName"));
               appointment.setConsultantId(result.getInt("appointment.userId"));
               appointment.setAppointmentType(result.getString("appointment.type"));
               appointment.setAppointmentTitle(result.getString("appointment.title"));
               appointment.setAppointmentDate(dateFormatter.format(result.getTimestamp("appointment.start").toLocalDateTime().toLocalDate()));
               LocalDateTime startTime = (result.getTimestamp("appointment.start").toLocalDateTime());
               ZonedDateTime startZdt = ZonedDateTime.of(startTime, ZoneId.of("UTC"));
               ZonedDateTime utcStart = startZdt.withZoneSameInstant(ZoneOffset.systemDefault());
               LocalDateTime startOut = utcStart.toLocalDateTime(); 
               LocalDateTime endTime = (result.getTimestamp("appointment.end").toLocalDateTime());
               ZonedDateTime endZdt = ZonedDateTime.of(endTime, ZoneId.of("UTC"));
               ZonedDateTime utcEnd = endZdt.withZoneSameInstant(ZoneOffset.systemDefault());
               LocalDateTime endOut = utcEnd.toLocalDateTime();
               appointment.setAppointmentStart(startOut.toLocalTime().toString());
               appointment.setAppointmentEnd(endOut.toLocalTime().toString());
               //ZonedDateTime zdt = result.getTimestamp("appointment.end").toLocalDateTime().atZone(ZoneId.of(ZoneId.systemDefault().toString()));
               
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
        Timestamp todayTS = Timestamp.valueOf(now);
        if (length.equals("week")) { 
        filter = now.plusWeeks(1);
        }
        else if (length.equals("month")) {
        filter = now.plusMonths(1);
        }
        Timestamp filterTS = Timestamp.valueOf(filter);
        
        ObservableList<Appointment> appointments=FXCollections.observableArrayList();
        String query = "SELECT customer.customerName, user.userName, appointment.type, appointment.title, appointment.start, appointment.end FROM appointment INNER JOIN customer ON customer.customerId = appointment.customerId INNER JOIN user on user.userId = appointment.userId WHERE appointment.start <= ? AND appointment.start >= ?";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setTimestamp(1, filterTS);
        ps.setTimestamp(2, todayTS);
        ps.execute();
        ResultSet result = ps.getResultSet();
                
         while(result.next()) {
               Appointment appointment = new Appointment(); 
               appointment.setCustomerName(result.getString("customer.customerName"));
               appointment.setConsultantName(result.getString("user.userName"));
               appointment.setAppointmentType(result.getString("appointment.type"));
               appointment.setAppointmentTitle(result.getString("appointment.title"));
               appointment.setAppointmentDate(dateFormatter.format(result.getTimestamp("appointment.start").toLocalDateTime().toLocalDate()));
               appointment.setAppointmentStart(ZonedDateTime.of(result.getTimestamp("appointment.start").toLocalDateTime(), ZoneId.systemDefault()).toString());
               appointment.setAppointmentEnd(result.getTimestamp("appointment.end").toLocalDateTime().toLocalTime().toString());
               
               appointments.add(appointment);
               foundAppts = result.getRow();
               System.out.println("Found an appointment in the filter: " + appointment);
         }
        return appointments;
    }
    public static ObservableList<Appointment> getAppointmentToModify(int apptId) throws SQLException{
        ObservableList<Appointment> appointments=FXCollections.observableArrayList();
        String query = "SELECT appointment.appointmentId, customer.customerName, user.userName, appointment.userId, appointment.type, appointment.title, appointment.start, appointment.end FROM appointment INNER JOIN customer ON customer.customerId = appointment.customerId INNER JOIN user on user.userId = appointment.userId WHERE appointmentId = ?";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setInt(1, apptId);
        ps.execute();
        ResultSet result = ps.getResultSet();
                
         while(result.next()) {
               Appointment appointment = new Appointment(); 
               appointment.setAppointmentId(result.getInt("appointment.appointmentId"));
               appointment.setCustomerName(result.getString("customer.customerName"));
               appointment.setConsultantName(result.getString("user.userName"));
               appointment.setConsultantId(result.getInt("appointment.userId"));
               appointment.setAppointmentType(result.getString("appointment.type"));
               appointment.setAppointmentTitle(result.getString("appointment.title"));
               appointment.setAppointmentDate(dateFormatter.format(result.getTimestamp("appointment.start").toLocalDateTime().toLocalDate()));
               appointment.setAppointmentStart(ZonedDateTime.of(result.getTimestamp("appointment.start").toLocalDateTime(), ZoneId.systemDefault()).toString());
               appointment.setAppointmentEnd(result.getTimestamp("appointment.end").toLocalDateTime().toLocalTime().toString());
               
               
               appointment.setAppointmentStart(result.getTimestamp("appointment.start").toLocalDateTime().toLocalTime().toString());
               appointments.add(appointment);
               System.out.println("Consultant ID from getApptToModify: " + result.getInt("appointment.userId"));
             
             //System.out.println("Appointment: " + appointment);
             /*for (int i = 1; i <= columnsNumber; i++) {
           if (i > 1) System.out.print(",  ");
           String columnValue = result.getString(i);
           System.out.print(columnValue + " " + rsmd.getColumnName(i));
       }
       System.out.println("");
*/
         }
         System.out.println("Consultant ID from getApptToModify: " + result.getInt("appointment.userId"));
        return appointments;
    }
    
    
    //public static Boolean alertableAppointments() {
    
    public static boolean getAppointmentsAlert(String userName) throws SQLException {
           boolean appointmentAlert = false; 
           LocalDateTime todayLDT = Timestamp.valueOf(now).toLocalDateTime();
           ZonedDateTime locZdt = ZonedDateTime.of(todayLDT, ZoneId.systemDefault());
           ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
           LocalDateTime ldtOut = utcZdt.toLocalDateTime();
           Timestamp convertedTs = Timestamp.valueOf(ldtOut);
           Timestamp fifteenAfter = new Timestamp(convertedTs.getTime() + 900000);
           String query = "SELECT appointment.title FROM appointment INNER JOIN user ON appointment.userId = user.userId WHERE user.userName = ? AND appointment.start BETWEEN ? AND ?";
           DBQuery.setPreparedStatement(query, DBConnection.getConnection());
           ps = DBQuery.getPreparedStatement();
           ps.setString(1, userName);
           ps.setTimestamp(2, convertedTs);
           ps.setTimestamp(3, fifteenAfter);
           ps.execute();
           ResultSet result = ps.getResultSet();
           if (result.next()) {
               appointmentAlert = true;
           }
           return appointmentAlert;
    }
    
    public static Timestamp convertedDateTime(LocalDate date, String hour, String minute) {
        String dateString = date.toString() + " " + hour + ":" + minute + ":00"; 
        LocalDateTime ldt = LocalDateTime.parse(dateString,inputFormatter);
        ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime ldtOut = utcZdt.toLocalDateTime();
        Timestamp convertedTs = Timestamp.valueOf(ldtOut);
        return convertedTs;
    }
    
    public static void addAppointment(int custId, int userId, String apptType, String apptTitle, LocalDate apptDate, String startHour, String startMinute, String endHour, String endMinute) throws SQLException { 
        String query = "INSERT INTO appointment (customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?,?,?,'Not Needed', 'Not Needed', 'Not Needed',?,'Not Needed',?,?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP,?)";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setInt(1, custId);
        ps.setInt(2, userId);
        ps.setString(3, apptTitle);
        ps.setString(4, apptType);
        ps.setTimestamp(5, convertedDateTime(apptDate, startHour, startMinute), cal);
        ps.setTimestamp(6, convertedDateTime(apptDate, endHour, endMinute), cal);
        ps.setString(7, UserDAO.currentUser);
        ps.setString(8, UserDAO.currentUser);
        ps.executeUpdate();
    }
    
    public static void modifyAppointment(int apptId, int custId, int userId, String apptType, String apptTitle, LocalDate apptDate, String startHour, String startMinute, String endHour, String endMinute) throws SQLException {
        String query = "UPDATE appointment SET customerId = ?, userId = ?, title = ?, description = 'Not Needed', location = 'Not Needed', contact = 'Not Needed', type = ?, url = 'Not Needed', start = ?, end = ?, lastUpdate = CURRENT_TIMESTAMP, lastUpdateBy = ? WHERE appointmentId = ?";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setInt(1, custId);
        ps.setInt(2, userId);
        ps.setString(3, apptTitle);
        ps.setString(4, apptType);
        ps.setTimestamp(5, convertedDateTime(apptDate, startHour, startMinute));
        ps.setTimestamp(6, convertedDateTime(apptDate, endHour, endMinute));
        ps.setString(7, UserDAO.currentUser);
        ps.setInt(8, apptId);
        ps.executeUpdate();
    }
    
    public static void deleteAppointment(int apptId) throws SQLException {
        String query = "DELETE FROM appointment WHERE appointmentId = ?";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setInt(1, apptId);
        ps.executeUpdate();
    }
    
    public static ObservableList<Appointment> getAppointmentsByMonth() throws SQLException {
        LocalDateTime filter = now.plusMonths(1);
        Timestamp filterTS = Timestamp.valueOf(filter);
        Timestamp todayTS = Timestamp.valueOf(now);
        String totalApptQuery = "SELECT * FROM appointment WHERE appointment.start <= ? AND appointment.start >= ?";
        DBQuery.setPreparedStatement(totalApptQuery, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setTimestamp(1, filterTS);
        ps.setTimestamp(2, todayTS);
        ps.execute();
        ResultSet totalAppts = ps.getResultSet();
        totalAppts.last();
        allApptsCount = totalAppts.getRow();
        
        ObservableList<Appointment> appointmentTypes=FXCollections.observableArrayList();
        String query = "SELECT DISTINCT appointment.type FROM appointment";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
        
        while(result.next()) {
            Appointment appointment = new Appointment();
            appointment.setAppointmentType(result.getString("appointment.type"));
            String countQuery = "SELECT * FROM appointment WHERE appointment.type = ? AND appointment.start <= ? AND appointment.start >= ?";
            DBQuery.setPreparedStatement(countQuery, DBConnection.getConnection());
            ps = DBQuery.getPreparedStatement();
            ps.setString(1, result.getString("appointment.type"));
            ps.setTimestamp(2, filterTS);
            ps.setTimestamp(3, todayTS);
            ps.execute();
            ResultSet count = ps.getResultSet();
            count.last();
            appointmentTypeCount = count.getRow();
            appointment.setAppointmentTypeCount(String.valueOf(appointmentTypeCount));
            appointment.setTotalApptsCount(String.valueOf(appointmentTypeCount / allApptsCount));
            System.out.println("Total appts count: " + allApptsCount);
            System.out.println("Appointment type and count: " + result.getString("appointment.type") + " count: " + appointmentTypeCount);
            appointmentTypes.add(appointment);
        }
        return appointmentTypes;
    }
    
    public static String validateApptTime(int userId, LocalDate apptDate, String startHour, String startMinute, String endHour, String endMinute) throws SQLException {
        String validTime = "true";
        String query = "SELECT * FROM appointment WHERE appointment.userId = ? AND ? BETWEEN appointment.start AND appointment.end";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setInt(1, userId);
        ps.setTimestamp(2, convertedDateTime(apptDate, startHour, startMinute), cal);
        ps.execute();
        ResultSet result = ps.getResultSet();
        if (result.next()) {
            validTime = "overlap";
        }
        
        if (Integer.parseInt(startHour) > 17 || (Integer.parseInt(endHour) > 18)) {
            validTime = "outsideHours";
        }
        return validTime;
    }
    
    
    public static String formattedTime(String appointmentDate, String type) {
        String formattedTime = null;
        if ("hour".equals(type)) {
            formattedTime = appointmentDate.substring(0, 2);
        }
        else if ("minute".equals(type)) {
            formattedTime = appointmentDate.substring(3, 5);
        }
        return formattedTime;
    }
}
