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
            EventManagementService eventManagementService = new EventManagementService();

            // Get user input to retrieve the type of event necessary.
            System.out.println("What is the type of your event, enter one of: Interview, Workshop, Talk or General");
            String type = sc.nextLine();

            System.out.println("Please enter a title of your event. ");
            String title = sc.nextLine();

            System.out.println("Please enter a capacity (number of people) for your event (minimum 0). ");
            int size = sc.nextInt();
            sc.nextLine();

            // If it is an interview:
            if (type.toLowerCase().equals("interview")) {
                // Obtain the delegateId and sponsorId
                System.out.println("Please enter the deleateId of interviewee. ");
                String delegate = sc.nextLine();
                System.out.println("Please enter the sponsorId of interviewer. ");
                String sponsor = sc.nextLine();
                eventManagementService.createInterview(title, size, delegate, sponsor);
                // If it is a talk:
            } else if (type.toLowerCase().equals("talk")) {
                System.out.println("Please enter the speaker of interviewee. ");
                String speaker = sc.nextLine();
                eventManagementService.createTalk(title, size, speaker);
                // If it is a workshop.
            } else if (type.toLowerCase().equals("workshop")) {
                String companyId = "";
                eventManagementService.createWorkshop(title, size,companyId);
                // Otherwise it is a general event.
            } else {
                eventManagementService.createEvent(title, size);
            }
            System.out.println("The event was successfully created. It will now be scheduled to an available room at a specific time.");

            eventManagementService.scheduleEvent();

        } else if (option == 4) {


        } else if (option == 5) {

        }
    }
}
