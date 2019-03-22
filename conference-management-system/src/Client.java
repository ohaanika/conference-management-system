import Model.Location;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

/**
 * The main GUI that allows users to do the following:
 * Choose from one of the following 5 options:
 * 1.
 * 2.
 * 3.
 * 4.
 * 5.
 * 6. Quit the console.
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
            System.out.println("4:Task operation: Create task/assign task to event/assign event to organizer ");
            System.out.println("5: Generate the entire conference schedule. ");
            System.out.println("6: quit program");

            // Obtain user input.
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 6) {
                break;
            }

            // Process the option chosen by user.
            processOption(option, sc);
        }


    }

    private static void processOption(int option, Scanner sc) {
        // TODO: Need to catch exception and ensure that the console outputs it.
        if (option == 1) {

        } else if (option == 2) {

        } else if (option == 3) {
            processEventCreation(sc);
        } else if (option == 4) {

        	taskOption(sc);
        } else if (option == 5) {

        }
    }
    private static void taskOption (Scanner sc) {
    	TaskManagementService tskman= new TaskManagementService();
    	 System.out.println("Please enter the task description");
    	 String taskDescription = sc.nextLine();
    	 System.out.println("Please enter the task deadlinedate");
    	 String taskDeadlineDateString = sc.nextLine();
    	 System.out.println("Please enter the task deadlinetime");
    	 String taskDeadlineTimeString = sc.nextLine();
    	 UUID taskid=null;
    	 
    	   Date taskDeadlineDate=null;
    	   boolean incorrect = true;
    	   while (incorrect){  
         try{
             taskDeadlineDate = java.sql.Date.valueOf(taskDeadlineDateString);
         } catch(Exception e){
             System.out.println("The format was incorrect, please re-enter.");
             continue;
         }
         incorrect=false;
    	   }
    	 Time taskDeadlineTime=null; 
         boolean inc=true;
         while(inc) {
         

         try{
              taskDeadlineTime = java.sql.Time.valueOf(taskDeadlineTimeString);
         } catch(Exception e){
             System.out.println("The format was incorrect, please re-enter.");
             continue;
         }
    	 inc=false;
         }
    	 
    	 taskid=tskman.createTask(taskDescription,taskDeadlineDate,taskDeadlineTime);
    	
    }
    private static void processEventCreation(Scanner sc) {
        EventManagementService eventManagementService = new EventManagementService();
        // 1. Event creation

        // Get user input to retrieve the type of event necessary.
        System.out.println("What is the type of your event, enter one of: Interview, Workshop, Talk or General");
        String type = sc.nextLine();

        System.out.println("Please enter a title for your event");
        String title = sc.nextLine();

        System.out.println("Please enter a capacity (number of people) for your event (minimum 0)");
        int size = sc.nextInt();
        sc.nextLine();

        UUID eventId = null;
        // If it is an interview:
        if (type.toLowerCase().equals("interview")) {
            // Obtain the delegateId and sponsorId
            System.out.println("Please enter the deleateId of interviewee. ");
            String delegate = sc.nextLine();

            System.out.println("Please enter the sponsorId of interviewer. ");
            String sponsor = sc.nextLine();

            eventId = eventManagementService.createInterview(title, size, delegate, sponsor);

            // If it is a talk:
        } else if (type.toLowerCase().equals("talk")) {
            System.out.println("Please enter the speaker of interviewee. ");
            String speaker = sc.nextLine();

            eventId = eventManagementService.createTalk(title, size, speaker);
            // If it is a workshop.
        } else if (type.toLowerCase().equals("workshop")) {
            String companyId = "";
            eventId = eventManagementService.createWorkshop(title, size, companyId);
            // Otherwise it is a general event.
        } else if (type.toLowerCase().equals("general")) {
            eventId = eventManagementService.createEvent(title, size);
        } else {
            System.out.println("That was not a valid option.");
            return;
        }
        System.out.println("The event was successfully created.");

        // 2. Event scheduling

        System.out.println("Would you like to schedule the created event (yes/no)?");
        String schedule = sc.nextLine();
        if (schedule.toLowerCase().equals("yes")) {
            // Get date of the event and convert to correct format.
            boolean incorrect = true;
            String date = null;
            java.sql.Date dateSQL = null;
            while (incorrect){
                System.out.println("Enter event date in correct format (2019-12-01)");
                date = sc.nextLine();
                try{
                    dateSQL = java.sql.Date.valueOf(date);
                } catch(Exception e){
                    System.out.println("The format was incorrect, please re-enter.");
                    continue;
                }
                incorrect = false;
            }

            // Get the start time of the event and convert to correct format.
            //TODO: Ensure format is correct.
            System.out.println("Enter start time (00:00:00)");
            String startTime = sc.nextLine();
            Time startTimeSQL = Time.valueOf(startTime);

            // Get the end time of the event and convert to correct format.
            //TODO: Ensure format is correct.
            System.out.println("Enter end time (00:00:00)");
            String endTime = sc.nextLine();
            Time endTimeSQL = Time.valueOf(endTime);

            // Allow user to pick an available location
            System.out.println("Pick one of these available locations:");
            ArrayList<Location> availableLocations = eventManagementService.findAvailableLocationForEvent(dateSQL, startTimeSQL, endTimeSQL);
            for (int i = 0; i < availableLocations.size(); i++) {
                System.out.println(i + ":" + availableLocations.get(i).getLocationName());
            }
            int locationIndex = sc.nextInt();
            sc.nextLine();

            // Schedule the event.
            eventManagementService.scheduleEvent(eventId, availableLocations.get(locationIndex), dateSQL, startTimeSQL, endTimeSQL);
            System.out.println("The event was successfully scheduled.");
        }
    }
}
