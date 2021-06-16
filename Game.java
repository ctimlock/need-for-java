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
        highway = new Highway(45, 3);
    }

    public void boost()
    {
        for (int i = 0; i < this.player.getVehicle().getBoostSpeed(); i++) 
        {
            this.movePlayerForward();
        }
        this.player.changeFuel((this.player.getVehicle().getBoostSpeed() * 2));
    }

    public void calculateEffect()
    {
        RoadTile currentTile = this.highway.getSpecificTile(this.player.getPosition(), this.player.getLane());
        this.player.changeFuel(currentTile.getFuelMod());
        this.player.changeDamage(currentTile.getHealthMod());
    }

    /**
     * Method which adds a set number of obstacles to an exisiting highway. The first three highway sections will not have obstacles placed.
     * @param obstacleNumber The number of obstacles to be added
     */
    public void generateObstacles(int obstacleNumber)
    {
        for (int i = 0; i < obstacleNumber; i++) 
        {
            boolean success = false;
            while(!success)
            {
                int randomX = (int)(Math.random() * (this.highway.getLength() - 3) + 3);
                int randomY = (int)(Math.random() * this.highway.getHeight());

                //Checks whether tile is already an obstacle.
                if (this.highway.getSpecificTile(randomX, randomY).getTileType().equals("Road"))
                {
                    int selection = (int)(Math.random() * 10);
                    String newTileType = "";
                    switch (selection) 
                    {
                        case 0:
                        case 1:
                        case 2:
                            newTileType = "Fuel";
                            break;
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            newTileType = "Roadblock";
                            break;
                        case 7:
                        case 8:
                            newTileType = "Tyre Spikes";
                            break;

                        case 9:
                            newTileType = "Manhole";
                            break;
                    }
                    this.highway.getSpecificTile(randomX, randomY).setTileToObstacle(newTileType);
                    success = true;
                }
            }
        }
    }

    /**
     * Method that returns the player.
     * @return The player class Player.
     */
    public Player getPlayer() 
    {
        return player;
    }

    public static void main(String[] args) 
    {
        Game game = new Game();
        game.setDifficulty(2);
    }

    /**
     * Method that increases the position of the player's player by one space.
     */
    public void movePlayerForward()
    {
        int newPosition = player.getPosition() + 1;
        player.setPosition(newPosition);
        player.changeFuel(-1);
        calculateEffect();
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

        int remaining = this.highway.getLength() - this.player.getPosition();
        int renderLength = Math.min(RENDER_DISTANCE, remaining);

        // Inserts the upper border of the highway.
        System.out.println(this.highway.getLaneMarkers(renderLength, '='));

        for (int y = 0; y < this.highway.getHeight(); y++) 
        {
            for (int x = 0; x < renderLength; x++) 
            {
                // Begins printing road tiles between player's position and either the render distance, or the end of the highway.
                System.out.print(this.highway.getSpecificTile(x + this.player.getPosition(), y).getIcon() + " ");
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

        this.generateObstacles(obstacles);

        int tankSize = (int)(this.player.getVehicle().getTankSize() * fuelLimiter);
        this.player.getVehicle().setTankSize(tankSize);
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
        int upDownInt = 0;
        switch (upOrDown.toLowerCase())
        {
            case "up":
                upDownInt = -1;
                break;

            case "down":
                upDownInt = 1;
                break;
        
            default:
                break;
        }

        int newLane = player.getLane() + upDownInt;

        if (newLane < this.highway.getHeight() && newLane >= 0) 
        {
            player.setLane(newLane);
            player.changeFuel(-1);
        } 
        else 
        {
            System.out.println("Error: illegal move detected. Moving player forwards instead.");
        }
        this.movePlayerForward();
    }
}