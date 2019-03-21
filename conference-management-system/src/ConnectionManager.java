/**
 * Class to connect to database.
 */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A singleton class to retrieve the DB Connection.
 */
class ConnectionManager {

    private static ConnectionManager singleton;
    private static Connection con;
    private String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";

    private ConnectionManager()
    {
        // Register the driver.  You must register the driver before you can use it.
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (Exception cnfe) {
            System.out.println("Class not found");
        }

        // Connect to database
        try {
            this.con = DriverManager.getConnection(url, "cs421g14", "AaReRaAs14");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnectionInstance() {
        if (singleton==null){
            singleton = new ConnectionManager();
        }
        return con;
    }

    public static void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        singleton = null;
        con = null;
    }

}

