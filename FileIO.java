import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class which reads and writes to files.
 * 
 * @author Charlie Timlock
 * @version ver1.0
 */
public class FileIO 
{
    private String fileName;

    public FileIO()
    {
        fileName = "";
    }

    public FileIO(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Accessor method to retrieve the file name.
     * @return The File name, returned as a string.
     */
    public String getFileName() 
    {
        return fileName;
    }

    /**
     * Method which reads a specified file and returns the contents, delmited per line with a tilde ("~").
     * @return Returns the contents of the file as a String.
     * @throws IOException
     */
    public String readFile()
    throws IOException
    {
        String output = "";
        try 
        {
            FileReader reader = new FileReader(this.fileName);
            Scanner scanner = new Scanner(reader);

            try 
            {
                while (scanner.hasNextLine()) 
                {
                    output = output + scanner.nextLine() + "~";
                }
            } 
            catch (Exception e) 
            {
                throw e;
            }
            finally 
            {
                try 
                {
                    scanner.close();
                    reader.close();
                } 
                catch (Exception e) 
                {
                    throw e;
                }
            }
        } 
        catch (IOException e) 
        {
            throw e;
        }
        return output;
    }

    /**
     * Mutator method to set the name of the file to be accessed.
     * @param fileName The file name, as a String.
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Method which accepts a string, and appends it to a file.
     * @param inputString The string to be appended.
     * @throws IOException
     */
    public void appendFile(String inputString)
    throws IOException
    {
        try (FileWriter writer = new FileWriter(fileName, true))
        {
            writer.append(inputString);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}