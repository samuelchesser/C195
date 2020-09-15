/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

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
    private TableView<?> appointmentsTableView;
    @FXML
    private TableColumn<?, ?> customerColumn;
    @FXML
    private TableColumn<?, ?> consultantColumn;
    @FXML
    private TableColumn<?, ?> typeColumn;
    @FXML
    private TableColumn<?, ?> titleColumn;
    @FXML
    private TableColumn<?, ?> dateColumn;
    @FXML
    private TableColumn<?, ?> startColumn;
    @FXML
    private TableColumn<?, ?> endColumn;
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

    
    
       public void populateAppointmentsTable() {
        //appointmentsTableView.setItems(AppointmentDAO.getAppointments());
    }
 

    @FXML
    private void showNewAppointmentScreen(ActionEvent event) {
    }

    @FXML
    private void showModifyAppointmentScreen(ActionEvent event) {
    }

    @FXML
    private void showCustomersScreen(ActionEvent event) {
    }

    @FXML
    private void showReportsScreen(ActionEvent event) {
    }

    @FXML
    private void deleteAppointmentHandler(ActionEvent event) {
    }

    @FXML
    private void exitButtonHandler(ActionEvent event) {
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
}
