/**
 * RoadTile
 */
public class RoadTile 
{
    private String tileType;
    private String icon;
    private int healthMod;
    private int fuelMod;

    public RoadTile()
    {
        tileType = "Road";
        icon = "x";
        healthMod = 0;
        fuelMod = 0;
    }

    /**
     * @return the fuelMod
     */
    public int getFuelMod() 
    {
        return fuelMod;
    }

    /**
     * @return the healthMod
     */
    public int getHealthMod() 
    {
        return healthMod;
    }

    /**
     * @return the icon
     */
    public String getIcon() 
    {
        return icon;
    }

    /**
     * @return the tileType
     */
    public String getTileType() 
    {
        return tileType;
    }

    /**
     * @param fuelMod the fuelMod to set
     */
    public void setFuelMod(int fuelMod) 
    {
        this.fuelMod = fuelMod;
    }

    /**
     * @param healthMod the healthMod to set
     */
    public void setHealthMod(int healthMod) 
    {
        this.healthMod = healthMod;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    /**
     * @param tileType the tileType to set
     */
    public void setTileType(String tileType) 
    {
        this.tileType = tileType;
    }
    
}