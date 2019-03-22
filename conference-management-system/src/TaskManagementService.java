import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.UUID;

import Model.Location;

public class TaskManagementService {
	
	
	public UUID createTask(String description, Date deadlineDate, Time deadlineTime) {
	        UUID taskId = UUID.randomUUID();

	        Connection connection = ConnectionManager.getConnectionInstance();
	        PreparedStatement basicTask = null;
	        // The SQL for an event with a title.
	        String createEventSQL = "INSERT INTO Task(tid,description,deadlinedate,deadlinetime) VALUES('" +taskId.toString() + "','" + description + "'," + deadlineDate  + "'," + deadlineTime + ");";

	        // An entry in the table is created for any event of any type due to ISA relationship.
	        try {
	            basicTask = connection.prepareStatement(createEventSQL);
	            basicTask.execute();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        try {
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return taskId;
	    }
	
	  public void GiveTaskToOrganizer(String OrganizerFirstName, String OrganizerLastName, UUID takskID) {
	        Connection connection = ConnectionManager.getConnectionInstance();
	       
	        PreparedStatement basicGiving = null;
	        String createEventSQL = "UPDATE EVENT SET " + "startTime='" + startTime.toString() + "',endTime='" + endTime.toString() + "',locationid='" + location.getLocationId() + "' WHERE eid='" + eventId.toString() + "';";

	        // An entry in the table is created for any event of any type due to ISA relationship.
	        try {
	            basicEvent = connection.prepareStatement(createEventSQL);
	            basicEvent.execute();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        try {
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	    }
	
	
}
