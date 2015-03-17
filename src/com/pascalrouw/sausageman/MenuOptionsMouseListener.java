package com.pascalrouw.sausageman;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Rectangle;

public class MenuOptionsMouseListener implements MouseListener {
    private ArrayList<JLabel> menuSelections;
    private MenuOptions options;
    private Frame frame;
    public MenuOptionsMouseListener(MenuOptions options, Frame frame, ArrayList<JLabel> menuSelections) {
        this.options = options;
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
        /*
        if (tempLabel == menuSelections.get(6) || tempLabel == menuSelections.get(7)) {
            tempLabel.setIcon(frame.getArrowLeftSel());
        }
        
        if (tempLabel == menuSelections.get(8) || tempLabel == menuSelections.get(9)) {
            tempLabel.setIcon(frame.getArrowRightSel());
        }
        */
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
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel tempLabel = (JLabel) e.getSource();
        
        if (tempLabel == menuSelections.get(5)) {
            for (JLabel tempLabel2 : menuSelections) {
                tempLabel2.removeMouseListener(this);
            }
            mouseExited(e);
            new MenuMain(frame);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
