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
public class AddAddressScreenController implements Initializable {

    @FXML
    private Label addCustomerScreenTitle;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private TextField addressTextField;
    @FXML
    private ComboBox<String> cityComboBox;
    @FXML
    private Label addressLabel1;
    @FXML
    private Label addressLabel11;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField zipTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addAddressButton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            ObservableList<Customer> customers = CustomerDAO.getCities();
            customers.forEach((customer) -> {
            cityComboBox.getItems().addAll(customer.getCityId() + " " + customer.getCustomerCity());
        });
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void saveAddress(ActionEvent event) throws SQLException, IOException {
        String address = addressTextField.getText();
        int cityId = Integer.parseInt(cityComboBox.getValue().substring(0,1));
        String zip = zipTextField.getText();
        String phone = phoneTextField.getText();
        CustomerDAO.addAddress(address, cityId, zip, phone);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setContentText("Address " + address + " successfully added!");
        alert.showAndWait();
        
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/CustomersScreen.fxml"));
        Scene scene = new Scene(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void displayAppointmentsScreen(ActionEvent event) throws IOException {
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/AppointmentScreen.fxml"));
        Scene scene = new Scene(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}
