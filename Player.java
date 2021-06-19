import java.io.IOException;

/**
 * Class which contains information about the current player and their current status.
 *
 * @author Charlie Timlock
 * @version ver1.0.0
 */
public class Player 
{
    public final String ICON = "@";
    private Vehicle vehicle;
    private int position;
    private int lane;
    private int damage;
    private int fuel;
    private String flavourText;
    private String name;

    /**
     * Default constructor that creates an object of the class Player.
     */
    public Player()
    {
        vehicle = new Vehicle();
        position = 0;
        lane = 0;
        damage = 0;
        fuel = 0;
        flavourText = "";
        name = "";
    }

    /**
     * Non-default constructor that creates an object of the class Player.
     * @param vehicle The player's vehicle, as an object of the class Vehicle.
     * @param position The player's current position on the highway, as an integer.
     * @param lane The player's current lane, as an integer.
     * @param damage The player's current damage, as an integer.
     * @param fuel The player's current fuel, as an integer.
     * @param flavourText The most recent outcome of the player, as a String.
     * @param name The player's name, as a String.
     */
    public Player(Vehicle vehicle, int position, int lane, int damage, int fuel, String flavourText, String name)
    {
        this.vehicle = vehicle;
        this.position = position;
        this.lane = lane;
        this.damage = damage;
        this.fuel = fuel;
        this.flavourText = flavourText;
        this.name = name;
    }

    /**
     * Method that modifies the player's current damage.
     * @param damageChange The amount to modify the player's damage by.
     */
    public void changeDamage(int damageChange)
    {
        this.damage += damage;
    }

    /**
     * Method that modifies the player's current fuel.
     * @param fuelChange The amount to modify the player's fuel by.
     */
    public void changeFuel(int fuelChange)
    {
        this.fuel = Math.min(this.fuel + fuelChange, this.vehicle.getTankSize());
    }
    
    /**
     * Accessor method to retrieve the current damage of the player.
     * @return The damage, returned as an integer.
     */
    public int getDamage() 
    {
        return damage;
    }

    /** Accessor method to retrieve the most recent event of the player.
     * @return The event as a String. 
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

    /**
     * Accessor method to get the name of the player.
     * @return The name, as a String.
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Accessor method to retrieve the horizontal position of the player.
     * @return The position, returned as an integer.
     */
    public int getPosition() 
    {
        return position;
    }

    /**
     * Method that returns a string that describes the player's current progress in the game.
     * @return The player's current game progress, as a String.
     */
    public String getStatus()
    {
        return "Damage: " + damage + "/" + this.vehicle.getHitPoints() + "     Fuel: " + fuel + "/" + this.vehicle.getTankSize() + "     Distance: " + position;
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
     * Method to check whether the player has died in their game.
     * @return Returns true when the player has died.
     */
    public Boolean hasDied()
    {
        return (this.fuel <= 0 || this.damage >= this.vehicle.getHitPoints());
    }

    /**
     * Method that relocates the player within the highway.
     * @param horizontal How many tiles to move the player horizontally, as an int.
     * @param vertical How many tiles to move the player vertically, as an int.
     */
    public void movePlayer(int horizontal, int vertical)
    {
        this.position += horizontal;
        this.lane += vertical;
    }

    /**
     * Method that lets the player select the vehicle they'd like to use for the game.
     * @throws IOException
     * @throws NumberFormatException
     */
    public void selectVehicle()
    throws IOException, NumberFormatException
    {
        FileIO reader = new FileIO(this.vehicle.getFileName());
        String[] lines = reader.readFile().split("~");
        Vehicle[] vehicleList = new Vehicle[lines.length];
        for (int i = 0; i < vehicleList.length; i++) 
        {
            String[] attributes = lines[i].split(",");
            String type = attributes[0];
            int boostSpeed = Integer.parseInt(attributes[1]);
            int tankSize = Integer.parseInt(attributes[2]);
            int hitPoints = Integer.parseInt(attributes[3]);
            vehicleList[i] = new Vehicle(type, boostSpeed, tankSize, hitPoints);
        }

        System.out.println("24/7 NEWS RADIO: \"Our eyes in the sky have just informed us of the vehicle being driven...\"");

        for (int i = 0; i < vehicleList.length; i++) 
        {
            String vowels = "aeiou";
            String type = vehicleList[i].getType();
            int boostSpeed = vehicleList[i].getBoostSpeed();
            int tankSize = vehicleList[i].getTankSize();
            int hitPoints = vehicleList[i].getHitPoints();
            char firstLetter = vehicleList[i].getType().charAt(0);
            String pre = (vowels.indexOf(Character.toLowerCase(firstLetter)) == -1 ? "A " : "An ");
            System.out.println("\"" + pre + type + "\"");
            System.out.print("(Boost Speed: " + boostSpeed);
            System.out.print(" Tank Size: " + tankSize);
            System.out.println(" Hit Points: " + hitPoints + ")");
        }

        System.out.println("");

        for (int i = 0; i < vehicleList.length; i++) 
        {
            System.out.println("Enter " + (i + 1) + " to select the " + vehicleList[i].getType());
        }

        int choice = -1;
        while (choice < 1 || choice > vehicleList.length)
        {
            try 
            {
                choice = Input.acceptIntegerInput();
            } catch (Exception e) 
            {
                System.out.println("Error, invalid selection. Try again.");
            }
        }

        this.setVehicle(vehicleList[choice - 1]);

        System.out.print("24/7 NEWS RADIO: \"Yes, it appears that " + this.name);
        System.out.print(" is attempting to outrun police with a stolen ");
        System.out.println(vehicleList[choice - 1].getType() + "\"");

        System.out.println("");
        System.out.println("Press Enter to turn off the Radio");
        Input.acceptEmptyInput();
    }

    /**
     * Mutator method that sets the current damage of the player.
     * @param damage The damage to set, as an integer.
     */
    public void setDamage(int damage) 
    {
        this.damage = damage;
    }

    /** Mutator method to set the most recent event of the player.
     * @param flavourText The event as a String.
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
     * Mutator method to set the player's name.
     * @param name The name to set, as a String.
     */
    public void setName(String name)
    {
        this.name = name;
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
     * Method that randomly places a player in a lane on the highway.
     * @param lanes The number of lanes on the highway.
     */
    public void setStartingLane(int lanes)
    {
        this.lane = (int)(Math.random() * lanes);
    }

    /**
     * Mutator method that sets the vehicle of the player.
     * @param vehicle The vehicle to set, as an object of the class Vehicle.
     */
    public void setVehicle(Vehicle vehicle) 
    {
        this.vehicle = vehicle;
    }

    /**
     * Method that returns the state of the current Player object as a String.
     */
    public String toString()
    {
        String output = "";
        output += "Name: " + name;
        output += " Position: " + position;
        output += " Lane: " + lane;
        output += " Damage: " + damage;
        output += " Fuel: " + fuel;
        output += " Flavour Text: " + flavourText;
        output += " Vehicle: " + vehicle.toString();
        return output;
    }
}