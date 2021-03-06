package gui;

import model.*;
import managementservices.AttendeeManagementService;
import managementservices.EventManagementService;
import managementservices.TaskManagementService;
import managementservices.HotelBookingManagementService;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * The main GUI that allows users to do the following: Choose from one of the
 * following 5 options: 1. 2. 3. 4. 5. 6. Quit the console.
 */
public class Client {

    public static void main(String[] args) {
        // Create scanner to obtain a user input.
        Scanner sc = new Scanner(System.in);
        // Until the user quits, do the following.
        while (true) {
            // Describe user options.
            System.out.println("Please choose one of the following 5 options (enter number):");
            System.out.println("1: Create attendee.");
            System.out.println("2: Perform hotel room assignments.");
            System.out.println("3: Create an event and schedule it.");
            System.out.println("4: Task operation: Create task/assign task to organizer ");
            System.out.println("5: Generate the entire conference schedule.");
            System.out.println("6: Quit program");

            // Obtain user input.
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 6) {
                break;
            }

            // Process the option chosen by user.
            processOption(option, sc);
        }
        sc.close();

        System.out.println("Application terminated successfully!");

    }

    private static void processOption(int option, Scanner sc) {
        if (option == 1) {
            createAttendee(sc);
        } else if (option == 2) {
        	processHotelBooking(sc);
        } else if (option == 3) {
            processEventCreationAndScheduling(sc);
        } else if (option == 4) {
            taskOption(sc);
        } else if (option == 5) {

        }
    }

    private static void createAttendee(Scanner sc) {

        AttendeeManagementService attnMgmSer = new AttendeeManagementService();
        String attendeeIdentifier;
        System.out.println("Please enter type of attendee");
        String type = sc.nextLine();
        System.out.println("Please enter first name");
        String firstName = sc.nextLine();
        System.out.println("Please enter last name");
        String lastName = sc.nextLine();
        System.out.println("Please enter email address");
        String emailAddress = sc.nextLine();
        System.out.println("Please enter shirt cut (F, M, U)");
        String shirtCut = sc.nextLine();

        while (!shirtCut.toLowerCase().equals("f") && !shirtCut.toLowerCase().equals("m")
                && !shirtCut.toLowerCase().equals("u")) {
            System.out.println("Incorrect shirt cut. Please enter: shirt cut (F, M, U)");
            shirtCut = sc.nextLine();
        }

        System.out.println("Please enter shirt size (XS, S, M, L, XL, XXL)");
        String shirtSize = sc.nextLine();

        while (!shirtSize.toLowerCase().equals("xs") && !shirtSize.toLowerCase().equals("s")
                && !shirtSize.toLowerCase().equals("m") && !shirtSize.toLowerCase().equals("l")
                && !shirtSize.toLowerCase().equals("xl") && !shirtSize.toLowerCase().equals("xxl")) {
            System.out.println("Incorrect shirt cut. Please enter: shirt cut (F, M, U)");
            shirtSize = sc.nextLine();
        }

        System.out.println("Please enter: dietary restrictions");
        String dietaryRestrictions = sc.nextLine();

        if (type.toLowerCase().equals("delegate")) {
            System.out.println("Please enter: major");
            String major = sc.nextLine();
            System.out.println("Please enter: university");
            String university = sc.nextLine();
            System.out.println("Please enter: province");
            String province = sc.nextLine();
            attendeeIdentifier = attnMgmSer.createAttendee(type, firstName, lastName, emailAddress, shirtCut,
                    shirtSize.toUpperCase(), dietaryRestrictions, major, university, province, "", "", "");

        } else if (type.toLowerCase().equals("organizer")) {
            System.out.println("Please enter: role");
            String role = sc.nextLine();
            attendeeIdentifier = attnMgmSer.createAttendee(type, firstName, lastName, emailAddress, shirtCut,
                    shirtSize.toUpperCase(), dietaryRestrictions, "", "", "", role, "", "");
        } else if (type.toLowerCase().equals("sponsor")) {
            System.out.println("Please enter company name from these options:");
            String companyName = sc.nextLine();
            attendeeIdentifier = attnMgmSer.createAttendee(type, firstName, lastName, emailAddress, shirtCut,
                    shirtSize.toUpperCase(), dietaryRestrictions, "", "", "", "", companyName, "");
        } else {
            System.out.println("Please enter company name");
            String companyName = sc.nextLine();
            System.out.println("Please enter tier");
            String tier = sc.nextLine();
            attendeeIdentifier = attnMgmSer.createAttendee(type, firstName, lastName, emailAddress, shirtCut,
                    shirtSize.toUpperCase(), dietaryRestrictions, "", "", "", "", companyName, tier);
        }

        System.out.println("Attendee " + attendeeIdentifier + " created successfully!\n");

    }
    
    private static void processHotelBooking(Scanner sc) {
    	HotelBookingManagementService homan = new HotelBookingManagementService();
    	AttendeeManagementService atman = new AttendeeManagementService();
    	System.out.println("How many attendees are you booking for? (min 1, max 4)");
    	int numAttendees = sc.nextInt();
        sc.nextLine();
        while ((numAttendees > 4) || (numAttendees <= 0)) {
        	System.out.println("ERROR: You cannot book for less than 1 or more than 4 people!");
        	System.out.println("Please re-enter the number of attendees you are booking for: ");
        	numAttendees = sc.nextInt();
            sc.nextLine();
        }
        List<HotelRoom> availableRooms = homan.findAvailableRooms(numAttendees);
        System.out.println("roomNumber" + " | " + "spotsOccupied" + " | " + "spotsAvailable");
        for (HotelRoom room : availableRooms) {
            System.out.println(room.getRoomNumber() + "       | " + room.getNumOccupied() + "             | " + room.getNumAvailable());
        }
        System.out.println("Please enter the room number of the hotel room that you would like to book from the given list.");
    	String roomNumber = sc.nextLine();
    	while (homan.findHotelRoomByRoomNumber(Integer.parseInt(roomNumber), availableRooms) == null) {
    	    System.out.println("ERROR: Room not in the given list!");
    	    System.out.println("Please re-enter the room number of the hotel room that you would like to book.");
        	roomNumber = sc.nextLine();
    	}
    	System.out.println("Please enter the aIDs of the attendees you are booking for.");
    	String[] attendeeFirstNames = new String[numAttendees];
    	String[] attendeeLastNames = new String[numAttendees];
    	for (int i = 1; i <= numAttendees; i++) {
    		System.out.println("Attendee " + i + " first name:");
    		attendeeFirstNames[i-1] = sc.nextLine();
    		System.out.println("Attendee " + i + " last name:");
    		attendeeLastNames[i-1] = sc.nextLine();
    		List<Attendee> attendees = atman.getAttendeeIDsFromName(attendeeFirstNames[i-1], attendeeLastNames[i-1]);
            while(attendees.size() == 0){
                System.out.println("ERROR: Sorry there are no attendees with that name. Please create one using option 1 or re-enter an existing name.");
                System.out.println("Attendee " + i + " first name:");
        		attendeeFirstNames[i-1] = sc.nextLine();
        		System.out.println("Attendee " + i + " last name:");
        		attendeeLastNames[i-1] = sc.nextLine();
        		attendees = atman.getAttendeeIDsFromName(attendeeFirstNames[i-1], attendeeLastNames[i-1]);
            }
            System.out.println("Please choose an attendee from the following list: ");
            boolean attendeeNotChosen = true;
            int attendeeIndex = 0;
            while (attendeeNotChosen) {
                for (int j = 0; j < attendees.size(); j++) {
                    Attendee currAttendee = attendees.get(j);
                    System.out.println(j + ": " + currAttendee.getEmail() + "," + currAttendee.getFn() + "," + currAttendee.getLn());
                }
                attendeeIndex = sc.nextInt();
                sc.nextLine();
                if (attendeeIndex >= attendees.size()) {
                    System.out.println("ERROR: Not a valid choice. Please re-select.");
                    continue;
                }
                attendeeNotChosen = false;
            }          
            homan.assignRoom(homan.findHotelRoomByRoomNumber(Integer.parseInt(roomNumber), availableRooms).getId(), attendees.get(attendeeIndex).getId());          
            System.out.println("Assigned hotel booking for " + attendees.get(attendeeIndex).getFn() + " " + attendees.get(attendeeIndex).getLn() + " successfully!");
    	}	
    }

    private static void taskOption(Scanner sc) {
        TaskManagementService tskman = new TaskManagementService();
        AttendeeManagementService atman = new AttendeeManagementService();
        System.out.println("Please enter the task description");
        String taskDescription = sc.nextLine();
        System.out.println("Please enter the task deadlinedate");
        String taskDeadlineDateString = sc.nextLine();
        System.out.println("Please enter the task deadlinetime");
        String taskDeadlineTimeString = sc.nextLine();
        UUID taskid = null;

        Date taskDeadlineDate = null;
        boolean incorrect = true;
        while (incorrect) {
            try {
                taskDeadlineDate = java.sql.Date.valueOf(taskDeadlineDateString);
            } catch (Exception e) {
                System.out.println("The format was incorrect, please re-enter.");
                continue;
            }
            incorrect = false;
        }
        Time taskDeadlineTime = null;
        boolean inc = true;
        while (inc) {

            try {
                taskDeadlineTime = java.sql.Time.valueOf(taskDeadlineTimeString);
            } catch (Exception e) {
                System.out.println("The format was incorrect, please re-enter.");
                continue;
            }
            inc = false;
        }

        taskid = tskman.createTask(taskDescription, taskDeadlineDate, taskDeadlineTime);

        System.out.println("Task created Successfully! Would you like to give it to an organizer (yes/no)?");
        String choice = sc.nextLine();
        if (choice.toLowerCase().equals("yes")) {
            System.out.println("Organizer Firstname?");
            String fn = sc.nextLine();
            System.out.println("Organizer Lastname?");
            String ln = sc.nextLine();
            System.out.println("Please enter your organizer's index from the following list:");
            List<Attendee> attendees = atman.getAttendeeIDsFromName(fn, ln);
            int i = 0;
            for (Attendee attendee : attendees) {
                System.out.println(i + ": " + attendee.getEmail() + " , " + attendee.getId());

            }
            int indx = sc.nextInt();
            Attendee chosenOne = attendees.get(indx);
            tskman.GiveTaskToOrganizer(chosenOne.getId(), taskid);
            System.out.println("Assigned Successfully!");

        }

    }

    private static void processEventCreationAndScheduling(Scanner sc) {
        EventManagementService eventManagementService = new EventManagementService();
        AttendeeManagementService attendeeManagementService = new AttendeeManagementService();

        // 1. Event creation
        // Get the type of event and title.
        System.out.println("What is the type of your event, enter one of: Interview, Workshop, Talk or General");
        String type = sc.nextLine();
        System.out.println("Please enter a title for your event (max 30 characters)");
        String title = sc.nextLine();

        UUID eventId;
        int size = 2;
        // If it is an interview:
        if (type.toLowerCase().equals("interview")) {
            eventId = createInterview(sc, attendeeManagementService, eventManagementService, title, type);
            if(eventId==null){
                System.out.println("The event could not be created");
                return;
            }

            // If it is a talk:
        } else if (type.toLowerCase().equals("talk")) {
            System.out.println("Please enter a capacity (number of people) for your event (minimum 0)");
            size = sc.nextInt();
            sc.nextLine();
            eventId = createTalk(sc, attendeeManagementService, eventManagementService, title, type, size);
            if(eventId==null){
                System.out.println("The event could not be created");
                return;
            }

            // If it is a workshop.
        } else if (type.toLowerCase().equals("workshop")) {
            System.out.println("Please enter a capacity (number of people) for your event (minimum 0)");
            size = sc.nextInt();
            sc.nextLine();
            eventId = createWorkshop(sc, attendeeManagementService, eventManagementService, title, type, size);
            if(eventId==null){
                System.out.println("The event could not be created");
                return;
            }
            // Otherwise it is a general event.
        } else if (type.toLowerCase().equals("general")) {
            System.out.println("Please enter a capacity (number of people) for your event");
            size = sc.nextInt();
            sc.nextLine();
            eventId = eventManagementService.createEvent(title, size);
            if(eventId==null){
                System.out.println("The event could not be created");
                return;
            }

            // Invalid option
        } else {
            System.out.println("That was not a valid option. Please try again.");
            return;
        }
        System.out.println("The event was successfully created.");

        // 2. Event scheduling

        System.out.println("Would you like to schedule the created event (yes/no)?");
        String schedule = sc.nextLine();
        if (schedule.toLowerCase().equals("yes")) {
            // Get date of the event and convert to correct format.
            boolean incorrect = true;
            String date;
            java.sql.Date dateSQL = null;
            while (incorrect) {
                System.out.println("Enter event date in correct format (2019-12-01)");
                date = sc.nextLine();
                try {
                    dateSQL = java.sql.Date.valueOf(date);
                } catch (Exception e) {
                    System.out.println("The format was incorrect, please re-enter.");
                    continue;
                }
                incorrect = false;
            }

            // Get the start time of the event and convert to correct format.
            // TODO: Ensure format is correct.
            System.out.println("Enter start time (00:00:00)");
            String startTime = sc.nextLine();
            Time startTimeSQL = Time.valueOf(startTime);

            // Get the end time of the event and convert to correct format.
            // TODO: Ensure format is correct.
            System.out.println("Enter end time (00:00:00)");
            String endTime = sc.nextLine();
            Time endTimeSQL = Time.valueOf(endTime);

            // Allow user to pick an available location
            ArrayList<Location> availableLocations = eventManagementService.findAvailableLocationForEvent(dateSQL,
                    startTimeSQL, endTimeSQL, size);
            if(availableLocations.size()==0){
                System.out.println("Sorry there are no available locations for this capacity. We cannot schedule the event.");
                return;
            }
            boolean locationChosen = false;
            int locationIndex = 0;
            while (!locationChosen) {
                System.out.println("Pick one of these available locations:");
                for (int i = 0; i < availableLocations.size(); i++) {
                    System.out.println(i + ":" + availableLocations.get(i).getLocationName());
                }
                locationIndex = sc.nextInt();
                sc.nextLine();
                if (locationIndex >= availableLocations.size()) {
                    System.out.println("Not a valid choice.");
                    continue;
                }
                locationChosen = true;
            }

            // Schedule the event.
            eventManagementService.scheduleEvent(eventId, availableLocations.get(locationIndex), dateSQL, startTimeSQL,
                    endTimeSQL);
            System.out.println("The event was successfully scheduled.");
        }
    }

    private static UUID createTalk(Scanner sc, AttendeeManagementService attendeeManagementService, EventManagementService eventManagementService, String title, String type, int size) {
        System.out.println("Please enter the first name of the speaker for this talk. ");
        String speakerFirstName = sc.nextLine();

        System.out.println("Please enter the last name of the speaker for this talk. ");
        String speakerLastName = sc.nextLine();

        List<Speaker> speakers = attendeeManagementService.getSpeakerIDsFromName(speakerFirstName, speakerLastName);
        if(speakers.size()==0){
            System.out.println("Sorry there are no speakers with that name. Please create one using option 1");
            return null;
        }
        System.out.println("Please choose the speaker from the following list: ");
        boolean speakerNotChosen = true;
        int speakerIndex=0;
        while (speakerNotChosen) {
            for (int i = 0; i < speakers.size(); i++) {
                Speaker currSpeaker = speakers.get(i);
                System.out.println(i + ": " + currSpeaker.getEmail() + "," + currSpeaker.getFirstName() + "," + currSpeaker.getLastName());
            }
            speakerIndex = sc.nextInt();
            sc.nextLine();
            if (speakerIndex >= speakers.size()) {
                System.out.println("Not a valid choice.");
                continue;
            }
            speakerNotChosen = false;
        }

        UUID eventId = eventManagementService.createTalk(title, size, speakers.get(speakerIndex).getId());
        return eventId;
    }

    private static UUID createInterview(Scanner sc, AttendeeManagementService attendeeManagementService, EventManagementService eventManagementService, String title, String type) {
        // Obtain the delegateId and sponsorId
        System.out.println("Please enter the first name of interviewee. ");
        String delegateFirstName = sc.nextLine();

        System.out.println("Please enter the last name of interviewee. ");
        String delegateLastName = sc.nextLine();

        List<Delegate> delegates = attendeeManagementService.getDelegateIDsFromName(delegateFirstName, delegateLastName);
        if(delegates.size()==0){
            System.out.println("Sorry there are no delegates with that name. Please create one using option 1");
            return null;
        }

        System.out.println("Please choose the delegate from the following list by entering the number:");
        boolean delegateNotChosen = true;
        int delegateIndex=0;
        while (delegateNotChosen) {

            for (int i = 0; i < delegates.size(); i++) {
                Delegate currDelegate = delegates.get(i);
                System.out.println(i + ": " + currDelegate.getUniversity() + "," + currDelegate.getMajor() + "," + currDelegate.getEmail() + "," + currDelegate.getFirstName() + "," + currDelegate.getLastName());
            }
            delegateIndex = sc.nextInt();
            sc.nextLine();
            if (delegateIndex >= delegates.size()) {
                System.out.println("Not a valid choice.");
                continue;
            }
            delegateNotChosen = false;
        }

        System.out.println("Please enter the first name of interviewer.");
        String sponsorFirstName = sc.nextLine();

        System.out.println("Please enter the last name of interviewer.");
        String sponsorLastName = sc.nextLine();

        List<Sponsor> sponsors = attendeeManagementService.getSponsorIDsFromName(sponsorFirstName, sponsorLastName);
        if(sponsors.size()==0){
            System.out.println("Sorry there are no sponsors with that name. Please create one using option 1");
            return null;
        }
        System.out.println("Please choose the sponsor from the following list by entering the number: ");
        boolean sponsorNotChosen = true;
        int sponsorIndex=0;
        while (sponsorNotChosen) {
            for (int i = 0; i < sponsors.size(); i++) {
                Sponsor currSponsor = sponsors.get(i);
                System.out.println(i + ": " + currSponsor.getCompanyName() + "," + currSponsor.getEmail() + "," + currSponsor.getFirstName() + "," + currSponsor.getLastName());
            }
            sponsorIndex = sc.nextInt();
            sc.nextLine();
            if (sponsorIndex >= sponsors.size()) {
                System.out.println("Not a valid choice.");
                continue;
            }
            sponsorNotChosen = false;
        }

        UUID eventId = eventManagementService.createInterview(title, 2, delegates.get(delegateIndex).getId(), sponsors.get(sponsorIndex).getId());

        return eventId;
    }

    private static UUID createWorkshop(Scanner sc, AttendeeManagementService attendeeManagementService, EventManagementService eventManagementService, String title, String type, int size) {
        List<String> companies = attendeeManagementService.getCompanyNames();
        System.out.println("Please enter the company leading the workshop from the following list by choosing the number.");
        boolean validCompanyNotChosen=true;
        int companyIndex=0;
        while (validCompanyNotChosen){
            for (int i = 0; i < companies.size(); i++) {
                System.out.println(i + ": " + companies.get(i));
            }
            companyIndex = sc.nextInt();
            sc.nextLine();
            if(companyIndex>=companies.size()){
                System.out.println("Not a valid choice.");
                continue;
            }
            validCompanyNotChosen=false;
        }
        UUID eventId = eventManagementService.createWorkshop(title, size, companies.get(companyIndex));
        return eventId;
    }
}
