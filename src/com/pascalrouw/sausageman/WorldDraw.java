package com.pascalrouw.sausageman;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.*;

/**
 * WorldDraw is responcible for drawing the world
 * 
 * @author Melvin Versluijs
 * @author Pascal Rouw
 * 
 * @version 2013.11.24
 */
public class WorldDraw {
    //--- JSON VARS ---\\
    private int width;
    private int height;
    private int tilewidth;
    private int tileheight;
    private int version;
    private ArrayList < JsonReaderLayers > layers;
    private ArrayList < JsonReaderTilesets > tilesets;
    private String orientation;
    private String background;
    private JsonReaderProperties properties;
    
    private int xSpawn;
    private int ySpawn;
    //----------------\\
    private ArrayList < ImageIcon > tileArray;
    private WorldTiles tiles;
    private WorldTraps traps;
    private WorldScore score;
    
    /**
     * Start the constructor with getting all the tiles in an ArrayList
     */
    public WorldDraw() {
        tiles = new WorldTiles();
        ArrayList<ImageIcon> tileArray = tiles.cutTiles( "Resources/Images/mainTileset.png", 54, 69 );
        traps = new WorldTraps(tileArray);
        score = new WorldScore(tileArray);
    }
    
    /**
     * @param frame      Class Frame
     */
    public ArrayList<Surface> initSurfaceDraw(Frame frame) {
        ArrayList<Surface> surfaceLabels = tiles.drawTiles(frame, layers, tilewidth, tileheight, width);
        return surfaceLabels;
    }
    
    /**
     * @param frame      Class Frame
     */
    public ArrayList<Trap> initTrapDraw(Frame frame) {
        ArrayList<Trap> trapsLabels = traps.drawTraps(frame, layers, tilewidth, tileheight, width);
        return trapsLabels;
    }
    
    /**
     * @param frame      Class Frame
     */
    public ArrayList<Score> initScoreDraw(Frame frame) {
        ArrayList<Score> scoreLabels = score.drawScore(frame, layers, tilewidth, tileheight, width);
        return scoreLabels;
    }
    
    /**
     * @param frame      Class Frame
     */
    public void drawBackground(Frame frame) {
        ImageIcon tempImg = new ImageIcon(this.getClass().getResource("Resources/Images/" + properties.getBackground()));
        JLabel temp = new JLabel("");
        temp.setBounds(0,0,12960,720);
        temp.setIcon(tempImg);
        frame.getGamePanel().add(temp);
    }
    
    public int getXSpawn() {
        return Integer.parseInt(properties.getXSpawn());
    }
    
    public int getYSpawn() {
        return Integer.parseInt(properties.getYSpawn());
    }
    
    public String getMusic() {
        return properties.getMusic();
    }
}
