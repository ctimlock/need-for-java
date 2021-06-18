import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * FileIO
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

    public String readFile()
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
                System.out.println("Error, file could not be read.");
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
                    System.out.println("Error, file did not close correctly.");
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error, file could not be accessed.");
        }
        return output;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public void writeFile(String inputString) 
    {
        try (FileWriter writer = new FileWriter(fileName, true))
        {
            //writer.write(inputString);
            writer.append(inputString);
        }
        catch (Exception e)
        {
            System.out.println("Error, could not write to file.");
        }
    }
}