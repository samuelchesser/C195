/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import javafx.collections.FXCollections;
import model.Appointment;
import model.Customer;
import utils.DBConnection;
import utils.DBQuery;

/**
 *
 * @author schesser
 */
public class CustomerDAO {
    static PreparedStatement ps;
    
    public static ObservableList<Customer> getCustomers() throws SQLException{
        ObservableList<Customer> customers=FXCollections.observableArrayList();
        String query = "SELECT cust.customerId, cust.customerName, a.addressId, a.address, city.city, a.postalCode, a.phone FROM customer cust INNER JOIN address a ON a.addressId = cust.addressId INNER JOIN city on a.cityId = city.cityId";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
                
         while(result.next()) {
               Customer customer = new Customer();
               customer.setcustomerId(result.getInt("cust.customerId"));
               customer.setCustomerName(result.getString("cust.customerName"));
               customer.setAddressId(result.getInt("a.addressId"));
               customer.setCustomerAddress(result.getString("a.address"));
               customer.setCustomerCity(result.getString("city.city"));
               customer.setCustomerZip(result.getString("a.postalCode"));
               customer.setCustomerPhone(result.getString("a.phone"));
               customers.add(customer);
          
       }
        return customers;
    }
    
    public static ObservableList<Customer> getCities() throws SQLException{
        ObservableList<Customer> customers=FXCollections.observableArrayList();
        String query = "SELECT DISTINCT city.cityId, city.city FROM city";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
                
         while(result.next()) {
               Customer customer = new Customer();
               customer.setCustomerCity(result.getString("city.city"));
               customer.setCityId(result.getInt("city.cityid"));
               customers.add(customer);
          
       }
        return customers;
    }
    
    public static void addCustomer(String custName, int address) throws SQLException {
        String query = "INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?,?,1,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP,?)";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setString(1, custName);
        ps.setInt(2, address);
        ps.setString(3, UserDAO.currentUser);
        ps.setString(4, UserDAO.currentUser);
        ps.executeUpdate();
        System.out.println("Added a customer!");
    }
    
    public static void modifyCustomer(String custName, int address, int custId) throws SQLException {
        String query = "UPDATE customer SET  customerName = ?, addressId = ?, active = 1, lastUpdate = CURRENT_TIMESTAMP, lastUpdateBy = ? WHERE customerId = ?";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setString(1, custName);
        ps.setInt(2, address);
        ps.setString(3, UserDAO.currentUser);
        ps.setInt(4, custId);
        ps.executeUpdate();
        System.out.println("Added a customer!");
    }
    
    public static void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM customer WHERE customerId = ?";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setInt(1, customerId);
        ps.executeUpdate();
    }
    
    public static void addAddress(String address, int cityId, String zip, String phone) throws SQLException {
        String query = "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?, 'NOT NEEDED', ?,?,?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP,?)";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setString(1, address);
        ps.setInt(2, cityId);
        ps.setString(3, zip);
        ps.setString(4, phone);
        ps.setString(5, UserDAO.currentUser);
        ps.setString(6, UserDAO.currentUser);
        ps.executeUpdate();
        System.out.println("Added an address!");
    }
    
    public static ObservableList<Customer> getAddresses() throws SQLException{
        ObservableList<Customer> customers=FXCollections.observableArrayList();
        String query = "SELECT address.addressId, address.address, city.city FROM address INNER JOIN city ON address.cityId = city.cityId";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
                
         while(result.next()) {
               Customer customer = new Customer();
               customer.setAddressId(result.getInt("address.addressId"));
               customer.setCustomerAddress(result.getString("address.address"));
               customer.setCustomerCity(result.getString("city.city"));
               customers.add(customer);
       }
        return customers;
    }
    
}
