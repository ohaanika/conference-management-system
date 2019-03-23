import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class AttendeeManagementService {

    /**
     * Registers an attendee of specific type.
     * Initializes the properties, generates the ID.
     *
     * @param firstName 
     * @param lastName
     * @param emailAddress
     * @param shirtCut
     * @param shirtSize
     * @param dietaryRestrictions
     */
    public void createAttendee(String type, String firstName, String lastName, String emailAddress, String shirtCut, String shirtSize, String dietaryRestrictions, String major, String university, String province, String role, String company) {
    	
		Connection connection = ConnectionManager.getConnectionInstance();

		try {
			UUID aid = UUID.randomUUID();
			connection.prepareStatement("INSERT INTO Attendee VALUES (" + aid + "," + firstName + "," + lastName + ","
					+ emailAddress + "," + shirtCut + "," + shirtSize + "," + dietaryRestrictions + ")");
			connection.close();
		} catch (SQLException e) {
			System.err.println("msg: " + e.getMessage() +"code: "+ e.getErrorCode() +"state: "+ e.getSQLState());
		}

		// An entry in the table is created for any attendee of any type due to ISA
		// relationship.

		
	}
    public void getAttendee(UUID aid) {
    	Connection connection = ConnectionManager.getConnectionInstance();
    	try {
			connection.("SELECT * FROM Attendee WHERE aid ");
			connection.close();
		} catch (SQLException e) {
			System.err.println("msg: " + e.getMessage() +"code: "+ e.getErrorCode() +"state: "+ e.getSQLState());
		}
//for debugging
    public void printStr(String type, String firstName, String lastName, String emailAddress, String shirtCut, String shirtSize, String dietaryRestrictions, String major, String university, String province, String role, String company) {
    	System.out.println("INSERT INTO Attendee VALUES (" +  UUID.randomUUID()+ "," + firstName + "," + lastName + "," + emailAddress
    		+ "," + shirtCut + "," + shirtSize + "," + dietaryRestrictions + ")");
    }
    
    
    
}
    
    
