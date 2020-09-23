/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AppointmentDAO;
import DAO.CustomerDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Customer;

/**
 * FXML Controller class
 *
 * @author schesser
 */
public class CustomersScreenController implements Initializable {

    @FXML
    private Label customersScreenTitle;
    @FXML
    private TableView<Customer> customersTableView;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;
    @FXML
    private TableColumn<Customer, String> cityColumn;
    @FXML
    private TableColumn<Customer, String> zipColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;

    
    public void populateCustomersTable() throws SQLException {
        customersTableView.setItems(CustomerDAO.getCustomers());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       nameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProp());
       addressColumn.setCellValueFactory(cellData -> cellData.getValue().customerAddressProp());
       cityColumn.setCellValueFactory(cellData -> cellData.getValue().customerCityProp());
       zipColumn.setCellValueFactory(cellData -> cellData.getValue().customerZipProp());
       phoneColumn.setCellValueFactory(cellData -> cellData.getValue().customerPhoneProp());
        try {
            populateCustomersTable();
        } catch (SQLException ex) {
            Logger.getLogger(CustomersScreenController.class.getName()).log(Level.WARNING, null, ex);
        }
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
