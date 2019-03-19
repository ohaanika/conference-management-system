import java.util.Scanner;

/**
 * The main GUI that allows users to do the following:
 * Choose from one of the following 5 options:
 *  1.
 *  2.
 *  3.
 *  4.
 *  5.
 * Quit the GUI.
 */
public class Client {
    public static void main(String [] args){
        while(true){
            // Describe user options.
            System.out.println("Please choose one of the following 5 options (enter number):");
            System.out.println("1: ");
            System.out.println("2: ");
            System.out.println("3: ");
            System.out.println("4: ");
            System.out.println("5: ");
            System.out.println("6: quit program");

            // Obtain user input.
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            if (option==6){
                break;
            }

            // Process the option chosen by user.
            processOption(option);
        }


    }

    private static void processOption(int option) {
        if(option==1){

        } else if (option==2){

        } else if (option==3){

        } else if (option==4){

        } else if (option==5){

        }
    }
}
