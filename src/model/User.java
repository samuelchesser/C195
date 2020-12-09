/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//Need to modify to match appointment more. Make sure we aren't breaking logging in if we do change Public User
/**
 *
 * @author schesser
 */
public class User {
    public static User activeUser;
    private final SimpleIntegerProperty userId;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty password;
    
    public User () {
        userId = new SimpleIntegerProperty();
        userName = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }
    
    public int getUserId() {
        return this.userId.get();
    }

    public IntegerProperty userIdProp() { return userId; }

    public void setUserId(int userId) { this.userId.set(userId);}

    public String getUserName() {
        return this.userName.get();
    }

    public StringProperty userNameProp() { return userName; }

    public void setUserName(String userName) { this.userName.set(userName);}
    
    public String getPassword() {
        return this.password.get();
    }

    public StringProperty passwordProp() { return password; }

    public void setPassword(String password) { this.password.set(password);}

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", password=" + password + '}';
    }
    

}
