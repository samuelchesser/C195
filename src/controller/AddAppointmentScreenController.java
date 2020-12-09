/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AppointmentDAO;
import DAO.CustomerDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import model.User;

/**
 * FXML Controller class
 *
 * @author schesser
 */
public class AddAppointmentScreenController implements Initializable {

    @FXML
    private Label addAppointmentScreenTitle;
    @FXML
    private ComboBox<String> customerComboBox;
    @FXML
    private ComboBox<String> consultantComboBox;
    @FXML
    private ComboBox<String> apptTypeComboBox;
    @FXML
    private TextField apptTitleTextField;
    @FXML
    private DatePicker dateField;
    @FXML
    private ComboBox<String> startMinuteComboBox;
    @FXML
    private ComboBox<String> startHourComboBox;
    @FXML
    private ComboBox<String> endHourComboBox;
    @FXML
    private ComboBox<String> endMinuteComboBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<Customer> customers = CustomerDAO.getCustomers();
            customers.forEach((customer) -> {
            customerComboBox.getItems().addAll(customer.getCustomerName());
        });
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ObservableList<User> users = UserDAO.getUsers();
            users.forEach((user) -> {
            consultantComboBox.getItems().addAll(user.getUserName());
        });
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ObservableList<Appointment> appointments = AppointmentDAO.getAppointments();
            appointments.forEach((appointment) -> {
            apptTypeComboBox.getItems().addAll(appointment.getAppointmentType());
        });
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        startHourComboBox.getItems().addAll(
            "01","02","03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" ); 
        startMinuteComboBox.getItems().addAll(
            "00","15","30", "45");
        endHourComboBox.getItems().addAll(
            "01","02","03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" ); 
        endMinuteComboBox.getItems().addAll(
            "00","15","30", "45"); 
    }

    @FXML
    private void addAppointmentHandler(ActionEvent event) {
    }

    @FXML
    private void backToApptScreen(ActionEvent event) throws IOException {
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/AppointmentScreen.fxml"));
        Scene scene = new Scene(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showCustomers(ActionEvent event) {
    }

    @FXML
    private void showConsultants(ActionEvent event) {
    }

    @FXML
    private void showAppointmentTypes(ActionEvent event) {
    }

    @FXML
    private void showMinutes(ActionEvent event) {
    }

    @FXML
    private void showHours(ActionEvent event) {
    }
    
}
