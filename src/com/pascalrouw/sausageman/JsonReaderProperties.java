package com.pascalrouw.sausageman;

/**
 * JsonReader for the part "properties"
 * in the map.json file
 * 
 * @author Melvin Versluijs
 * @author Pascal Rouw
 * 
 * @version 2013.12.03
 */
public class JsonReaderProperties
{
    private String background;
    private String xSpawn;
    private String ySpawn;
    private String music;
    
    /**
     * Returns String background
     */
    public String getBackground() {
        return background;
    }
    
    /**
     * Returns String xSpawn
     */
    public String getXSpawn() {
        return xSpawn;
    }
    
    /**
     * Returns String ySpawn
     */
    public String getYSpawn() {
        return ySpawn;
    }
    
    /**
     * Returns String music
     */
    public String getMusic() {
        return music;
    }
}
