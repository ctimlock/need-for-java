public class Highway 
{
    private int height;
    private int length;
    private RoadTile[][] tiles;

    public Highway()
    {
        height = 1;
        length = 1;
        tiles = new RoadTile[length][height];
        for (int y = 0; y < height; y++) 
        {
            for (int x = 0; x < length; x++) 
            {
                tiles[x][y] = new RoadTile();
            }
        }
    }    

    public Highway(int length, int height)
    {
        this.height = height;
        this.length = length;
        tiles = new RoadTile[length][height];
        for (int y = 0; y < height; y++) 
        {
            for (int x = 0; x < length; x++) 
            {
                tiles[x][y] = new RoadTile();
            }
        }
    }

    /**
     * @return the height
     */
    public int getHeight() 
    {
        return height;
    }

    /**
     * @return the length
     */
    public int getLength() 
    {
        return length;
    }

    /**
     * @return the tiles
     */
    public RoadTile[][] getTiles() 
    {
        return tiles;
    }

    public RoadTile getSpecificTile(int x, int y)
    {
        return tiles[x][y];
    }

    public String getLaneMarkers(int number, char symbol)
    {
        String output = "";
        for (int i = 0; i < number; i++) 
        {
            output += symbol + " ";
        }
        return output;
    }
}
