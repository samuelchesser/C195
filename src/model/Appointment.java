/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author schesser
 */

//Watch date/time conversion and see if you need to convert calendar stuff to string
public class Appointment {
    private final IntegerProperty appointmentId;
    private final StringProperty customerName;
    private final StringProperty consultantName;
    private final StringProperty appointmentType;
    private final StringProperty appointmentTitle;
    private Calendar appointmentDate;
    private Calendar appointmentStartTime;
    private Calendar appointmentEndTime;
    private final StringProperty appointmentDateString;
    private final StringProperty appointmentStartString;
    private final StringProperty appointmentEndString;

    
    public Appointment() {
        appointmentId = new SimpleIntegerProperty();
        customerName = new SimpleStringProperty();
        consultantName = new SimpleStringProperty();
        appointmentType = new SimpleStringProperty();
        appointmentTitle = new SimpleStringProperty();
        appointmentDateString = new SimpleStringProperty();
        appointmentStartString = new SimpleStringProperty();
        appointmentEndString = new SimpleStringProperty();
        
    }
    
    public int getAppointmentId() {
        return this.appointmentId.get();
    }

    public IntegerProperty appointmentIdProp() { return appointmentId; }

    public void setAppointmentId(int appointmentId) { this.appointmentId.set(appointmentId);}
    
    public String getCustomerName() {
        return this.customerName.get();
    }

    public StringProperty customerNameProp() { return customerName; }

    public void setCustomerName(String customerName) { this.customerName.set(customerName);}
    
    public String getConsultantName() {
        return this.consultantName.get();
    }

    public StringProperty consultantNameProp() { return consultantName; }

    public void setConsultantName(String consultantName) { this.consultantName.set(consultantName);}
    
    public String getAppointmentType() {
        return this.appointmentType.get();
    }

    public StringProperty appointmentTypeProp() { return appointmentType; }

    public void setAppointmentType(String appointmentType) { this.appointmentType.set(appointmentType);}
    
    public String getAppointmentTitle() {
        return this.appointmentTitle.get();
    }

    public StringProperty appointmentTitleProp() { return appointmentTitle; }

    public void setAppointmentTitle(String appointmentTitle) { this.appointmentTitle.set(appointmentTitle);}


    public Calendar getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Calendar appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Calendar getAppointmentStartTime() {
        return appointmentStartTime;
    }

    public void setAppointmentStartTime(Calendar appointmentStartTime) {
        this.appointmentStartTime = appointmentStartTime;
    }

    public Calendar getAppointmentEndTime() {
        return appointmentEndTime;
    }

    public void setAppointmentEndTime(Calendar appointmentEndTime) {
        this.appointmentEndTime = appointmentEndTime;
    }
    
    public String getAppointmentDateString() {
        return this.appointmentDateString.get();
    }

    public StringProperty appointmentDateProp() { return appointmentDateString; }

    public void setAppointmentDateString(String appointmentDateString) { this.appointmentDateString.set(appointmentDateString);}
    
    public String getAppointmentStartString() {
        return this.appointmentStartString.get();
    }

    public StringProperty appointmentStartProp() { return appointmentStartString; }

    public void setAppointmentDtartString(String appointmentStartString) { this.appointmentStartString.set(appointmentStartString);}
    
    public String getAppointmentEndString() {
        return this.appointmentEndString.get();
    }

    public StringProperty appointmentEndProp() { return appointmentEndString; }

    public void setAppointmentEndString(String appointmentEndString) { this.appointmentEndString.set(appointmentEndString);}
    
}
