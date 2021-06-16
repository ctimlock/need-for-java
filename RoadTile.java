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
        icon = " ";
        healthMod = 0;
        fuelMod = 0;
    }

    public RoadTile(String tileType, String icon, int healthMod, int fuelMod)
    {
        this.tileType = tileType;
        this.icon = icon;
        this.healthMod = healthMod;
        this.fuelMod = fuelMod;
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

    public void setTileToObstacle(String newTileType)
    {
        String newIcon = "";
        int newHealthMod = 0;
        int newFuelMod = 0;

        switch (newTileType)
        {
            case "Road":
                newIcon = " ";
                newHealthMod = 0;
                newFuelMod = 0;
                break;

            case "Fuel":
                newIcon = "F";
                newHealthMod = 0;
                newFuelMod = 10;
                break;
                
            case "Roadblock":
                newIcon = "B";
                newHealthMod = -20;
                newFuelMod = 0;
                break;
            
            case "Tyre Spikes":
                newIcon = "S";
                newHealthMod = -45;
                newFuelMod = 0;
                break;
                
            case "Manhole":
                newIcon = "O";
                newHealthMod = -60;
                newFuelMod = 0;
                break;
        }

        this.tileType = newTileType;
        this.icon = newIcon;
        this.healthMod = newHealthMod;
        this.fuelMod = newFuelMod;
    }

    /**
     * @param tileType the tileType to set
     */
    public void setTileType(String tileType) 
    {
        this.tileType = tileType;
    }    
    
}