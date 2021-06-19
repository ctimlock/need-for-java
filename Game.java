/**
 * Class which runs the Need for Java game, and handles player interaction.
 *
 * @author Charlie Timlock
 * @version ver1.0.0
 */

public class Game
{
    private Player player;
    private Highway highway;
    private final int RENDER_DISTANCE = 10;
    private final String OUTPUT_FILE = "output.txt";
    private final String VEHICLES_FILE = "vehicles.txt"; 

    /**
     * Default constructor that creates an object of the class Game.
     */
    public Game()
    {
        player = new Player();
        highway = new Highway();
    }

    /**
     * Non-default constructor that creates an object of the class Game.
     * @param player The player. as an object of the class Player.
     * @param highway The highway, as an object of the class Highway.
     */
    public Game(Player player, Highway highway)
    {
        this.player = player;
        this.highway = highway;
    }

    /**
     * Applies a selected difficulty to the current game.
     * @param difficulty The difficulty selection as an integer. 1 = Easy, 2 = Moderate, 3 = Hard.
     */
    public void applyDifficulty(int difficulty)
    {
        int lengthMin = 0;
        int lengthMax = 0;
        double fuelLimiter = 0.0;
        int obstacles = 0;
        String choice = "";

        switch (difficulty) 
        {
            case 1:
                lengthMin = 10;
                lengthMax = 15;
                fuelLimiter = 1.0;
                obstacles = 12;
                choice = "Easy";
                break;

            case 2:
                lengthMin = 15;
                lengthMax = 30;
                fuelLimiter = 0.8;
                obstacles = 24;
                choice = "Moderate";
                break;

            case 3:
                lengthMin = 30;
                lengthMax = 50;
                fuelLimiter = 0.5;
                obstacles = 45;
                choice = "Hard";
                break;
        
            default:
                lengthMin = 10;
                lengthMax = 15;
                fuelLimiter = 1.0;
                obstacles = 12;
                choice = "Easy";
                break;
        }

        // Reinitialise the highway with new length.
        int length = (int) (Math.random() * (lengthMax - lengthMin) + lengthMin);
        Highway highway = new Highway(length, 3);
        this.setHighway(highway);

        this.highway.generateObstacles(obstacles);

        this.player.getVehicle().multiplyTankSize(fuelLimiter);
        this.player.changeFuel(9999);

        this.pushConsole(25);
        System.out.println("It's " + this.highway.getLength() + " KM to make it to the border.");
        System.out.println("There's about " + this.player.getFuel() + " litres of fuel in the tank.");
        System.out.println("Good luck. Don't get caught.");
        this.pushConsole(2);
        System.out.println("Press enter to start.");
        Input.acceptStringInput();
        this.pushConsole(25);
    }

    /**
     * Method that calculates and applies the effect of an obstacle on the player.
     */
    public void applyEffect()
    {
        int x = this.player.getPosition();
        int y = this.player.getLane();
        this.player.changeFuel(this.highway.getSpecificTileFuelMod(x, y));
        this.player.changeDamage(this.highway.getSpecificTileDamage(x, y));
    }

    /**
     * Method that executes the "Boost" move.
     */
    public void boost()
    {
        int x = this.player.getVehicle().getBoostSpeed();
        // I have changed the boost to give a fuel discount. This makes boosting useful, when possible.
        // Spec implementation is this.player.changeFuel(-x * 3);
        this.player.changeFuel((int)(-x * .75));
        for (int i = 0; i < x; i++) 
        {
            this.player.movePlayer(1, 0);
            this.finalizeTurn();
        }
    }

    /**
     * Method that displays the current state of the game in the terminal.
     */
    public void display()
    {
        System.out.println(this.player.toString());
        System.out.println(this.highway.toString());
    }

