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
        for (int x = 0; x < length; x++) 
        {
            for (int y = 0; y < height; y++) 
            {
                tiles[x][y] = new RoadTile();
            }
        }
    }

    public Highway(int length, int height)
    {
        this.height = height;
        this.length = length;
        tiles = new RoadTile[height][length];
        for (int i = 0; i < height; i++) 
        {
            for (int j = 0; j < length; j++) 
            {
                tiles[i][j] = new RoadTile();
            }
        }
    }

    public RoadTile getSpecificTile(int x, int y)
    {
        return tiles[x][y];
    }

    public void renderHighway(int position, int viewLength)
    {
        int renderLength = Math.min(viewLength, length);

        System.out.println(this.getLaneMarkers(viewLength, '='));

        for (int y = 0; y < this.height; y++) 
        {
            for (int x = position; x < renderLength; x++) 
            {
                System.out.print(tiles[y][x].getIcon() + " ");
            }
            System.out.println("");

            if (y < this.height - 1)
            {
                System.out.println(this.getLaneMarkers(viewLength, '-'));
            }          
        }
        
        System.out.println(this.getLaneMarkers(viewLength, '='));
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
