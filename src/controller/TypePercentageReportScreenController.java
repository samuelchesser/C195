/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author schesser
 */
public class TypePercentageReportScreenController implements Initializable {

    @FXML
    private Label reportsScreenTitle;
    @FXML
    private TableView<?> appointmentsByMonthReportTable;
    @FXML
    private TableColumn<?, ?> appointmentTypeColumn;
    @FXML
    private TableColumn<?, ?> nextMonthCountColumn;
    @FXML
    private TableColumn<?, ?> percentColumn;
    @FXML
    private TableView<?> consultantAppointmentsReportTable;
    @FXML
    private TableColumn<?, ?> consultantColumn;
    @FXML
    private TableColumn<?, ?> kickoffColumn;
    @FXML
    private TableColumn<?, ?> checkinColumn;
    @FXML
    private TableColumn<?, ?> retroColumn;
    @FXML
    private TableColumn<?, ?> launchColumn;
    @FXML
    private TableColumn<?, ?> trainingColumn;
    @FXML
    private TableColumn<?, ?> totalColumn;
    @FXML
    private Button showAppointmentScreen;
    @FXML
    private Button exitApplication;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
