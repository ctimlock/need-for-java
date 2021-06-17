import java.util.Scanner;

/**
 * Class which accepts input from the user via the keyboard.
 *
 * @author Charlie Timlock
 * @version ver1.0.0
 */

public class Input 
{
    private Scanner console;

    /**
     * Default constructor that creates an object of the class Input.
     */
    public Input()
    {
        console = new Scanner(System.in);
    }

    /**
     * Method to request and pass back a user input as a character.
     * @param index The index of the character to return from a user's entered String, as an integer.
     * @return Returns the specified character from a user's entered string as a character.
     */
    public char acceptCharInput(int index)
    {
        return console.nextLine().charAt(index);
    }

    /**
     * Method to request and pass back a user input as a Double.
     * @return Returns the user's entered string as a Double.
     */
    public double acceptDoubleInput()
    {
        return Double.parseDouble(console.nextLine());
    }

    /**
     * Method to request and pass back a user input as a Integer.
     * @return Returns the user's entered string as an Integer.
     */
    public int acceptIntegerInput()
    {
        return Integer.parseInt(console.nextLine());
    }

    /**
     * Method to request and pass back a user input as a String.
     * @return Returns the user's entered string as a String.
     */
    public String acceptStringInput()
    {
        return console.nextLine();          
    }
}