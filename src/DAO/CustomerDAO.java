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
    
}
