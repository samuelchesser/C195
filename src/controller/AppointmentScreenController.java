/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AppointmentDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Appointment;

/**
 * FXML Controller class
 *
 * @author schesser
 */
public class AppointmentScreenController implements Initializable {

    @FXML
    private Label appointmentsScreenTitle;
    @FXML
    private RadioButton allAppointmentsRadio;
    @FXML
    private ToggleGroup timeSpan;
    @FXML
    private RadioButton thirtyDaysRadio;
    @FXML
    private RadioButton sevenDaysRadio;
    @FXML
    private TableView<Appointment> appointmentsTableView;
    @FXML
    private TableColumn<Appointment, String> customerColumn;
    @FXML
    private TableColumn<Appointment, String> consultantColumn;
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    @FXML
    private TableColumn<Appointment, String> titleColumn;
    @FXML
    private TableColumn<Appointment, String> dateColumn;
    @FXML
    private TableColumn<Appointment, String> startColumn;
    @FXML
    private TableColumn<Appointment, String> endColumn;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private Button modifyAppointmentButton;
    @FXML
    private Button viewCustomersButton;
    @FXML
    private Button viewReportsButton;
    @FXML
    private Button deleteAppointment;
    @FXML
    private Button exitButton;


    @FXML
    private void showNewAppointmentScreen(ActionEvent event) throws IOException {
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/AddAppointmentScreen.fxml"));
        Scene scene = new Scene(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showModifyAppointmentScreen(ActionEvent event) throws IOException {
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/AddAppointmentScreen.fxml"));
        Scene scene = new Scene(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showCustomersScreen(ActionEvent event) throws IOException {
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/CustomersScreen.fxml"));
        Scene scene = new Scene(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showReportsScreen(ActionEvent event) {
    }

    @FXML
    private void deleteAppointmentHandler(ActionEvent event) {
    }

    @FXML
    private void exitButtonHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit CAMP?");
        alert.setContentText("Are you sure you want to quit the Appointment Management Application?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            System.out.println("User canceled, returning to application.");
        }
    }
    
        public void populateApptsTable() throws SQLException {
        appointmentsTableView.setItems(AppointmentDAO.getAppointments());
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       customerColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProp());
       consultantColumn.setCellValueFactory(cellData -> cellData.getValue().consultantNameProp());
       typeColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentTypeProp());
       titleColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentTitleProp());
       dateColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentDateProp());
       startColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentStartProp());
       endColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentEndProp());
        try {
            populateApptsTable();
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentScreenController.class.getName()).log(Level.WARNING, null, ex);
        }
       
    }   
    
}
