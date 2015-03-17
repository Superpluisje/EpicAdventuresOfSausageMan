package com.pascalrouw.sausageman;

import java.util.ArrayList;
import javax.swing.JLabel;

public class MenuMain
{
    private Frame frame;
    private ArrayList<JLabel> menuSelections;

    public MenuMain(Frame frame) {
        this.frame = frame;
        menuSelections = frame.getMenuSelections();

        setButtonTexts();
        addListeners();

        frame.getMPlayer().createSong("ThemeSong_LOW.wav",true,-20f,true);

        frame.getLoadPanel().setVisible(false);
        frame.getMenuPanel().setVisible(true);
        frame.getFrame().add(frame.getMenuPanel());
    }

    public void setButtonTexts() {
        frame.getTitle().setVisible(true);
        menuSelections.get(0).setVisible(true);
        menuSelections.get(1).setVisible(true);
        menuSelections.get(2).setVisible(true);
        menuSelections.get(3).setVisible(true);
        menuSelections.get(4).setVisible(true);
        menuSelections.get(5).setVisible(false);

        menuSelections.get(0).setText("New Game");
        menuSelections.get(1).setText("Continue");
        menuSelections.get(2).setText("Level Select");
        menuSelections.get(3).setText("Options");
        menuSelections.get(4).setText("Quit");
    }

    public void addListeners() {
        MenuMainMouseListener mouse = new MenuMainMouseListener(this,frame,menuSelections);
        menuSelections.get(0).addMouseListener(mouse);
        menuSelections.get(1).addMouseListener(mouse);
        menuSelections.get(2).addMouseListener(mouse);
        menuSelections.get(3).addMouseListener(mouse);
        menuSelections.get(4).addMouseListener(mouse);
    }

    public void startNewGame() {
        frame.getMenuPanel().setVisible(false);
        frame.getLoadPanel().setVisible(true);
        frame.getLoadPanel().paintImmediately(0,0,1280,770);
        
        LoadWorld world = new LoadWorld(frame);
        world.load("world1-1");
    }
}
