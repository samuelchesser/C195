/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AppointmentDAO;
import DAO.UserDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Appointment;
import model.User;

/**
 * FXML Controller class
 *
 * @author schesser
 */
public class TypePercentageReportScreenController implements Initializable {

    @FXML
    private Label reportsScreenTitle;
    @FXML
    private TableView<Appointment> appointmentsByMonthReportTable;
    @FXML
    private TableColumn<Appointment, String> appointmentTypeColumn;
    @FXML
    private TableColumn<Appointment, String> nextMonthCountColumn;
    @FXML
    private TableColumn<Appointment, String> percentColumn;
    @FXML
    private TableView<User> consultantAppointmentsReportTable;
    @FXML
    private TableColumn<User, String> consultantColumn;
    @FXML
    private TableColumn<User, String> kickoffColumn;
    @FXML
    private TableColumn<User, String> checkinColumn;
    @FXML
    private TableColumn<User, String> retroColumn;
    @FXML
    private TableColumn<User, String> launchColumn;
    @FXML
    private TableColumn<User, String> trainingColumn;
    @FXML
    private TableColumn<User, String> totalColumn;
    @FXML
    private Button showAppointmentScreen;
    @FXML
    private Button exitApplication;

    /**
     * Initializes the controller class.
     */
    
    public void populateApptTypeByMonthTable() throws SQLException {
        appointmentsByMonthReportTable.setItems(AppointmentDAO.getAppointmentsByMonth());
    }
    public void populateConsultantAppointmentsReportTable() throws SQLException {
        consultantAppointmentsReportTable.setItems(UserDAO.getConsultantMonthlyApts());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointmentTypeColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentTypeProp());
        nextMonthCountColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentTypeCountProp());
        percentColumn.setCellValueFactory(cellData -> cellData.getValue().totalApptsCountProp());
        consultantColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProp());
        kickoffColumn.setCellValueFactory(cellData -> cellData.getValue().kickoffCountProp());
        checkinColumn.setCellValueFactory(cellData -> cellData.getValue().checkinCountProp());
        retroColumn.setCellValueFactory(cellData -> cellData.getValue().retroCountProp());
        launchColumn.setCellValueFactory(cellData -> cellData.getValue().launchCountProp());
        trainingColumn.setCellValueFactory(cellData -> cellData.getValue().trainingCountProp());
        totalColumn.setCellValueFactory(cellData -> cellData.getValue().totalCountProp());
        try {
            populateApptTypeByMonthTable();
            populateConsultantAppointmentsReportTable();
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentScreenController.class.getName()).log(Level.WARNING, null, ex);
        }
    }    
   

    @FXML
    private void displayAppointmentScreen(ActionEvent event) throws IOException {
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/AppointmentScreen.fxml"));
        Scene scene = new Scene(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void exitApplication(ActionEvent event) {
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
    
}
