package managementservices; /**
 * Class to connect to database.
 * TODO: Could simplify to not recreate the managementservices.ConnectionManager everytime.
 * TODO: Handle the SQL issues.
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
    private static String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";

    private ConnectionManager()
    {
        // Register the driver.  You must register the driver before you can use it.
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (Exception cnfe) {
            System.out.println("Class not found");
        }

    }

    public static Connection getConnectionInstance() {

        if (singleton==null){
            singleton = new ConnectionManager();
        }
        // Connect to database
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "cs421g14", "AaReRaAs14");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}

