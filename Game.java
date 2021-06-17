public class Game
{
    private Player player;
    private Highway highway;
    private final int RENDER_DISTANCE = 45;

    /**
     * Default constructor that creates an object of the class Game.
     */
    public Game()
    {
        player = new Player();
        highway = new Highway();
    }

    /**
     * 
     * @param difficulty
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

        this.nukeConsole(25);
        System.out.println("You have chosen " + choice + ".");
        System.out.println("It's " + this.highway.getLength() + " KM to make it to the border.");
        System.out.println("There's about " + this.player.getFuel() + " litres of fuel in the tank.");
        System.out.println("Good luck. Don't get caught.");
        System.out.println("Press enter to start.");
        Input.acceptStringInput();
        this.nukeConsole(25);
    }

    public void boost()
    {
        int x = this.player.getVehicle().getBoostSpeed();
        for (int i = 0; i < x; i++) 
        {
            this.player.movePlayer(1, 0);
        }
        this.player.changeFuel(-x * 3);
    }

    public void calculateEffect()
    {
        this.player.changeFuel(this.highway.getSpecificTileFuelMod(this.player.getPosition(), this.player.getLane()));
        this.player.changeDamage(this.highway.getSpecificTileDamage(this.player.getPosition(), this.player.getLane()));
    }

    public void endTurn()
    {
        this.hasWon();
        this.calculateEffect();
        this.hasLost();
        this.setCurrentFlavourText();
    }

    /**
     * @return the highway
     */
    public Highway getHighway() 
    {
        return this.highway;
    }


    /**
     * Method that returns the player.
     * @return The player class Player.
     */
    public Player getPlayer() 
    {
        return player;
    }

    public void getPlayerName() 
    {
        System.out.println("We've just gotten confirmation of the identity of the driver:");
        String name = "";
        Boolean flag = true;
        while(flag)
        {
            name = Input.acceptStringInput();
            if (Validation.isLengthWithinRange(name, 3, 12))
            {
                this.player.setName(name);
                flag = false;
                System.out.println("Yes, it appears that the driver of the vehicle is none other than " + name + ", the notorious fugitive.");
                this.nukeConsole(1);
            }
            else
            {
                System.out.println("My apologies, that appears to be a incorrect. (Must be between 3 and 12 characters long.");
            }
        }
        System.out.println("Press Enter to turn off the radio.");
        Input.acceptEmptyInput();
    }

    public void hasLost()
    {
        if(this.player.hasDied())
        {
            System.out.println("Sucked in idiot! You lose.");
            // TODO: death actions.
        }
    }


    public void hasWon()
    {
        if (this.player.getPosition() >= this.highway.getLength())
        {
            System.out.println("Congratulations! You win.");
            // TODO: Add more stuff.
        }
    }

    public static void main(String[] args) 
    {
        Game game = new Game();
        game.player.setStartingLane(game.highway.getHeight());
        game.getPlayerName();
        game.setDifficulty();
        while (!game.player.hasDied())
        {
        game.takeTurn();
        }
    }

    /**
     * Method that increases the position of the player's player by one space.
     */
    public void moveForward()
    {
        this.player.movePlayer(1, 0);
        this.player.changeFuel(-1);
    }

    public void nukeConsole(int lines)
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

    public void setCurrentFlavourText()
    {
        String output = "";

        switch (this.highway.getSpecificTileTileType(this.player.getPosition(), this.player.getLane())) 
        {
            case "Road":
                output = "";
                break;

            case "Fuel":
                output = "You picked up " + this.highway.getSpecificTileFuelMod(this.player.getPosition(), this.player.getLane()) + " fuel.";
                break;
                
            case "Roadblock":
                output = "Careful, you hit a roadblock. You took " + this.highway.getSpecificTileDamage(this.player.getPosition(), this.player.getLane()) + " damage.";
                break;
            
            case "Tyre Spikes":
                output = "Ouch, you ran over some tyre spikes. You took " + this.highway.getSpecificTileDamage(this.player.getPosition(), this.player.getLane()) + " damage.";
                break;
                
            case "Manhole":
                output = "OOFT, you hit an open manhole! You took " + this.highway.getSpecificTileDamage(this.player.getPosition(), this.player.getLane()) + " damage!";
                break;
        }
        this.player.setFlavourText(output);
    }

    public void setDifficulty()
    {
        int difficulty = 0;
        while (difficulty == 0)
        {
            System.out.println("Choose your difficulty: Easy, Moderate, or Hard.");
            switch (Character.toLowerCase(Input.acceptCharInput(0)))
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
        this.applyDifficulty(difficulty);
    }

    /**
     * @param highway the highway to set
     */
    public void setHighway(Highway highway) 
    {
        this.highway = highway;
    }

    /**
     * Mutator method to set the player.
     * @param player The player to set, passed in as an object of the class Player. 
     */
    public void setPlayer(Player player) 
    {
        this.player = player;
    }

    public void startGame()
    {
        this.setDifficulty();

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

    public void takeTurn()
    {
        // Nuke the existing graphics.
        this.nukeConsole(25);

        // Render the highway and player status.
        this.renderHighway();
        this.nukeConsole(1);
        System.out.println(this.player.getFlavourText());
        this.nukeConsole(1);
        System.out.println(this.player.getStatus());
        this.nukeConsole(1);

        boolean canSwerveUp = (this.player.getLane() == 0 ? false : true);
        boolean canSwerveDown = (this.player.getLane() == this.highway.getHeight() - 1 ? false : true);
        String swerveUpMessage = "Enter 1 to swerve up a lane.";
        String swerveDownMessage = "Enter 2 to swerve down a lane.";
        String moveForwardMessage = "Enter 3 to move forward by 1 space.";
        String boostMessage = "Enter 4 to boost ahead " + this.player.getVehicle().getBoostSpeed() + " spaces.";

        // Present viable options.
        if (canSwerveUp == false)
        {
            System.out.println("");
            System.out.println(swerveDownMessage);
            System.out.println(moveForwardMessage);
            System.out.println(boostMessage);
        } 
        else if (canSwerveDown == false)
        {
            System.out.println(swerveUpMessage);
            System.out.println("");
            System.out.println(moveForwardMessage);
            System.out.println(boostMessage);
        }
        else
        {
            System.out.println(swerveUpMessage);
            System.out.println(swerveDownMessage);
            System.out.println(moveForwardMessage);
            System.out.println(boostMessage);
        }

        this.nukeConsole(1);
        
        // Request player choice and check it's valid.
        int choice = 0;
        Boolean flag = true;
        while (flag) 
        {
            try 
            {
                choice = Input.acceptIntegerInput();
                if ((!canSwerveUp && choice == 1) || (!canSwerveDown && choice == 2) || choice > 4 || choice < 1)
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
                this.swerve("down");
                break;
            case 3:
                this.moveForward();
                break;
            case 4:
                this.boost();
                break;
        
            default:
                this.moveForward();
                break;
        }

        // Perform end of turn checks.
        this.endTurn();
    }
}