import Model.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class EventManagementService {

    /**
     * Creates an event of specific type.
     * Initializes the properties, generates the ID, but no location or start time, end time or date is assigned just yet.
     *
     * @param title the title of the event.
     * @return the UUID of the newly created event.
     */
    public UUID createEvent(String title, int size) {
        UUID eventId = UUID.randomUUID();

        Connection connection = ConnectionManager.getConnectionInstance();
        PreparedStatement basicEvent = null;
        // The SQL for an event with a title.
        String createEventSQL = "INSERT INTO Event(eid,title,size) VALUES('" + eventId.toString() + "','" + title + "'," + size + ");";

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

        return eventId;
    }

    public UUID createInterview(String title, int size, String delegateId, String sponsorId) {
        // First create the event.
        UUID eventId = createEvent(title, size);
        // Now create the interview.
        Connection connection = ConnectionManager.getConnectionInstance();

        PreparedStatement basicEvent = null;
        String createEventSQL = "INSERT INTO Interview(eid,delegateId,sponsorId) VALUES('" + eventId.toString() + "','" + delegateId + "','" + sponsorId + "');";
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

        return eventId;
    }

    public UUID createTalk(String title, int size, String speakerId) {
        // First create the event.
        UUID eventId = createEvent(title, size);
        // Now create the interview.
        Connection connection = ConnectionManager.getConnectionInstance();

        PreparedStatement basicEvent = null;
        String createEventSQL = "INSERT INTO Talk(eid,speakerId) VALUES('" + eventId.toString() + "','" + speakerId + "');";
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

        return eventId;
    }

    public UUID createWorkshop(String title, int size, String companyId) {
        // First create the event.
        UUID eventId = createEvent(title, size);
        // Now create the workshop.
        Connection connection = ConnectionManager.getConnectionInstance();
        PreparedStatement basicEvent = null;
        // The SQL for an event with a title.
        String createEventSQL = "INSERT INTO Workshop(eid,,sponsorId) VALUES('" + eventId.toString() + "','" + companyId + "');";

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

        return eventId;
    }

    public void scheduleEvent(UUID eventId, Location location, Date sqlDate, Time startTime, Time endTime) {
        Connection connection = ConnectionManager.getConnectionInstance();
        // The SQL to update an event with the start time , end time and date.
        // TODO: Checks
        //  1. Is the start time before the start date?
        //  2. Is the date in the future? (DO WE NEED THIS)
        PreparedStatement basicEvent = null;
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

    public ArrayList<Location> findAvailableLocationForEvent(Date sqlDate, Time startTime, Time endTime) {
        ArrayList<Location> availableLocations = new ArrayList<Location>();

        return availableLocations;
    }
}
