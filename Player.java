public class Player 
{
    public final String ICON = "@";
    private Vehicle vehicle;
    private int position;
    private int lane;
    private int damage;
    private int fuel;
    private String flavourText;

    public Player()
    {
        vehicle = new Vehicle();
        position = 0;
        lane = 0;
        damage = 0;
        fuel = 0;
        flavourText = "";
    }

    public Player(Vehicle vehicle, int position, int lane, int damage, int fuel, String flavourText)
    {
        this.vehicle = vehicle;
        this.position = position;
        this.lane = lane;
        this.damage = damage;
        this.fuel = fuel;
        this.flavourText = flavourText;
    }

    public void changeFuel(int fuelChange)
    {
        this.fuel = Math.min(this.fuel + fuelChange, this.vehicle.getTankSize());
    }

    public void changeDamage(int damage)
    {
        this.damage += damage;
    }
    
    /**
     * Accessor method to retrieve the current damage of the player.
     * @return The damage, returned as an integer.
     */
    public int getDamage() 
    {
        return damage;
    }

    /**
     * @return the flavourText
     */
    public String getFlavourText() 
    {
        return flavourText;
    }

    /**
     * Accessor method to retrieve the fuel level of the player.
     * @return The fuel, returned as an integer.
     */
    public int getFuel() 
    {
        return fuel;
    }


    /**
     * Accessor method to retrieve the current lane of the player.
     * @return The lane, returned as an integer.
     */
    public int getLane() 
    {
        return lane;
    }
    

    public int[] getLocation()
    {
        int[] location = {this.position, this.lane};
        return location;
    } 

    /**
     * Accessor method to retrieve the position of the player.
     * @return The position, returned as an integer.
     */
    public int getPosition() 
    {
        return position;
    }

    public String getStatus()
    {
        return "Distance: " + position + "     Damage: " + damage + "/" + this.vehicle.getHitPoints() + "     Fuel: " + fuel + "/" + this.vehicle.getTankSize();
    }

    /**
     * Accessor method to retrieve the player's vehicle.
     * @return The player's vehicle, as an object of the class Vehicle.
     */
    public Vehicle getVehicle() 
    {
        return vehicle;
    }

    public Boolean hasDied()
    {
        return (this.fuel <= 0 || this.damage >= this.vehicle.getHitPoints());
    }

    public void movePlayer(int horizontal, int vertical)
    {
        this.position += horizontal;
        this.lane += vertical;
    }

    /**
     * Mutator method that sets the current damage of the player.
     * @param damage The damage to set, as an integer.
     */
    public void setDamage(int damage) 
    {
        this.damage = damage;
    }

    /**
     * @param flavourText the flavourText to set
     */
    public void setFlavourText(String flavourText) 
    {
        this.flavourText = flavourText;
    }

    /**
     * Mutator method that sets the current fuel of the player.
     * @param fuel The fuel to set, as an integer.
     */
    public void setFuel(int fuel) 
    {
        this.fuel = fuel;
    }


    /**
     * Mutator method that sets the vertical position (lane) of the player.
     * @param lane The lane to set, as an integer.
     */
    public void setLane(int lane) 
    {
        this.lane = lane;
    }

    /**
     * Mutator method that sets the horizontal position of the player.
     * @param position The position to set, as an integer.
     */
    public void setPosition(int position)
    {
        this.position = position;
    }

    /**
     * Mutator method that sets the vehicle of the player.
     * @param vehicle The vehicle to set, as an object of the class Vehicle.
     */
    public void setVehicle(Vehicle vehicle) 
    {
        this.vehicle = vehicle;
    }
}