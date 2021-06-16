public class Player 
{
    public final String ICON = "@";
    private Vehicle vehicle;
    private int position;
    private int lane;
    private int damage;
    private int fuel;

    public Player()
    {
        vehicle = new Vehicle();
        position = 0;
        lane = 0;
        damage = 0;
        fuel = 0;
    }

    public Player(Vehicle vehicle, int position, int lane, int damage, int fuel)
    {
        this.vehicle = vehicle;
        this.position = position;
        this.lane = lane;
        this.damage = damage;
        this.fuel = fuel;
    }

    public void burnFuel(int burnedFuel)
    {
        this.fuel -= burnedFuel;
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

    /**
     * Accessor method to retrieve the position of the player.
     * @return The position, returned as an integer.
     */
    public int getPosition() 
    {
        return position;
    }

    /**
     * Accessor method to retrieve the player's vehicle.
     * @return The player's vehicle, as an object of the class Vehicle.
     */
    public Vehicle getVehicle() 
    {
        return vehicle;
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