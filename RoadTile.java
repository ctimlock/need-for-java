/**
 * RoadTile
 */
public class RoadTile 
{
    private String tileType;
    private String icon;
    private int damage;
    private int fuelMod;

    public RoadTile()
    {
        tileType = "Road";
        icon = " ";
        damage = 0;
        fuelMod = 0;
    }

    public RoadTile(String tileType, String icon, int damage, int fuelMod)
    {
        this.tileType = tileType;
        this.icon = icon;
        this.damage = damage;
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
     * @return the damage
     */
    public int getDamage() 
    {
        return damage;
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
     * @param damage the damage to set
     */
    public void setDamage(int damage) 
    {
        this.damage = damage;
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
        int newDamage = 0;
        int newFuelMod = 0;

        switch (newTileType)
        {
            case "Road":
                newIcon = " ";
                newDamage = 0;
                newFuelMod = 0;
                break;

            case "Fuel":
                newIcon = "F";
                newDamage = 0;
                newFuelMod = 10;
                break;
                
            case "Roadblock":
                newIcon = "B";
                newDamage = 20;
                newFuelMod = 0;
                break;
            
            case "Tyre Spikes":
                newIcon = "S";
                newDamage = 45;
                newFuelMod = 0;
                break;
                
            case "Manhole":
                newIcon = "O";
                newDamage = 60;
                newFuelMod = 0;
                break;
        }

        this.tileType = newTileType;
        this.icon = newIcon;
        this.damage = newDamage;
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