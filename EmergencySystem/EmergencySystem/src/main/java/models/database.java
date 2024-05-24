package models;
import java.sql.*;


public abstract class database {
	 // Database connection parameters
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "Omr_20021129";
	// Database connection method
	public static Connection connect() throws SQLException {
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	    } catch (ClassNotFoundException e) {
	        System.err.println("Oracle JDBC Driver not found. Ensure the driver is in your classpath.");
	        e.printStackTrace();
	    }
	    return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}

}
