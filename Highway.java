/**
 * Class which stores the information for the highway the player traverses in Need for Java.
 * 
 * @author Charlie Timlock
 * @version ver1.0
 */
public class Highway 
{
    private int height;
    private int length;
    private RoadTile[][] tiles;

    /**
     * Default constructor that creates an object of the class Highway.
     */
    public Highway()
    {
        height = 1;
        length = 1;
        tiles = new RoadTile[length + 20][height];
        for (int y = 0; y < height; y++) 
        {
            for (int x = 0; x < length + 20; x++) 
            {
                tiles[x][y] = new RoadTile();
            }
        }
    }    

    /**
     * Non default constructor that creates an object of the class Highway. 20 "Border" tiles are added automatically to the length of the highway.
     * @param length The length of the highway that is to be traversed, as an integer.
     * @param height The number of lanes of the highway that can be traversed, as an integer.
     */
    public Highway(int length, int height)
    {
        this.height = height;
        this.length = length;
        tiles = new RoadTile[length + 20][height];
        for (int y = 0; y < height; y++) 
        {
            for (int x = 0; x < length + 20; x++) 
            {
                tiles[x][y] = new RoadTile();
            }
        }
    }

    /**
     * Method which adds a number of randomised obstacles to an exisiting highway. The first three highway sections will not have obstacles placed.
     * @param obstacleNumber The number of obstacles to be added, as an integer.
     */
    public void generateObstacles(int obstacleNumber)
    {
        for (int i = 0; i < obstacleNumber; i++) 
        {
            boolean success = false;
            while(!success)
            {
                int randomX = (int)(Math.random() * (this.length - 3) + 3);
                int randomY = (int)(Math.random() * this.height);

                //Checks whether tile is already an obstacle.
                if (this.getSpecificTile(randomX, randomY).getTileType().equals("Road"))
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
                    this.getSpecificTile(randomX, randomY).setTileToObstacle(newTileType);
                    success = true;
                }
            }
        }
        for (int a = this.length; a < this.length + 20; a++) 
        {
            for (int b = 0; b < height; b++)
            {
                this.getSpecificTile(a, b).setTileToObstacle("Border");
            }
        }
    }

    /**
     * Accessor method that returns the number of lanes of the highway that can be traversed.
     * @return The number of lanes, as an integer.
     */
    public int getHeight() 
    {
        return height;
    }

    /**
     * A method for generating the lane dividers for between the road tiles.
     * @param number The horizonta number of dividers to be returned.
     * @param symbol The symbol to be used.
     * @return Returns the specified number of the specified symbol, separated by spaces as a String.
     */
    public String getLaneMarkers(int number, char symbol)
    {
        String output = "";
        for (int i = 0; i < number; i++) 
        {
            output += symbol + " ";
        }
        return output;
    }

    /**
     * Accessor method that returns the length of the highway that is to be traversed.
     * @return The length of the highway, as an integer.
     */
    public int getLength() 
    {
        return length;
    }

    /**
     * Accessor method that returns a road tile from a specified location on the highway.
     * @param x The horizontal road segment, as an int, starting from 0 for the first segment.
     * @param y The vertical postition, as an int, starting from 0 for top lane.
     * @return The specified road tile, as an object of the class RoadTile.
     */
    public RoadTile getSpecificTile(int x, int y)
    {
        return tiles[x][y];
    }

    /**
     * Accessor method that returns the damage done when landing on the road tile from a specified location on the highway.
     * @param x The horizontal road segment, as an int, starting from 0 for the first segment.
     * @param y The vertical postition, as an int, starting from 0 for top lane.
     * @return The damage value as an integer.
     */
    public int getSpecificTileDamage(int x, int y)
    {
        return tiles[x][y].getDamage();
    }

    /**
     * Accessor method that returns the fuel change done when landing on the road tile from a specified location on the highway.
     * @param x The horizontal road segment, as an int, starting from 0 for the first segment.
     * @param y The vertical postition, as an int, starting from 0 for top lane.
     * @return Fuel change value, as an integer.
     */
    public int getSpecificTileFuelMod(int x, int y)
    {
        return tiles[x][y].getFuelMod();
    }

    /**
     * Accessor method that returns the name of the road tile from a specified location on the highway.
     * @param x The horizontal road segment, as an int, starting from 0 for the first segment.
     * @param y The vertical postition, as an int, starting from 0 for top lane.
     * @return The type of tile, as a String.
     */
    public String getSpecificTileTileType(int x, int y)
    {
        return tiles[x][y].getTileType();
    }    

    // NOTE: I don't think that the following Methods should be included - they do not have any effect by themselves.
    // The highway should be re-initialized with new height and length values, as the 2D array will no longer function.
    // Additionally, the getTiles() accessor breaks encapsulation, as all RoadTile generation should be done by the Highway.

    /**
     * Accessor method that returns the Road Tiles that make up the highway.
     * @return The tiles, as a 2 dimenstional array of the object RoadTile.
     */
    public RoadTile[][] getTiles() 
    {
        return tiles;
    }

    /**
     * Mutator method that sets the number of lanes of the highway that can be traversed.
     * @param height The number of lanes, as an integer.
     */
    public void setHeight(int height) 
    {
        this.height = height;
    }

    /**
     * Mutator method that sets the length of the highway that is to be traversed.
     * @param length The length of the highway, as an integer.
     */
    public void setLength(int length) 
    {
        this.length = length;
    }

    /**
     * Mutator method that sets the Road Tiles that make up the highway.
     * @param tiles The tiles, as a 2 dimenstional array of the object RoadTile.
     */
    public void setTiles(RoadTile[][] tiles) 
    {
        this.tiles = tiles;
    }

    /**
     * A method that returns the state of the Highway object as a String.
     * @return The length, height, number of traversible tiles, and number of non-traversible tiles, as a String.
     */
    public String toString()
    {
        String output = "";
        output += "Length: " + this.length;
        output += " Height: " + this.height;
        output += " Traversible Tiles: " + (this.length * this.height);
        output += " Non-traversible Border Tiles: " + (20 * this.height);
        return output;
    }
}
