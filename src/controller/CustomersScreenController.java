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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author schesser
 */
public class CustomersScreenController implements Initializable {

    @FXML
    private Label customersScreenTitle;
    @FXML
    private TableView<?> customersTableView;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> addressColumn;
    @FXML
    private TableColumn<?, ?> cityColumn;
    @FXML
    private TableColumn<?, ?> zipCOlumn;
    @FXML
    private TableColumn<?, ?> phoneColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showAddCustomerScreen(ActionEvent event) {
    }

    @FXML
    private void showModifyCustomerScreen(ActionEvent event) {
    }

    @FXML
    private void deleteCustomer(ActionEvent event) {
    }

    @FXML
    private void showAppointmentScreen(ActionEvent event) {
    }
    
}
