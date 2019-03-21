import java.util.Scanner;

/**
 * The main GUI that allows users to do the following:
 * Choose from one of the following 5 options:
 * 1.
 * 2.
 * 3.
 * 4.
 * 5.
 * Quit the GUI.
 */
public class Client {

    public static void main(String[] args) {
        // Create scanner to obtain a user input.
        Scanner sc = new Scanner(System.in);
        // Until the user quits, do the following.
        while (true) {
            // Describe user options.
            System.out.println("Please choose one of the following 5 options (enter number):");
            System.out.println("1: ");
            System.out.println("2: ");
            System.out.println("3: Create an event and schedule it");
            System.out.println("4: ");
            System.out.println("5: Generate the entire schedule. ");
            System.out.println("6: quit program");

            // Obtain user input.
            int option = sc.nextInt();
            if (option == 6) {
                break;
            }

            // Process the option chosen by user.
            processOption(option, sc);
        }


    }

    private static  void processOption(int option, Scanner sc) {
        if (option == 1) {

        } else if (option == 2) {

        } else if (option == 3) {
            EventManagementService eventManagementService = new EventManagementService();

            // Get user input to retrieve the type of event necessary.
            System.out.println("What is the type of your event, enter one of: Interview, Workshop, Talk or General");
            String type = sc.nextLine();

            System.out.println("Please enter the title of your event. ");
            String title = sc.nextLine();

            // If it is an interview:
            if(type.toLowerCase().equals("interview")){
                // Obtain the delegateId and sponsorId
                System.out.println("Please enter the deleateId of interviewee. ");
                System.out.println("Please enter the sponsorId of interviewer. ");
                eventManagementService.createEvent(title,type,"","");
            } else {
                eventManagementService.createEvent(title,type,null,null);
            }

            /**
             * createEvent(title),
             * createInterview(title,delegateID,sponsorID),
             * createWorkshop(title),
             * createTalk(title)
             *
             * Creates an event of that type.
             * Initializes the properties, generates the ID but no location or start time, end time or date is assigned just yet.
             *
             * scheduleEvent(eventID,startTime,EndTime,eventDate,locationID)
             */
            // TODO: Need to catch exception and ensure that the console outputs it.
            eventManagementService.scheduleEvent();

        } else if (option == 4) {


        } else if (option == 5) {

        }
    }
}
