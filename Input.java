import java.util.Scanner;

/**
 * Class which accepts input from the user via the keyboard via several static methods.
 *
 * @author Charlie Timlock
 * @version ver1.0.0
 */

public class Input 
{
    /**
     * Default constructor that creates an object of the class Input.
     * Has been set to private to avoid needless instantiation.
     */
    private Input()
    {

    }

    /**
     * Method to request and pass back a user input as a character.
     * @param index The index of the character to return from a user's entered String, as an integer.
     * @return Returns the specified character from a user's entered string as a character.
     */
    public static char acceptCharInput(int index)
    {
        Scanner console = new Scanner(System.in);
        return console.nextLine().charAt(index);
    }

    /**
     * Method to get user to press enter.
     */
    public static void acceptEmptyInput()
    {
        Scanner console = new Scanner(System.in);
        String temp = console.nextLine();          
    }

    /**
     * Method to request and pass back a user input as a Integer.
     * @return Returns the user's entered string as an Integer.
     */
    public static int acceptIntegerInput()
    {
        Scanner console = new Scanner(System.in);
        return Integer.parseInt(console.nextLine());
    }

    /**
     * Method to request and pass back a user input as a String.
     * @return Returns the user's entered string as a String.
     */
    public static String acceptStringInput()
    {
        Scanner console = new Scanner(System.in);
        return console.nextLine();          
    }
}