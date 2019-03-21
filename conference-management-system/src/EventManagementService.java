import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EventManagementService {

    /**
     * Creates an event of specific type.
     * Initializes the properties, generates the ID, but no location or start time, end time or date is assigned just yet.
     *
     * @param title the title of the event.
     * @return the UUID of the newly created event.
     */
    public UUID createEvent(String title) {
        UUID eventId = UUID.randomUUID();

        Connection connection = ConnectionManager.getConnectionInstance();
        PreparedStatement basicEvent = null;
        // The SQL for an event with a title.
        String createEventSQL = "INSERT INTO Event(eventid,title) VALUES(" + eventId.toString() +","+title +")";

        // An entry in the table is created for any event of any type due to ISA relationship.
        try {
            basicEvent = connection.prepareStatement(createEventSQL);
            ResultSet result = basicEvent.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionManager.closeConnection();

        return eventId;
    }

    public void createInterview(String title, String delegateId, String sponsorId) {
        createEvent(title);

    }

    public void createTalk(String title, String speakerId) {
        createEvent(title);
    }

    public void createWorkshop(String title, String speakerId) {
        createEvent(title);
    }

    public void scheduleEvent() {
        Connection connection = ConnectionManager.getConnectionInstance();
    }

}
