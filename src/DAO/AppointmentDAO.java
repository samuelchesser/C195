/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    private static ObservableList<Appointment> getAppointments() throws SQLException{
        ObservableList<Appointment> appointments=FXCollections.observableArrayList();
        String query = "SELECT customer.customerName, user.userName, appointment.type, appointment.title, appointment.start, appointment.end FROM appointment INNER JOIN customer ON customer.id = appointment.customerId INNER JOIN user on user.userId = appointment.userId";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
        Appointment appointment = new Appointment();         
         while(result.next()) {
             appointments.add(appointment);
         }
        return appointments;
    }
    
}
