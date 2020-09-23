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

/**
 *
 * @author schesser
 */
public class Customer {
    private final IntegerProperty customerId;
    private final StringProperty customerName;
    private final StringProperty customerAddress;
    private final StringProperty customerCity;
    private final StringProperty customerZip;
    private final StringProperty customerPhone;


    
    public Customer() {
        customerId = new SimpleIntegerProperty();
        customerName = new SimpleStringProperty();
        customerAddress = new SimpleStringProperty();
        customerCity = new SimpleStringProperty();
        customerZip = new SimpleStringProperty();
        customerPhone = new SimpleStringProperty();

    }
    
    public int getCustomerId() {
        return this.customerId.get();
    }

    public IntegerProperty customerIdProp() { return customerId; }

    public void setcustomerId(int customerId) { this.customerId.set(customerId);}
    
    public String getCustomerName() {
        return this.customerName.get();
    }

    public StringProperty customerNameProp() { return customerName; }

    public void setCustomerName(String customerName) { this.customerName.set(customerName);}
    
    public String getCustomerAddress() {
        return this.customerAddress.get();
    }

    public StringProperty customerAddressProp() { return customerAddress; }

    public void setCustomerAddress(String customerAddress) { this.customerAddress.set(customerAddress);}
    
    public String getCustomerCity() {
        return this.customerCity.get();
    }

    public StringProperty customerCityProp() { return customerCity; }

    public void setCustomerCity(String customerCity) { this.customerCity.set(customerCity);}
    
    public String getCustomerZip() {
        return this.customerName.get();
    }

    public StringProperty customerZipProp() { return customerZip; }

    public void setCustomerZip(String customerZip) { this.customerZip.set(customerZip);}
    
    public String getCustomerPhone() {
        return this.customerPhone.get();
    }

    public StringProperty customerPhoneProp() { return customerPhone; }

    public void setCustomerPhone(String customerPhone) { this.customerPhone.set(customerPhone);}

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", customerName=" + customerName + ", customerAddress=" + customerAddress + ", customerCity=" + customerCity + ", customerZip=" + customerZip + ", customerPhone=" + customerPhone + '}';
    }
    
    
}