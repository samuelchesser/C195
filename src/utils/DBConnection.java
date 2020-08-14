package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author schesser
 */
public class DBConnection {
    
    private static final String dbName = "U04cHT";
    private static final String dbUrl = "jdbc:mysql://3.227.166.251/" + dbName;
    private static final String userName = dbName;
    private static final String password = "53688202800";
    private static final String driver = "com.mysql.jdbc.Driver";
    static Connection connection;

    //Create DB Connection
    
    public static void makeConnection() {
        try {
        Class.forName(driver);
        connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(dbUrl, userName, password);
        System.out.println("Connection successful");
        }
        catch (ClassNotFoundException exception) {
            System.out.println("Class not found for SQL driver: " + exception.getMessage());
        }
        catch (SQLException exception) {
            System.out.println("SQL exception encountered: " + exception.getMessage());
        }
    }
    
    //Close DB Connection
    
    public static void closeConnection()  throws ClassNotFoundException, SQLException {
            connection.close();
            System.out.println("Connection closed");
        }
    
    public static Connection getConnection() {
        return connection;
    }
}
       
