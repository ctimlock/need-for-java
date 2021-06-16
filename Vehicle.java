/**
 * Car
 */
public class Vehicle 
{
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
     * @param type
     * @param boostSpeed
     * @param tankSize
     * @param hitPoints
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
}