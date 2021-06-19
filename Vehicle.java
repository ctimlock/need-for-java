/**
 * Class which stores the information for individual vehicles for use in Need for Java.
 * 
 * @author Charlie Timlock
 * @version ver1.0
 */
public class Vehicle 
{
    private final String fileName = "vehicles.txt"; 
    private String type;
    private int boostSpeed;
    private int tankSize;
    private int hitPoints;
    
    /**
     * Default constructor that creates an object of the class Vehicle.
     */
    public Vehicle()
    {
        type = "car";
        boostSpeed = 3;
        tankSize = 120;
        hitPoints = 50;
    }
    

    /**
     * Non-default constructor that creates an object of the class Vehicle.
     * @param type The vehicle type/name.
     * @param boostSpeed The boost speed of the vehicle.
     * @param tankSize The maxiumum amount of fuel the vehicle can hold.
     * @param hitPoints The maximum damage the vehicle can sustain.
     */
    public Vehicle(String type, int boostSpeed, int tankSize, int hitPoints)
    {
        this.type = type;
        this.boostSpeed = boostSpeed;
        this.tankSize = tankSize;
        this.hitPoints = hitPoints;
    }

    /**
     * Accessor method to retrieve the boost speed of the vehicle object.
     * @return The boost speed, as an integer.
     */
    public int getBoostSpeed() 
    {
        return boostSpeed;  
    }

    /**
     * Accessor method for the name of the file that stores the vehicle information.
     * @return The file name, as a String.
     */
    public String getFileName() 
    {
        return fileName;
    }

    /**
     * Accessor method to retrieve the hit point max of the vehicle object.
     * @returnTthe hit points, as an integer.
     */
    public int getHitPoints() 
    {
        return hitPoints;
    }


    /**
     * Accessor method to retrieve the tank size of the vehicle object.
     * @return The tank size, as an integer.
     */
    public int getTankSize() 
    {
        return tankSize;
    }

    /**
     * Accessor method to retrieve the type of the vehicle object.
     * @return The type, as a String.
     */
    public String getType() 
    {
        return type;
    }

    /**
     * Method that multiplies the size of the vehicle's maximum fuel by a given number.
     * A value of less than one will reduce the maximum fuel (e.g tank size multiplied by 0.5 will be halved.)
     * @param multiplier The multiplier to be applied, as a Double.
     */
    public void multiplyTankSize(double multiplier)
    {
        this.tankSize *= multiplier;
    }

    /**
     * Mutator method to set the boost speed of the vehicle object.
     * @param boostSpeed The boostSpeed to set.
     */
    public void setBoostSpeed(int boostSpeed) 
    {
        this.boostSpeed = boostSpeed;
    }

    /**
     * Mutator method to set the hit points of the vehicle object.
     * @param hitPoints The hitPoints to set.
     */
    public void setHitPoints(int hitPoints) 
    {
        this.hitPoints = hitPoints;
    }

    /**
     * Mutator method to set the tank size of the vehicle object.
     * @param tankSize The tankSize to set.
     */
    public void setTankSize(int tankSize) 
    {
        this.tankSize = tankSize;
    }

    /**
     * Mutator method to set the type of the vehicle object.
     * @param type The type to set.
     */
    public void setType(String type) 
    {
        this.type = type;
    }

    /**
     * Method that provides the current state of the object as a String.
     * @return Returns the type, boost speed, tank size, and hit points as a String.
     */
    public String toString()
    {
        String output = "";
        output += "Type: " + type;
        output += " Boost Speed: " + boostSpeed;
        output += " Tank Size: " + tankSize;
        output += " Hit Points: " + hitPoints;
        return output;
    }
}