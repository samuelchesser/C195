/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AppointmentDAO;
import static DAO.AppointmentDAO.foundAppts;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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

    String filterLength = "";
    
    private static Appointment apptToModify;
    public static int apptToModifyId;
    @FXML
    private void filterSevenDays(ActionEvent event) throws SQLException {
        filterLength = "week";
        AppointmentDAO.filterDays(filterLength);

        if (foundAppts > 0) {
             populateApptsTable("filterDays");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Appointments Within 7 Days");
            alert.setContentText("There are no appointments scheduled in the next 7 days.");
            alert.showAndWait();
        }
        
    }
    
    @FXML
    private void filterThirtyDays(ActionEvent event) throws SQLException {
        filterLength = "month";
        AppointmentDAO.filterDays(filterLength);

        if (foundAppts > 0) {
             populateApptsTable("filterDays");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Appointments Within 30 Days");
            alert.setContentText("There are no appointments scheduled in the next 30 days. Hopefully business picks up!");
            alert.showAndWait();
        }
        
    }
    
    @FXML
    private void showAllAppts(ActionEvent event) throws SQLException {
        populateApptsTable("initial");
        
    }
    
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
        apptToModify = appointmentsTableView.getSelectionModel().getSelectedItem();
        System.out.println("Appt to modify: " + apptToModify);
        System.out.println("Appt to modify ID: " + apptToModifyId);
        if (apptToModify == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No appointment selected");
            alert.setContentText("You need to select an appointment to go to the modify screen. Please select an appointment.");
            alert.showAndWait();
        }
        else {
        apptToModifyId = apptToModify.getAppointmentId();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddAppointmentScreen.fxml"));
        Parent appointment = (Parent) loader.load();
        Scene scene = new Scene(appointment);
        AddAppointmentScreenController addController=loader.getController();
        addController.setModifiedApptFields(apptToModify);
        addController.context = "modify";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    }

    @FXML
    private void showCustomersScreen(ActionEvent event) throws IOException {
        Parent customer = FXMLLoader.load(getClass().getResource("/view/CustomersScreen.fxml"));
        Scene scene = new Scene(customer);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showReportsScreen(ActionEvent event) throws IOException {
        Parent reports = FXMLLoader.load(getClass().getResource("/view/TypePercentageReportScreen.fxml"));
        Scene scene = new Scene(reports);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteAppointmentHandler(ActionEvent event) throws SQLException {
        apptToModify = appointmentsTableView.getSelectionModel().getSelectedItem();
        System.out.println("Appt to delete: " + apptToModify);
        System.out.println("Appt to delete ID: " + apptToModifyId);
        if (apptToModify == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No appointment selected");
            alert.setContentText("Please select an appointment to delete first.");
            alert.showAndWait();
        }
         else {
            apptToModifyId = apptToModify.getAppointmentId();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete appointment?");
            alert.setContentText("Clicking OK will delete the appointment. This can't be undone!");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                AppointmentDAO.deleteAppointment(apptToModifyId);
                populateApptsTable("initial");
            }
        }
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
    
        public void populateApptsTable(String context) throws SQLException {
            if (context.equals("initial")) {
                appointmentsTableView.setItems(AppointmentDAO.getAppointments());
            }
            else if (context.equals("filterDays")) {
                appointmentsTableView.setItems(AppointmentDAO.filterDays(filterLength));    
            }
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
            populateApptsTable("initial");
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentScreenController.class.getName()).log(Level.WARNING, null, ex);
        }
       
    }   
    
}
