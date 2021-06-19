/**
 * Class which stores the information for a specific tile of the Need for Java highway.
 * 
 * @author Charlie Timlock
 * @version ver1.0
 */
public class RoadTile 
{
    private String tileType;
    private String icon;
    private int damage;
    private int fuelMod;

    /**
     * Default constructor that creates an object of the class Roadtile.
     */
    public RoadTile()
    {
        tileType = "Road";
        icon = " ";
        damage = 0;
        fuelMod = 0;
    }

    /**
     * Non-default constructor that creates an object of the class Game.
     * @param tileType The type of tile being created, as a String.
     * @param icon The render icon of the tile being created, as a String.
     * @param damage The damage done when landing on the tile, as an integer.
     * @param fuelMod The change in fuel when landing on the tile, as an integer.
     */
    public RoadTile(String tileType, String icon, int damage, int fuelMod)
    {
        this.tileType = tileType;
        this.icon = icon;
        this.damage = damage;
        this.fuelMod = fuelMod;
    }

    /**
     * Accessor method for the damage done when landing on the tile.
     * @return The damage value as an integer.
     */
    public int getDamage() 
    {
        return damage;
    }

    /**
     * Accessor method for the fuel change done when landing on the tile.
     * @return Fuel change value, as an integer.
     */
    public int getFuelMod() 
    {
        return fuelMod;
    }

    /**
     * Accessor method for icon to render for a tile for the game display.
     * @return The render icon of the tile, as a String.
     */
    public String getIcon() 
    {
        return icon;
    }

    /**
     * Accessor method for the name/type of the tile.
     * @return The type of tile, as a String.
     */
    public String getTileType() 
    {
        return tileType;
    }

    /**
     * Mutator method for the damage done when landing on the tile.
     * @param damage The damage value as an integer.
     */
    public void setDamage(int damage) 
    {
        this.damage = damage;
    }

    /**
     * Mutator method for the damage done when landing on the tile.
     * @param fuelMod Fuel change value, as an integer.
     */
    public void setFuelMod(int fuelMod) 
    {
        this.fuelMod = fuelMod;
    }

    /**
     * Mutator method for icon to render for a tile for the game display.
     * @param icon The render icon of the tile, as a String.
     */
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    /**
     * Method which sets all values for the Road Tile to match an established template, depending on the String value that is passed in.
     * Valid string types to pass in are "Road", "Fuel", "Roadblock", "Tyre Spikes", "Manhole", and "Border".
     * Passing in an invalid string will set the tile to the default, which is "Road".
     * @param newTileType The tile type being set.
     */
    public void setTileToObstacle(String newTileType)
    {
        String newIcon = "";
        int newDamage = 0;
        int newFuelMod = 0;

        switch (newTileType.toLowerCase())
        {
            case "road":
                newIcon = " ";
                newDamage = 0;
                newFuelMod = 0;
                break;

            case "fuel":
                newIcon = "F";
                newDamage = 0;
                newFuelMod = 10;
                break;
                
            case "roadblock":
                newIcon = "B";
                newDamage = 20;
                newFuelMod = 0;
                break;
            
            case "tyre Spikes":
                newIcon = "S";
                newDamage = 45;
                newFuelMod = 0;
                break;
                
            case "manhole":
                newIcon = "O";
                newDamage = 60;
                newFuelMod = 0;
                break;
            
            case "border":
                newIcon = ">";
                newDamage = 0;
                newFuelMod = 0;
                break;

            default:
                newTileType = "Road";
                newIcon = " ";
                newDamage = 0;
                newFuelMod = 0;
                break;
        }

        this.tileType = newTileType;
        this.icon = newIcon;
        this.damage = newDamage;
        this.fuelMod = newFuelMod;
    }

    /**
     * Mutator method for the name/type of the tile.
     * @param tileType The type of tile, as a String.
     */
    public void setTileType(String tileType) 
    {
        this.tileType = tileType;
    }    
    
    /**
     * Method that returns the current state of the Road Tile.
     * @return The current state of the Road Tile as a String.
     */
    public String toString()
    {
        String output = "";
        output += "Type: " + tileType;
        output += " Icon: " + icon;
        output += " Damage Modifier: " + damage;
        output += " Fuel Modifier: " + fuelMod;
        return output;
    }
}