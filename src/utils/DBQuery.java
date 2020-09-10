/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author schesser
 */
public class DBQuery {
    private static PreparedStatement preparedStatement;
    
    // Create PreparedStatement Object
    public static void setPreparedStatement(String sqlStatement, Connection conn) throws SQLException
    {
       preparedStatement = conn.prepareStatement(sqlStatement); 
    }
    
    // Return PreparedStatement Object
    public static PreparedStatement getPreparedStatement()
    {
      return preparedStatement;
    }      
}
