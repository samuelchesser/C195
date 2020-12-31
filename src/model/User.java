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
    private final SimpleStringProperty kickoffCount;
    private final SimpleStringProperty checkinCount;
    private final SimpleStringProperty retroCount;
    private final SimpleStringProperty launchCount;
    private final SimpleStringProperty trainingCount;
    private final SimpleStringProperty totalCount;
  
    public User () {
        userId = new SimpleIntegerProperty();
        userName = new SimpleStringProperty();
        password = new SimpleStringProperty();
        kickoffCount = new SimpleStringProperty();
        checkinCount = new SimpleStringProperty();
        retroCount = new SimpleStringProperty();
        trainingCount = new SimpleStringProperty();
        launchCount = new SimpleStringProperty();        
        totalCount = new SimpleStringProperty();
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
    
    public String getKickoffCount() {
        return this.kickoffCount.get();
    }

    public StringProperty kickoffCountProp() { return kickoffCount; }

    public void setKickoffCount(String kickoffCount) { this.kickoffCount.set(kickoffCount);}
    
    public String getCheckinCount() {
        return this.checkinCount.get();
    }

    public StringProperty checkinCountProp() { return checkinCount; }

    public void setCheckinCount(String checkinCount) { this.checkinCount.set(checkinCount);}
    
    public String getRetroCount() {
        return this.retroCount.get();
    }

    public StringProperty retroCountProp() { return retroCount; }

    public void setRetroCount(String retroCount) { this.retroCount.set(retroCount);}
    
    public String getLaunchCount() {
        return this.launchCount.get();
    }

    public StringProperty launchCountProp() { return launchCount; }

    public void setLaunchCount(String launchCount) { this.launchCount.set(launchCount);}
    
    public String getTrainingCount() {
        return this.trainingCount.get();
    }

    public StringProperty trainingCountProp() { return trainingCount; }

    public void setTrainingCount(String trainingCount) { this.trainingCount.set(trainingCount);}
    
    public String getTotalCount() {
        return this.totalCount.get();
    }

    public StringProperty totalCountProp() { return totalCount; }

    public void setTotalCount(String totalCount) { this.totalCount.set(totalCount);}

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", password=" + password + '}';
    }
    

}
