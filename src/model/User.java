/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author schesser
 */
public final class User {
    public static User activeUser;
    private final SimpleIntegerProperty userId = new SimpleIntegerProperty();
    private final SimpleStringProperty userName = new SimpleStringProperty();
    private final SimpleStringProperty password = new SimpleStringProperty();
    
    public User (int currentId, String currentName, String currentPassword) {
        setUserId(currentId);
        setUserName(currentName);
        setPassword(currentPassword);
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }
    public int getUserId() {
        return userId.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }
    public String getUserName() {
        return userName.get();
    }
    
     public void setPassword(String password) {
        this.password.set(password);
    }
    public String getPassword() {
        return password.get();
    }
    

}
