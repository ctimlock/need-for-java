public class Game
{
    private Player player;
    private Highway highway;
    private final int RENDER_DISTANCE = 10;

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
        this.player.burnFuel((this.player.getVehicle().getBoostSpeed() * 2));
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
            //int typeSelection = (int)(Math.random() * 10);
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
        game.player.setFuel(100);
        int obstacleNumber = 45;
        game.generateObstacles(obstacleNumber);
    }

    /**
     * Method that increases the position of the player's player by one space.
     */
    public void movePlayerForward()
    {
        int newPosition = player.getPosition() + 1;
        player.setPosition(newPosition);
        player.burnFuel(1);
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
            player.burnFuel(1);
        } 
        else 
        {
            System.out.println("Error: illegal move detected. Moving player forwards instead.");
        }
        this.movePlayerForward();
    }
}