    /**
     * Method that finalizes a player's turn.
     * Will check if the player has won, apply any obstacle outcomes, check if the player has lost, and then set the flavour text for the most recent event, in that order.
     */
    public void finalizeTurn()
    {
        if (this.hasWon())
        {
            this.printOutcomeToFile();
            this.pushConsole(25);
            System.out.println("Game Over");
            this.pushConsole(2);
            System.out.println(this.player.getFlavourText());
            this.pushConsole(4);
            System.exit(0);
        }
        this.applyEffect();
        if (this.hasLost())
        {
            this.printOutcomeToFile();
            this.pushConsole(25);
            System.out.println("Game Over");
            this.pushConsole(2);
            System.out.println(this.player.getFlavourText());
            this.pushConsole(4);
            System.exit(0);
        }
        this.setCurrentFlavourText();
    }

    /**
     * Accessor method that returns the game's highway.
     * @return The highway as an object of the class Highway.
     */
    public Highway getHighway() 
    {
        return this.highway;
    }

    /**
     * Method that returns the player.
     * @return The player as an object of the class Player.
     */
    public Player getPlayer() 
    {
        return player;
    }

    /**
     * Method that requests and sets the player's name. Also prints the in-terminal intro text.
     */
    public void getPlayerName() 
    {
        System.out.print("24/7 NEWS RADIO: \"We're coming to you with a breaking news story.\"   (Press Enter to Continue)");
        Input.acceptEmptyInput();
        System.out.print("24/7 NEWS RADIO: \"Reports are coming in that there is a high-speed police chase on the south bound highway.\"   (Press Enter to Continue)");
        Input.acceptEmptyInput();
        System.out.print("24/7 NEWS RADIO: \"Several police interceptor vehicles are in pursuit, but the driver appears to be headed towards the border.\"   (Press Enter to Continue)");
        Input.acceptEmptyInput();
        System.out.print("24/7 NEWS RADIO: \"...\"");
        Input.acceptEmptyInput();
        System.out.println("24/7 NEWS RADIO: \"We've just gotten confirmation of the identity of the driver...\"");
        this.pushConsole(1);
        System.out.println("(Enter your name)");
        
        String name = "";
        Boolean flag = true;
        while(flag)
        {
            name = Input.acceptStringInput();
            if (Validation.isLengthWithinRange(name, 3, 12))
            {
                this.player.setName(name);
                flag = false;
                this.pushConsole(1);
                System.out.println("24/7 NEWS RADIO: \"Yes, it appears that the driver of the vehicle is none other than " + name + ", the notorious fugitive.\"");
                this.pushConsole(1);
            }
            else
            {
                System.out.println("24/7 NEWS RADIO: \"My apologies, that appears to be a incorrect.\"");
                System.out.println("(Must be between 3 and 12 characters long.)");
            }
        }
        System.out.println("Press Enter to continue.");
        Input.acceptEmptyInput();
    }

