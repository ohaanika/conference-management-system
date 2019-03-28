package managementservices;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.UUID;

public class TaskManagementService {
	
	
	public UUID createTask(String description, Date deadlineDate, Time deadlineTime) {
	        UUID taskId = UUID.randomUUID();

	        Connection connection = ConnectionManager.getConnectionInstance();
	        PreparedStatement basicTask = null;
	        // The SQL for an event with a title.
	        String createEventSQL = "INSERT INTO Task(tid,description,deadlinedate,deadlinetime) VALUES('" +taskId.toString() + "','" + description + "','" + deadlineDate  + "','" + deadlineTime + "');";

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
	
	  public void GiveTaskToOrganizer(UUID oID,UUID tID) {
	        Connection connection = ConnectionManager.getConnectionInstance();
	       
	        PreparedStatement basicGiving = null;
	        String createEventSQL = "INSERT INTO OrganizerTasks(organizerid,taskid) VALUES('" +oID.toString() + "','" + tID.toString()  + "');";

	        // An entry in the table is created for any event of any type due to ISA relationship.
	        try {
	            basicGiving = connection.prepareStatement(createEventSQL);
	            basicGiving.execute();

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
