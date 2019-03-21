/**
 * Class to connect to database.
 * TODO: Could simplify to not recreate the ConnectionManager everytime.
 */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A singleton class to retrieve the DB Connection.
 * The connection MUST be RETRIEVED and CLOSED through the manager to ensure there is a single one.
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
            // This should never occur, but in case someone has used the connection to close it, and instance remains.
            // Ensure that it is closed.
            if (con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return con;
    }

    public static void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // TODO: This could be avoided, but for now lets leave it.
        singleton = null;
        con = null;
    }

}

