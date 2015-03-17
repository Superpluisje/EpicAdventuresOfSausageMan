package com.pascalrouw.sausageman;

import java.util.ArrayList;
import javax.swing.JLabel;
import com.google.gson.*;
import java.io.Reader;
import java.io.InputStreamReader;

public class MenuOptions {
    private Frame frame;
    private ArrayList<JLabel> menuSelections;
    
    private int musicVolume;
    private int soundVolume;
    
    private JsonConfigReader conf;
    private JsonConfigReader config;
    
    public MenuOptions(Frame frame) {
        this.frame = frame;
        menuSelections = frame.getMenuSelections();
        
        toggleButtons();
        addListeners();
        
        
        try{
            Reader reader = new InputStreamReader( this.getClass().getResourceAsStream( "Resources/Config/audio.json" ), "UTF-8" );
            Gson gson = new GsonBuilder().create();
            conf = new JsonConfigReader();
            config = gson.fromJson( reader, conf.getClass() );
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        this.musicVolume = config.getMusic();
        this.soundVolume = config.getSound();
        
        System.out.println(musicVolume);
    }
    
    public void toggleButtons() {
        frame.getTitle().setVisible(false);
        menuSelections.get(0).setVisible(false);
        menuSelections.get(1).setVisible(false);
        menuSelections.get(2).setVisible(false);
        menuSelections.get(3).setVisible(false);
        menuSelections.get(4).setVisible(false);
        menuSelections.get(5).setVisible(true);
        
        //menuSelections.get(6).setVisible(true);
        //menuSelections.get(7).setVisible(true);
        //menuSelections.get(8).setVisible(true);
        //menuSelections.get(9).setVisible(true);
    }
    
    public void addListeners() {
        MenuOptionsMouseListener mouse = new MenuOptionsMouseListener(this,frame,menuSelections);
        menuSelections.get(5).addMouseListener(mouse);
        //menuSelections.get(6).addMouseListener(mouse);
        //menuSelections.get(7).addMouseListener(mouse);
        //menuSelections.get(8).addMouseListener(mouse);
        //menuSelections.get(9).addMouseListener(mouse);
    }
    
    public void addMusicVolume(int volume){
        musicVolume += volume;
    }
    
    public void addSoundVolume(int volume){
        soundVolume += volume;
    }
}
