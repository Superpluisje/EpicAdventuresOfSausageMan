package com.pascalrouw.sausageman;

import com.google.gson.*;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
* WorldInit is a class for initializing the world loading sequence
* 
* @author Melvin Versluijs
* @author Pascal Rouw
*
* @version 2013.11.22
*/
public class LoadWorld {
    private Frame frame;
    
    /**
     * Create this class and make frame classwide
     * 
     * @param frame      Class Frame
     */
    public LoadWorld(Frame frame) {
        this.frame = frame;
    }
    
    /**
     * Initialize the world by reading the MAPNAME.json and putting 
     * those values in a WorldDraw object
     * 
     * @param worldName      Name of the world to be loaded
     */
    public void load(String worldName) {
        try {
            Reader reader = new InputStreamReader( this.getClass().getResourceAsStream( "Resources/Maps/" + worldName + ".json" ), "UTF-8" );
            Gson gson = new GsonBuilder().create();
            
            WorldDraw world = new WorldDraw();
            WorldDraw worldDraw = gson.fromJson( reader, world.getClass() );
            ArrayList<Surface> surfaceLabels = worldDraw.initSurfaceDraw(frame);
            ArrayList<Trap> trapLabels = worldDraw.initTrapDraw(frame);
            ArrayList<Score> scoreLabels = worldDraw.initScoreDraw(frame);
            worldDraw.drawBackground(frame);
            
            Player player = new Player(frame, surfaceLabels, trapLabels, scoreLabels);
            player.setBounds(worldDraw.getXSpawn(),worldDraw.getYSpawn(),72,72);
            player.setXSpawn(worldDraw.getXSpawn());
            player.setYSpawn(worldDraw.getYSpawn());
            player.setHealth(3);
            
            PlayerKeyListener keyListener = new PlayerKeyListener(player, surfaceLabels, trapLabels, scoreLabels);
            frame.getFrame().addKeyListener(keyListener);
            frame.getGamePanel().addKeyListener(keyListener);
            
            frame.getGamePanel().add(player);
            frame.getGamePanel().setComponentZOrder(player, 0);
            
            frame.getFrame().add(frame.getGamePanel());
            frame.getFrame().add(frame.getUiPanel());
            frame.getLoadPanel().setVisible(false);
            frame.getFrame().remove(frame.getMenuPanel());
            frame.getFrame().remove(frame.getLoadPanel());
            
            frame.getMPlayer().createSong(worldDraw.getMusic(),true,-20f,true);
        } 
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
