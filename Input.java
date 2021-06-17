import java.util.Scanner;

public class Input 
{
    public Input()
    {

    }

    public String requestString(String message)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return console.nextLine();
    }

    public char requestChar(String message, int index)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return console.nextLine().charAt(index);
    }
}