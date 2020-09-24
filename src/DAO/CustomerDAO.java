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
        String query = "SELECT cust.customerName, a.address, city.city, a.postalCode, a.phone FROM customer cust INNER JOIN address a ON a.addressId = cust.addressId INNER JOIN city on a.cityId = city.cityId";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet result = ps.getResultSet();
                
         while(result.next()) {
               Customer customer = new Customer(); 
               customer.setCustomerName(result.getString("cust.customerName"));
               customer.setCustomerAddress(result.getString("a.address"));
               customer.setCustomerCity(result.getString("city.city"));
               customer.setCustomerZip(result.getString("a.postalCode"));
               customer.setCustomerPhone(result.getString("a.phone"));
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
        //need to figure out question makes (this is the third mark, but column 5) and setting the createdBy and updateBy to the logged in user. Grab from 
        ps.setString(3, user.);
        ps.setInt(2, address);
        ps.setInt(2, address);
        ps.setInt(2, address);
        ps.executeUpdate();
    }
    
    public static void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM customer WHERE customerId = ?";
        DBQuery.setPreparedStatement(query, DBConnection.getConnection());
        ps = DBQuery.getPreparedStatement();
        ps.setInt(1, customerId);
        ps.executeUpdate();
    }
    
}
