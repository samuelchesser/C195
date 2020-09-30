/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AppointmentDAO;
import DAO.CustomerDAO;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
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
    private void showAddCustomerScreen(ActionEvent event) throws IOException {
        Parent customers = FXMLLoader.load(getClass().getResource("/view/AddCustomerScreen.fxml"));
        Scene scene = new Scene(customers);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showModifyCustomerScreen(ActionEvent event) throws IOException {
        Parent customers = FXMLLoader.load(getClass().getResource("/view/AddCustomerScreen.fxml"));
        Scene scene = new Scene(customers);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteCustomer(ActionEvent event) throws SQLException {
        Customer customer = customersTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm customer deletion");
        alert.setContentText("Please confirm you want to delete the following customer: " + customer.getCustomerName());
        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.get() == ButtonType.OK) {
          //  deleteCustomer(customer);
            populateCustomersTable();
        }
    }

    @FXML
    private void showAppointmentScreen(ActionEvent event) {
    }
    
}
