public class Highway 
{
    private int height;
    private int length;
    private RoadTile[][] tiles;

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

    public int getSpecificTileDamage(int x, int y)
    {
        return tiles[x][y].getDamage();
    }    

    public int getSpecificTileFuelMod(int x, int y)
    {
        return tiles[x][y].getFuelMod();
    }
    
    public String getSpecificTileTileType(int x, int y)
    {
        return tiles[x][y].getTileType();
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
