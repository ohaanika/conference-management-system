/**
 * Class to connect to database.
 */
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

class dbconnection {
    public static void main(String[] args) throws SQLException {

        // Register the driver.  You must register the driver before you can use it.
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (Exception cnfe) {
            System.out.println("Class not found");
        }

        // Connect to database
        String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
        Connection con = DriverManager.getConnection(url, "cs421g14", "AaReRaAs14");
        Statement statement = con.createStatement();

        // SQL queries to execute go here:



        // Close connection:
        statement.close();
        con.close();
    }
}

