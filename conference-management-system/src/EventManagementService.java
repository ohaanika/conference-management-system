public class EventManagementService {

    /**
     * Creates an event of specific type.
     * Initializes the properties, generates the ID but no location or start time, end time or date is assigned just yet.
     *
     * @param title the title of the event
     * @param type the type of event
     * @param delegateId the delegate being interviewed, null otherwise
     * @param sponsorId the sponsor interviewing an attendee, null otherwise
     */
    public void createEvent(String title, String type, String delegateId, String sponsorId) {
        // An entry in the table is created for any event of any type due to ISA relationship.

        // createWorkshop(title) if type is Workshop

        // createTalk(title) if type is Talk

        // createInterview(String title, String type)
    }

    public void scheduleEvent() {
    }
}
