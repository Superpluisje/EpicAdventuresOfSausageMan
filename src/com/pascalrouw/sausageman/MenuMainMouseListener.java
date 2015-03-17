package com.pascalrouw.sausageman;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Rectangle;
/**
 * MouseListener for MenuMain
 * 
 * @author Melvin Versluijs
 * @author Pascal Rouw
 * 
 * @version 2013.12.03
 */
public class MenuMainMouseListener implements MouseListener {
    private ArrayList<JLabel> menuSelections;
    private MenuMain menu;
    private Frame frame;
    /**
     * Create class
     * 
     * @param menu              Class MenuMain
     * @param frame             Class Frame
     * @param menuSelections    Arraylist of menu buttons
     */
    public MenuMainMouseListener(MenuMain menu, Frame frame, ArrayList<JLabel> menuSelections) {
        this.menu = menu;
        this.menuSelections = menuSelections;
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel tempLabel = (JLabel) e.getSource();
        frame.getFrame().setCursor( frame.getCSelect() );
        for (JLabel tempLabel2 : menuSelections) {
            if (tempLabel == tempLabel2) {
                Rectangle tempBounds = tempLabel2.getBounds();
                tempLabel2.setBounds(tempBounds.x - 5,tempBounds.y - 5,210,60);
                tempLabel2.setBackground(new Color(164,38,9));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel tempLabel = (JLabel) e.getSource();
        frame.getFrame().setCursor(frame.getCDefault());
        for (JLabel tempLabel2 : menuSelections) {
            if (tempLabel == tempLabel2) {
                Rectangle tempBounds = tempLabel2.getBounds();
                tempLabel2.setBounds(tempBounds.x + 5,tempBounds.y + 5,200,50);
                tempLabel2.setBackground(new Color(184,58,29));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel tempLabel = (JLabel) e.getSource();

        if (tempLabel == menuSelections.get(0)) {
            frame.getMPlayer().stopSong("ThemeSong_LOW.wav");
            menu.startNewGame();
        }

        if (tempLabel == menuSelections.get(2)) {
            for (JLabel tempLabel2 : menuSelections) {
                tempLabel2.removeMouseListener(this);
            }
            mouseExited(e);
            new MenuLevelSelect(frame);
        }

        if (tempLabel == menuSelections.get(3)) {
            for (JLabel tempLabel2 : menuSelections) {
                tempLabel2.removeMouseListener(this);
            }
            mouseExited(e);
            new MenuOptions(frame);
        }

        if (tempLabel == menuSelections.get(4)) {
            System.exit(0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
