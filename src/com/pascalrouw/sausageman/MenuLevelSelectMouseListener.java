package com.pascalrouw.sausageman;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Rectangle;

public class MenuLevelSelectMouseListener implements MouseListener
{
    private ArrayList<JLabel> menuSelections;
    private MenuLevelSelect levelSelect;
    private Frame frame;
    public MenuLevelSelectMouseListener(MenuLevelSelect levelSelect, Frame frame, ArrayList<JLabel> menuSelections) {
        this.levelSelect = levelSelect;
        this.menuSelections = menuSelections;
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel tempLabel = (JLabel) e.getSource();

        for (JLabel tempLabel2 : menuSelections) {
            if (tempLabel == tempLabel2) {
                Rectangle tempBounds = tempLabel2.getBounds();
                tempLabel2.setBounds(tempBounds.x - 5,tempBounds.y - 5,210,60);
                tempLabel2.setBackground(new Color(164,38,9));
            }
        }

        for (JLabel tempLabel2 : levelSelect.getLevelLabels()) {
            if (tempLabel == tempLabel2) {
                Rectangle tempBounds = tempLabel2.getBounds();
                tempLabel2.setBounds(tempBounds.x - 5,tempBounds.y - 5,tempBounds.width + 10,tempBounds.height + 10);
                tempLabel2.setBackground(new Color(164,38,9));
            }
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel tempLabel = (JLabel) e.getSource();

        for (JLabel tempLabel2 : menuSelections) {
            if (tempLabel == tempLabel2) {
                Rectangle tempBounds = tempLabel2.getBounds();
                tempLabel2.setBounds(tempBounds.x + 5,tempBounds.y + 5,200,50);
                tempLabel2.setBackground(new Color(184,58,29));
            }
        }

        for (JLabel tempLabel2 : levelSelect.getLevelLabels()) {
            if (tempLabel == tempLabel2) {
                Rectangle tempBounds = tempLabel2.getBounds();
                tempLabel2.setBounds(tempBounds.x + 5,tempBounds.y + 5,tempBounds.width - 10,tempBounds.height - 10);
                tempLabel2.setBackground(new Color(184,58,29));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel tempLabel = (JLabel) e.getSource();

        for (JLabel tempLabel2 : levelSelect.getLevelLabels()) {
            if (tempLabel == tempLabel2) {
                frame.getMenuPanel().setVisible(false);
                frame.getLoadPanel().setVisible(true);
                frame.getLoadPanel().paintImmediately(0,0,1280,720);
                LoadWorld world = new LoadWorld(frame);
                world.load(tempLabel2.getText());
            }
        }

        if (tempLabel == menuSelections.get(5)) {
            for (JLabel tempLabel2 : menuSelections) {
                tempLabel2.removeMouseListener(this);
            }
            for (JLabel tempLabel2 : levelSelect.getLevelLabels()) {
                tempLabel2.removeMouseListener(this);
                frame.getMenuPanel().remove(tempLabel2);
            }
            mouseExited(e);
            new MenuMain(frame);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
