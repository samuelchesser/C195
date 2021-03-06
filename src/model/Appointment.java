/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
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
    private final IntegerProperty customerId;
    private final StringProperty consultantName;
    private final IntegerProperty consultantId;
    private final StringProperty appointmentType;
    private final StringProperty appointmentTitle;
    private LocalDate appointmentDate;
    private LocalTime appointmentStartTime;
    private LocalTime appointmentEndTime;
    private final StringProperty appointmentDateString;
    private final StringProperty appointmentStartString;
    private final StringProperty appointmentEndString;
    private final StringProperty appointmentTypeCount;
    private final StringProperty totalApptsCount;

    
    public Appointment() {
        appointmentId = new SimpleIntegerProperty();
        customerName = new SimpleStringProperty();
        customerId = new SimpleIntegerProperty();
        consultantName = new SimpleStringProperty();
        consultantId = new SimpleIntegerProperty();
        appointmentType = new SimpleStringProperty();
        appointmentTitle = new SimpleStringProperty();
        appointmentDateString = new SimpleStringProperty();
        appointmentStartString = new SimpleStringProperty();
        appointmentEndString = new SimpleStringProperty();
        appointmentTypeCount = new SimpleStringProperty();
        totalApptsCount = new SimpleStringProperty();
        
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
    
    public int getCustomerId() {
        return this.customerId.get();
    }

    public IntegerProperty customerIdProp() { return customerId; }

    public void setCustomerId(int customerId) { this.customerId.set(customerId);}
    
    public String getConsultantName() {
        return this.consultantName.get();
    }

    public StringProperty consultantNameProp() { return consultantName; }

    public void setConsultantName(String consultantName) { this.consultantName.set(consultantName);}
    
    public int getConsultantId() {
        System.out.println("Getting consultant ID: " + this.consultantId.get());
        return this.consultantId.get();
    }

    public IntegerProperty consultantIdProp() { return consultantId; }

    public void setConsultantId(int consultantId) { this.consultantId.set(consultantId);}
    
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


    public String getAppointmentDate() {
        return this.appointmentDateString.get();
    }

    public StringProperty appointmentDateProp() { return appointmentDateString; }

    public void setAppointmentDate(String appointmentDateString) { this.appointmentDateString.set(appointmentDateString);}

    public String getAppointmentStartTime() {
        return appointmentStartString.get();
    }

   public StringProperty appointmentStartProp() { return appointmentStartString; }

    
    public void setAppointmentStart(String appointmentStartString) { this.appointmentStartString.set(appointmentStartString);}

    public String getAppointmentEndTime() {
           return appointmentEndString.get();
       }

    public StringProperty appointmentEndProp() { return appointmentEndString; }


    public void setAppointmentEnd(String appointmentEndString) { this.appointmentEndString.set(appointmentEndString);}
    
    public String getAppointmentTypeCount() {
        return this.appointmentTypeCount.get();
    }

    public StringProperty appointmentTypeCountProp() { return appointmentTypeCount; }

    public void setAppointmentTypeCount(String appointmentTypeCount) { this.appointmentTypeCount.set(appointmentTypeCount);}
    
    public String getTotalApptsCount() {
        return this.totalApptsCount.get();
    }

    public StringProperty totalApptsCountProp() { return totalApptsCount; }

    public void setTotalApptsCount(String totalApptsCount) { this.totalApptsCount.set(totalApptsCount);}

    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId + ", customerName=" + customerName + ", consultantName=" + consultantName + ", appointmentType=" + appointmentType + ", appointmentTitle=" + appointmentTitle + ", appointmentDate=" + appointmentDate + ", appointmentStartTime=" + appointmentStartTime + ", appointmentEndTime=" + appointmentEndTime + ", appointmentDateString=" + appointmentDateString + ", appointmentStartString=" + appointmentStartString + ", appointmentEndString=" + appointmentEndString + '}';
    }
    
    
}
