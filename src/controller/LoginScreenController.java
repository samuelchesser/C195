/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import DAO.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author schesser
 */
public class LoginScreenController implements Initializable {

    @FXML
    private Label loginScreenTitle;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;
    
    //If this isn't working, channge file names to rb_ru and this to Languages/rb
    ResourceBundle rb = ResourceBundle.getBundle("Languages/loginScreen", Locale.getDefault());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    void loginButtonHandler(ActionEvent event) throws SQLException, IOException {
       
        String user = usernameTextField.getText();
        String password = passwordTextField.getText();
        
        if (!"".equals(password) && !"".equals(user)) {
            boolean loginSuccessful = UserDAO.attemptLogin(user, password);
            
            if (loginSuccessful) {
               Stage stage = new Stage();
                Parent appointment = FXMLLoader.load(getClass().getResource("/view/AddAppointmentScreen.fxml"));
                Scene scene = new Scene(appointment);
                stage.setScene(scene);
                stage.show();

            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(rb.getString("notFoundError"));
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(rb.getString("blankError"));
            alert.showAndWait();
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
    
}
