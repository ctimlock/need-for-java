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

    public void boost()
    {
        int x = this.player.getVehicle().getBoostSpeed();
        for (int i = 0; i < x; i++) 
        {
            this.player.movePlayer(1, 0);
        }
        this.player.changeFuel(-x * 3);
        this.endTurn();
    }

    public void calculateEffect()
    {
        RoadTile currentTile = this.highway.getSpecificTile(this.player.getPosition(), this.player.getLane());
        this.player.changeFuel(currentTile.getFuelMod());
        this.player.changeDamage(currentTile.getHealthMod());
    }

    public void checkLose()
    {
        if(this.player.hasDied())
        {
            System.out.println("Sucked in idiot! You lose.");
            // TODO: death actions
        }
    }

    public void checkWin()
    {
        if (this.player.getPosition() == this.highway.getLength())
        {
            System.out.println("Congratulations! You win.");
            // TODO: Add more stuff.
        }
    }

    public void endTurn()
    {
        this.checkWin();
        this.checkLose();
        this.calculateEffect();
    }

    /**
     * Method that returns the player.
     * @return The player class Player.
     */
    public Player getPlayer() 
    {
        return player;
    }

    /**
     * @return the highway
     */
    public Highway getHighway() 
    {
        return this.highway;
    }

    public static void main(String[] args) 
    {
        Game game = new Game();
        game.setDifficulty(2);
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
        this.endTurn();
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

        int remaining = this.highway.getLength() - playerX;
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
     * 
     * @param difficulty
     */
    public void setDifficulty(int difficulty)
    {
        int lengthMin = 0;
        int lengthMax = 0;
        double fuelLimiter = 0.0;
        int obstacles = 0;

        switch (difficulty) 
        {
            case 1:
                lengthMin = 10;
                lengthMax = 15;
                fuelLimiter = 1.0;
                obstacles = 12;
                break;

            case 2:
                lengthMin = 15;
                lengthMax = 30;
                fuelLimiter = 0.8;
                obstacles = 24;
                break;

            case 3:
                lengthMin = 30;
                lengthMax = 50;
                fuelLimiter = 0.5;
                obstacles = 45;
                break;
        
            default:
                lengthMin = 10;
                lengthMax = 15;
                fuelLimiter = 1.0;
                obstacles = 12;
                break;
        }

        // Reinitialise the highway with new length.
        int length = (int) (Math.random() * (lengthMax - lengthMin) + lengthMin);
        Highway highway = new Highway(length, 3);
        this.setHighway(highway);

        this.highway.generateObstacles(obstacles);

        this.player.getVehicle().multiplyTankSize(fuelLimiter);
        this.player.changeFuel(9999);
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

        this.endTurn();
    }

    public void takeTurn()
    {
        // Nuke the existing graphics.
        for (int i = 0; i < 25; i++) 
        {
            System.out.println("");
        }

        // Render the highway and player status.
        this.renderHighway();
        System.out.println("");
        System.out.println(this.player.getStatus());

        boolean canSwerveUp = (this.player.getLane() == 0 ? false : true);
        boolean canSwerveDown = (this.player.getLane() == this.highway.getHeight() - 1 ? false : true);
        String swerveUpMessage = "Enter 1 to swerve up a lane.";
        String swerveDownMessage = "Enter 2 to swerve down a lane.";
        String moveForwardMessage = "Enter 3 to move forward by 1 space.";
        String boostMessage = "Enter 4 to boost ahead " + this.player.getVehicle().getBoostSpeed() + " spaces.";

        // Present viable options.
        if (canSwerveUp == false)
        {
            System.out.println(swerveDownMessage);
            System.out.println(moveForwardMessage);
            System.out.println(boostMessage);
        } 
        else if (canSwerveDown == false)
        {
            System.out.println(swerveUpMessage);
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
        
        // Request player choice and check it's valid.
        Input input = new Input();
        int choice = 0;
        Boolean flag = true;
        while (flag) 
        {
            try 
            {
                choice = input.acceptIntegerInput();
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