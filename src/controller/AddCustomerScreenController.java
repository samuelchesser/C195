/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CustomerDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

/**
 * FXML Controller class
 *
 * @author schesser
 */
public class AddCustomerScreenController implements Initializable {

    @FXML
    private Label addCustomerScreenTitle;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private ComboBox<String> customerAddressComboBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    public String context = "add";
    @FXML
    private Button addAddressButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<Customer> customers = CustomerDAO.getAddresses();
            customers.forEach((customer) -> {
                if (!customerAddressComboBox.getItems().contains(customer.getAddressId() + ": " + customer.getCustomerAddress() + " - " + customer.getCustomerCity())) {
            customerAddressComboBox.getItems().addAll(customer.getAddressId() + ": " + customer.getCustomerAddress() + " - " + customer.getCustomerCity());
                }
        });
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    @FXML
    private void saveCustomer(ActionEvent event) throws SQLException, IOException {
        if ("add".equals(context)) {
        String custName = customerNameTextField.getText();
        int custAddress = Integer.parseInt(customerAddressComboBox.getValue().substring(0,1));
        System.out.println("Customer Name: " + custName);
        System.out.println("Address combo box: " + custAddress);
        CustomerDAO.addCustomer(custName, custAddress);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setContentText("Customer " + custName + " successfully added");
        alert.showAndWait();
        
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/AppointmentScreen.fxml"));
        Scene scene = new Scene(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        }
        else if ("modify".equals(context)) {
        String custName = customerNameTextField.getText();
        int custId = CustomersScreenController.custToModifyId;
        int custAddress = Integer.parseInt(customerAddressComboBox.getValue().substring(0,1));
        System.out.println("Customer Name: " + custName);
        System.out.println("Address combo box: " + custAddress);
        CustomerDAO.modifyCustomer(custName, custAddress, custId);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setContentText("Customer " + custName + " successfully modified!");
        alert.showAndWait();
        
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/AppointmentScreen.fxml"));
        Scene scene = new Scene(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        }
        
    }

    /*
    This main appointment view serves as the fulfillment of requirement I-2 "the schedule for each consultant"
    Each consultant can see their and other schedules, and also filter to the next 7 and 30 days
    */    
    @FXML
    private void displayAppointmentsScreen(ActionEvent event) throws IOException {
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/AppointmentScreen.fxml"));
        Scene scene = new Scene(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void setModifiedCustFields(Customer customer) {
        System.out.println(customer);
        addCustomerScreenTitle.setText("CAPA: Modify Customer");
        customerAddressComboBox.setValue(customer.getCustomerAddress());
        customerNameTextField.setText(customer.getCustomerName());
    }

    @FXML
    private void showNewAddressScreen(ActionEvent event) throws IOException {
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/AddAddressScreen.fxml"));
        Scene scene = new Scene(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }
    
}
