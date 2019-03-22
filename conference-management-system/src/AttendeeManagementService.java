import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import Model.Attendee;
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

    public void printStr(String type, String firstName, String lastName, String emailAddress, String shirtCut, String shirtSize, String dietaryRestrictions, String major, String university, String province, String role, String company) {
    	System.out.println("INSERT INTO Attendee VALUES (" +  UUID.randomUUID()+ "," + firstName + "," + lastName + "," + emailAddress
    		+ "," + shirtCut + "," + shirtSize + "," + dietaryRestrictions + ")");
    }
    
    
	public List<Attendee> getAttendeeIDsFromName( String FirstName, String LastName) {
		 Connection connection = ConnectionManager.getConnectionInstance();
	       
	        PreparedStatement basicGetting = null;
	        String getAttendeeSQL = "SELECT aid,emailaddress FROM Attendee WHERE firstname= '"+FirstName+"' AND lastname= '"+LastName+"';";
	        List<Attendee> attendees =new ArrayList<Attendee>();
	        // An entry in the table is created for any event of any type due to ISA relationship.
	        try {
	           basicGetting = connection.prepareStatement(getAttendeeSQL);
	           ResultSet rs= basicGetting.executeQuery();
	           while ( rs.next()) {
	        	  UUID  aid= UUID.fromString(rs.getString("aid"));
	  
	        	  String email= rs.getString("emailaddress");
	        	  Attendee attendee= new Attendee();
	        	  attendee.setEmail(email);
	        	  attendee.setFn(FirstName);
	        	  attendee.setLn(LastName);
	        	  attendee.setId(aid);
	        	  attendees.add(attendee);
	        	  
	        	   		
	           }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        try {
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

		return attendees;
		
		
	}
    
    
    
}
    
    
