package managementservices;

import model.Location;

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
            eventId=null;
            System.out.println("Sorry the event could not be created.");
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventId;
    }

    /**
     * Creates an interview between a delegate(interviewee) and sponsor(interviewer)
     *
     * @param title      The title of the event
     * @param size       The number of people interviewing
     * @param delegateId
     * @param sponsorId
     *
     * @return the event id of the newly created event.
     */
    public UUID createInterview(String title, int size, UUID delegateId, UUID sponsorId) {
        // First create the event.
        UUID eventId = createEvent(title, size);
        if(eventId==null){
            return null;
        }
        // Now create the interview.
        Connection connection = ConnectionManager.getConnectionInstance();

        PreparedStatement basicEvent;
        String createEventSQL = "INSERT INTO Interview(eid,delegateId,sponsorId) VALUES('" + eventId.toString() + "','" + delegateId.toString() + "','" + sponsorId.toString() + "');";
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

    /**
     * Create a talk and assign it a speaker.
     *
     * @param title
     * @param size
     * @param speakerId
     * @return
     */
    public UUID createTalk(String title, int size, UUID speakerId) {
        // First create the event.
        UUID eventId = createEvent(title, size);
        if(eventId==null){
            return null;
        }
        // Now create the interview.
        Connection connection = ConnectionManager.getConnectionInstance();

        PreparedStatement basicEvent = null;
        String createEventSQL = "INSERT INTO Talk(eid,speakerId) VALUES('" + eventId.toString() + "','" + speakerId.toString() + "');";
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

    /**
     * Create a workshop and assign a company to it.
     *
     * @param title
     * @param size
     * @param companyId
     * @return
     */
    public UUID createWorkshop(String title, int size, String companyId) {
        // First create the event.
        UUID eventId = createEvent(title, size);
        if(eventId==null){
            return null;
        }
        // Now create the workshop.
        Connection connection = ConnectionManager.getConnectionInstance();
        PreparedStatement basicEvent = null;
        // The SQL for an event with a title.
        String createEventSQL = "INSERT INTO Workshop(eid,companyid) VALUES('" + eventId.toString() + "','" + companyId + "');";

        // An entry in the table is created for any event of any type due to ISA relationship.
        try {
            basicEvent = connection.prepareStatement(createEventSQL);
            basicEvent.execute();

        } catch (SQLException e) {
            System.out.println("Sorry the event could not be created.");
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventId;
    }

    /**
     * Schedule an event given the desired parameters.
     *
     * @param eventId   The unique eventId from the Event table.
     * @param location  The unique locationId for the room this event will occur in.
     * @param sqlDate   The date of the event in the format (yyyy-mm-dd)
     * @param startTime The start time of the event in the format (00:00:00)
     * @param endTime   The end time of the event in the format (00:00:00)
     */
    public void scheduleEvent(UUID eventId, Location location, Date sqlDate, Time startTime, Time endTime) {
        Connection connection = ConnectionManager.getConnectionInstance();
        // The SQL to update an event with the start time , end time and date.
        // TODO: Checks
        //  1. Is the start time before the start date?
        //  2. Is the date in the future? (DO WE NEED THIS)
        PreparedStatement basicEvent;
        String createEventSQL = "UPDATE EVENT SET " + "startTime='" + startTime.toString() + "',endTime='" + endTime.toString() + "',locationid='" + location.getLocationName() +"',eventDate='"+sqlDate.toString() +"' WHERE eid='" + eventId.toString() + "';";

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

    /**
     * The locations that are available for that time.
     *
     * @param sqlDate   The date of the event.
     * @param startTime The start time.
     * @param endTime   The end time.
     *
     * @return availableLocations   The locations available in that period of time.
     */
    public ArrayList<Location> findAvailableLocationForEvent(Date sqlDate, Time startTime, Time endTime, int size) {
        ArrayList<Location> availableLocations = new ArrayList<Location>();
        Connection connection = ConnectionManager.getConnectionInstance();
        PreparedStatement basicEvent;
        String createEventSQL = "SELECT * from Location WHERE lname NOT IN ( SELECT DISTINCT locationId FROM Event WHERE eventDate='"+ sqlDate.toString() +"' AND startTime<='"+ endTime.toString() +"' AND endTime>='"+startTime.toString()+ "' AND locationId IS NOT null) AND capacity>" + size + ";";

        // An entry in the table is created for any event of any type due to ISA relationship.
        try {
            basicEvent = connection.prepareStatement(createEventSQL);
            ResultSet rs = basicEvent.executeQuery();
            while (rs.next()){
                Location location = new Location();
                location.setLocationName(rs.getString("lname"));
                availableLocations.add(location);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableLocations;
    }
}
