import java.io.IOException;

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
            System.out.print("\"" + pre + type + "\"");
            System.out.print("(Boost Speed: " + boostSpeed);
            System.out.print(" Tank Size: " + tankSize);
            System.out.println(" Hit Points: " + hitPoints);
        }

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
     * @return the name
     */
    public String getName() 
    {
        return name;
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
     * @param name the name to set
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
}