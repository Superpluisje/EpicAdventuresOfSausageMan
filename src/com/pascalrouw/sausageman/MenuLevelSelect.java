package com.pascalrouw.sausageman;

import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.BorderFactory;
/**
 * MenuLevelSelect makes the level select menu
 * 
 * @author Melvin Versluijs
 * @author Pascal Rouw
 * 
 * @version 2013.12.03
 */
public class MenuLevelSelect {
    private Frame frame;
    private ArrayList<JLabel> menuSelections;
    private ArrayList<JLabel> levelLabels;
    
    /**
     * Create this class and execute remaining methods
     * 
     * @param frame     Class Frame
     */
    public MenuLevelSelect(Frame frame) {
        this.frame = frame;
        menuSelections = frame.getMenuSelections();
        
        levelLabels = loadLevelList();
        toggleButtons();
        addListeners();
    }
    
    /**
     * Hide main menu buttons and show 'back' button
     */
    public void toggleButtons() {
        frame.getTitle().setVisible(false);
        menuSelections.get(0).setVisible(false);
        menuSelections.get(1).setVisible(false);
        menuSelections.get(2).setVisible(false);
        menuSelections.get(3).setVisible(false);
        menuSelections.get(4).setVisible(false);
        menuSelections.get(5).setVisible(true);
    }
    
    /**
     * Add MouseListeners to every button (JLabel)
     */
    public void addListeners() {
        MenuLevelSelectMouseListener mouse = new MenuLevelSelectMouseListener(this,frame,menuSelections);
        menuSelections.get(5).addMouseListener(mouse);
        for (JLabel tempLabel : levelLabels) {
            tempLabel.addMouseListener(mouse);
        }
    }
    
    /**
     * Create a button for each world
     */
    public ArrayList<JLabel> loadLevelList() {
        ArrayList<JLabel> tempArray = new ArrayList<JLabel>();
        for (int i = 0; i < 2; i++) {
            JLabel temp = new JLabel("World1-" + (i + 1));
            temp.setBounds(100,100 + (50 * i),1080,40);
            temp.setBackground(new Color(184,58,29));
            temp.setForeground(Color.WHITE);
            temp.setFont(frame.getSausageFont().deriveFont(10f));
            temp.setBorder(BorderFactory.createLineBorder(Color.black, 3));
            temp.setOpaque(true);
            
            frame.getMenuPanel().add(temp);
            frame.getMenuPanel().setComponentZOrder(temp, 0);
            tempArray.add(temp);
        }
        return tempArray;
    }
    
    /**
     * return ArrayList<JLabel> levelLabels
     */
    public ArrayList<JLabel> getLevelLabels() {
        return levelLabels;
    } 
}
