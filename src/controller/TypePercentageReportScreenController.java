/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    
}