    /**
     * Method that checks whether the player has lost, and will prep the game to finish if true.
     * @return Returns true if the player has lost.
     */
    public boolean hasLost()
    {
        if(this.player.hasDied())
        {
            int x = this.player.getPosition();
            int y = this.player.getLane();
            String finisher = this.highway.getSpecificTileTileType(x, y);
            int distance =  x;

            String outcome = this.player.getName() + " drove " + distance + " kilometers before the ";
            outcome += finisher.toLowerCase() + " they ran into destroyed their ";
            outcome += this.player.getVehicle().getType() + ", after which they were caught by the police.";
            this.player.setFlavourText(outcome);
            return true;
        }
        else if(this.player.hasRunOutOfFuel())
        {
            int x = this.player.getPosition();
            int distance = x;

            String outcome = this.player.getName() + " drove " + distance + " kilometers before their ";
            outcome += this.player.getVehicle().getType() + " ran out of fuel, after which they were caught by the police.";
            this.player.setFlavourText(outcome);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Method that checks whether the player has won, and will prep the game to finish if true.
     * @return Returns true if the player has won.
     * @return
     */
    public boolean hasWon()
    {
        if (this.player.getPosition() >= this.highway.getLength())
        {
            String outcome = this.player.getName() + " drove " + this.highway.getLength();
            outcome += " kilometers with their " + this.player.getVehicle().getType();
            outcome += " to outrun the cops, and made it across the border.";
            this.player.setFlavourText(outcome);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String[] args) 
    {
        Game game = new Game();
        game.startGame();
    }

    /**
     * Method that increases the position of the player's player by one space, and deducts the fuel penalty.
     */
    public void moveForward()
    {
        this.player.movePlayer(1, 0);
        this.player.changeFuel(-1);
    }

    /**
     * Method that prints the outcome of the game to the output file.
     */
    public void printOutcomeToFile()
    {
        FileIO writer = new FileIO(OUTPUT_FILE);
        try
        {
            writer.appendFile("\n" + this.player.getFlavourText());
        }
        catch (Exception e)
        {
            System.out.println("Error, could not save outcome to file.");
        }
    }

    /**
     * Method that inserts a number of empty lines in the terminal.
     * @param lines The number of empty lines to print.
     */
    public void pushConsole(int lines)
    {
        for (int i = 0; i < lines; i++) 
        {
            System.out.println("");
        }
    }

    /**
     * Method that renders the current highway.
     */
    public void renderHighway()
    {
        // Sets the tile at the player's current position to display the player icon.
        int playerX = player.getPosition();
        int playerY = player.getLane();
        highway.getSpecificTile(playerX, playerY).setIcon(player.ICON);

        int remaining = this.highway.getLength() + 3 - playerX;
        int renderLength = Math.min(RENDER_DISTANCE, remaining);

        // Inserts the upper border of the highway.
        System.out.println(this.highway.getLaneMarkers(renderLength, '='));

        for (int y = 0; y < this.highway.getHeight(); y++) 
        {
            for (int x = 0; x < renderLength; x++) 
            {
                // Begins printing road tiles between player's position and either the render distance, or the end of the highway.
                System.out.print(this.highway.getSpecificTile(x + playerX, y).getIcon() + " ");
            }
            System.out.println("");

            if (y < this.highway.getHeight() - 1)
            {
                // Inserts a lane divider between internal lanes.
                System.out.println(this.highway.getLaneMarkers(renderLength, '-'));
            }          
        }

        // Inserts the lower border of the highway.
        System.out.println(this.highway.getLaneMarkers(renderLength, '='));
    }

    /**
     * Method that requests the player's difficulty selection.
     * @return Returns the selection as an int.
     */
    public int selectDifficulty()
    {
        this.pushConsole(25);
        int difficulty = 0;
        while (difficulty == 0)
        {
            char choice = ' ';
            Boolean flag = true;
            while (flag)
            {
                try 
                {
                System.out.println("Choose your difficulty: Easy, Moderate, or Hard.");
                this.pushConsole(1);
                choice = Character.toLowerCase(Input.acceptCharInput(0));
                flag = false;
                } 
                catch (Exception e) 
                {
                    System.out.println("Error, invalid input detected. Please try again.");
                }
            }


            switch (choice)
            {
                case 'e':
                case '1':
                    difficulty = 1;
                    break;

                case 'm':
                case '2':
                    difficulty = 2;
                    break;

                case 'h':
                case '3':
                    difficulty = 3;
                    break;

                default:
                    difficulty = 0;
                    System.out.println("Invalid selection.");
                    break;
            }
        }
        return difficulty;
    }

    /**
     * Method that lets the player select the vehicle they'd like to use for the game.
     */
    public void selectVehicle(int difficulty)
    {
        FileIO reader = new FileIO(VEHICLES_FILE);
        try 
        {
            System.out.println("24/7 NEWS RADIO: \"Our eyes in the sky have just informed us of the vehicle being driven...\"");

            String[] vehicleStrings = reader.readFile().split("~");
            String[] vehicleNames = new String[vehicleStrings.length];

            for (int i = 0; i < vehicleStrings.length; i++) 
            {
                String[] attributes = vehicleStrings[i].split(",");
                vehicleNames[i] = attributes[0];
                String boostSpeed = attributes[1];
                int tankSize = Integer.parseInt(attributes[2]);
                switch (difficulty) 
                {
                    case 1:
                        tankSize *= 1;
                        break;
                
                    case 2:
                        tankSize *= 0.8;
                        break;
            
                    case 3:
                        tankSize *= 0.5;
                        break;
                
                    default:
                        break;
                }
                String hitPoints = attributes[3];

                String output = "";
                String vowels = "aeiou";
                char firstLetter = vehicleNames[i].charAt(0);
                String pre = (vowels.indexOf(Character.toLowerCase(firstLetter)) == -1 ? "A " : "An ");
                output += ("\"" + pre + vehicleNames[i] + "\"");
                output += (" (Boost Speed: " + boostSpeed);
                output += (" Tank Size: " + tankSize);
                output += (" Hit Points: " + hitPoints + ")");
                System.out.println(output);
            }

            this.pushConsole(1);
    
            for (int i = 0; i < vehicleNames.length; i++) 
            {
                System.out.println("Enter " + (i + 1) + " to select the " + vehicleNames[i]);
            }

            this.pushConsole(1);

            int choice = -1;
            while (choice < 1 || choice > vehicleNames.length)
            {
                try 
                {
                    choice = Input.acceptIntegerInput();
                } 
                catch (Exception e) 
                {
                    System.out.println("Error, invalid selection. Try again.");
                }
            }

            this.player.setVehicle(vehicleStrings[choice - 1]);
            this.pushConsole(1);    
            System.out.print("24/7 NEWS RADIO: \"Yes, it appears that " + this.player.getName());
            System.out.print(" is attempting to outrun police with a stolen ");
            System.out.println(vehicleNames[choice - 1] + "\"");
    
            System.out.println("");
            System.out.println("Press Enter to turn off the Radio");
            Input.acceptEmptyInput();

        }
        catch (Exception e) 
        {
            System.out.println("Error, could not read file correctly.");
        }
    }

    /**
     * Method that updates the most recent event of the player, for display and printing to file.
     */
    public void setCurrentFlavourText()
    {
        String output = "";
        int x = this.player.getPosition();
        int y = this.player.getLane();

        switch (this.highway.getSpecificTileTileType(x, y)) 
        {
            case "Road":
                output = "";
                break;

            case "Fuel":
                output = "You picked up " + this.highway.getSpecificTileFuelMod(x, y) + " fuel.";
                break;
                
            case "Roadblock":
                output = "Careful, you hit a roadblock. You took " + this.highway.getSpecificTileDamage(x, y) + " damage.";
                break;
            
            case "Tyre Spikes":
                output = "Ouch, you ran over some tyre spikes. You took " + this.highway.getSpecificTileDamage(x, y) + " damage.";
                break;
                
            case "Manhole":
                output = "OOFT, you hit an open manhole! You took " + this.highway.getSpecificTileDamage(x, y) + " damage!";
                break;
        }
        this.player.setFlavourText(output);
    }

    /**
     * Mutator method to set the Game's highway.
     * @param highway The highway, as an object of the class Highway.
     */
    public void setHighway(Highway highway) 
    {
        this.highway = highway;
    }

    /**
     * Mutator method to set the game's player.
     * @param player The player to set, passed in as an object of the class Player. 
     */
    public void setPlayer(Player player) 
    {
        this.player = player;
    }

    /**
     * Method that starts the game.
     */
    public void startGame()
    {
        this.pushConsole(25);
        System.out.println("    N E E D");
        System.out.println("              FOR");
        System.out.println("                    J A V A");
        this.pushConsole(5);
        System.out.println("Press Enter to begin.");
        Input.acceptEmptyInput();

        int difficulty = this.selectDifficulty();

        this.pushConsole(25);
        this.getPlayerName();

        this.pushConsole(25);
        try
        {
            this.selectVehicle(difficulty);
        }
        catch (Exception e)
        {
            System.out.println("Error, could not successfully load vehicles.");
            System.exit(0);
        }

        this.applyDifficulty(difficulty);

        this.pushConsole(25);
        
        

        this.player.setStartingLane(this.highway.getHeight());
        while (!this.player.hasDied())
        {
        this.takeTurn();
        }

    }

    /**
     * Method that moves the player up or down by a lane, and forward by one position.
     * @param upOrDown The direction for the lane change, passed in as a String of either "up" or "down"
     */
    public void swerve(String upOrDown)
    {
        switch (upOrDown)
        {
            case "up":
                    this.player.movePlayer(1, -1);
                    this.player.changeFuel(-2);
                break;

            case "down":
                    this.player.movePlayer(1, 1);
                    this.player.changeFuel(-2);
                break;
        }
    }

    /**
     * Method that requests and executes a player's turn, including updating the display.
     */
    public void takeTurn()
    {
        // push the existing graphics.
        this.pushConsole(25);

        // Render legend, the highway, and player status.

        System.out.println("F: 10 Fuel   B: Roadblock (20 dmg)   S: Tyre Spikes (45 dmg)   O: Open Manhole (60 dgm)");
        this.pushConsole(1);
        this.renderHighway();
        this.pushConsole(1);
        System.out.println(this.player.getFlavourText());
        this.pushConsole(1);
        System.out.println(this.player.getStatus() + "/" + this.highway.getLength());
        this.pushConsole(1);

        boolean canSwerveUp = (this.player.getLane() == 0 ? false : true);
        boolean canSwerveDown = (this.player.getLane() == this.highway.getHeight() - 1 ? false : true);
        String swerveUpMessage = "Enter 1 to swerve up a lane.";
        String moveForwardMessage = "Enter 2 to move forward by 1 space.";
        String swerveDownMessage = "Enter 3 to swerve down a lane.";
        String boostMessage = "Enter 4 to boost ahead " + this.player.getVehicle().getBoostSpeed() + " spaces.";

        // Present viable options.
        if (canSwerveUp == false)
        {
            System.out.println("");
            System.out.println(moveForwardMessage);
            System.out.println(swerveDownMessage);
            System.out.println(boostMessage);
        } 
        else if (canSwerveDown == false)
        {
            System.out.println(swerveUpMessage);
            System.out.println(moveForwardMessage);
            System.out.println("");
            System.out.println(boostMessage);
        }
        else
        {
            System.out.println(swerveUpMessage);
            System.out.println(moveForwardMessage);
            System.out.println(swerveDownMessage);
            System.out.println(boostMessage);
        }

        this.pushConsole(1);
        
        // Request player choice and check it's valid.
        int choice = 0;
        Boolean flag = true;
        while (flag) 
        {
            try 
            {
                choice = Input.acceptIntegerInput();
                if ((!canSwerveUp && choice == 1) || (!canSwerveDown && choice == 3) || choice > 4 || choice < 1)
                {
                    System.out.println("Wrong input! Try again.");
                } else 
                {
                    flag = false;
                }

            } 
            catch (Exception e) 
            {
                System.out.println("Wrong input! Try again.");
            }
        }

        // Execute player choice.
        switch (choice) 
        {
            case 1:
                this.swerve("up");
                break;
            case 2:
                this.moveForward();
                break;
            case 3:
                this.swerve("down");
                break;
            case 4:
                this.boost();
                break;
        
            default:
                this.moveForward();
                break;
        }

        // Perform end of turn checks.
        this.finalizeTurn();
    }
}