/**
 * Class which validates input from the user.
 *
 * @author Charlie Timlock
 * @version ver1.0.0
 */

public class Validation 
{
    /*
    Constructor has been made private to prevent instantiation of a Validation object.
    All methods in the class are Static, so an instance of the Validation object is not required.
    */
    private Validation()
    {

    }

    /**
     * Static method that validates that a given string is not either empty or only space characters.
     * @param input The string to be validated, passed in as a String.
     * @return Returns True when the input string is not either empty or only space characters.
     */
    public static boolean isNotBlank(String input)
    {
        return (input.isEmpty() || input.isBlank() ? false : true);
    }

    /**
     * Static method that validates that a given string's length is within a selected range.
     * @param input The string to be validated, passed in as a String.
     * @param min The lower limit (inclusive) as an Integer.
     * @param max The upper limit (inclusive) as an Integer.
     * @return Returns True when the input string is within the specified range.
     */
    public static boolean isLengthWithinRange(String input, int min, int max)
    {
        return (input.length() <= max && input.length() >= min ? true : false);
    }
}