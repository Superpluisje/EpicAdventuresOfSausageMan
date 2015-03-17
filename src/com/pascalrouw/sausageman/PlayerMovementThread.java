package com.pascalrouw.sausageman;

import java.util.TimerTask;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class PlayerMovementThread implements Runnable {
    private PlayerCheckCollision checkCollision;
    private ArrayList<Integer> collisions;
    private Player player;
    private int speed;
    private int y;
    private Rectangle tempPlayerBounds;
    private Rectangle tempPanelBounds;

    private ImageIcon iconStandRight;
    private ImageIcon iconStandLeft;
    private ImageIcon iconRunRight;
    private ImageIcon iconRunLeft;
    private ImageIcon iconJumpRight;
    private ImageIcon iconJumpLeft;

    public PlayerMovementThread(Player player, ArrayList<Surface> surfaceLabels, ArrayList<Trap> trapLabels, ArrayList<Score> scoreLabels) {
        this.player = player;
        checkCollision = new PlayerCheckCollision(player, surfaceLabels, trapLabels, scoreLabels);

        iconStandRight = new ImageIcon(this.getClass().getResource("Resources/Images/standRight.gif"));
        iconStandLeft = new ImageIcon(this.getClass().getResource("Resources/Images/standLeft.gif"));
        iconRunRight = new ImageIcon(this.getClass().getResource("Resources/Images/runRight.gif"));
        iconRunLeft = new ImageIcon(this.getClass().getResource("Resources/Images/runLeft.gif"));
        iconJumpRight = new ImageIcon(this.getClass().getResource("Resources/Images/jumpRight.gif"));
        iconJumpLeft = new ImageIcon(this.getClass().getResource("Resources/Images/jumpLeft.gif"));
        player.setIcon(iconStandRight);
    }

    public void bottom() {
        if (player.getVSpeed() < 12) {
                player.setVSpeed(player.getVSpeed() + 1);
        }
        
        if (collisions.contains(2)) {
            if (player.getVSpeed() > 0) { 
                player.setVSpeed(0);
            }

            if (player.getIcon() == iconJumpRight && player.getVSpeed() >= 0.0 && !player.getDead()) {
                player.setIcon(iconStandRight);
                player.setJumped(false);
            } 
            else
                if (player.getIcon() == iconJumpLeft && player.getVSpeed() >= 0.0 && !player.getDead()) {
                    player.setIcon(iconStandLeft);
                    player.setJumped(false);
                }
        } 
        else {
            if (speed > 0 || player.getIcon() == iconStandRight && !player.getDead()) {
                player.setIcon(iconJumpRight);
            } 
            else 
                if (speed < 0 || player.getIcon() == iconStandLeft && !player.getDead()) {
                    player.setIcon(iconJumpLeft);
                }
        }
    }

    public void top() {
        if (collisions.contains(0)) {
            player.setVSpeed(1);
        }
    }

    public void move() {
        if (player.getLeft() && !collisions.contains(3) && !player.getDead()) {
            speed = -8;
            if (collisions.contains(2)) {
                player.setIcon(iconRunLeft);
            }
        } 
        else
            if (player.getRight() && !collisions.contains(1) && !player.getDead()) {
                speed = 8;
                if (collisions.contains(2)) {
                    player.setIcon(iconRunRight);
                }
            } 
            else {
                speed = 0;
                if (player.getIcon() == iconRunRight && !player.getDead()) {
                    player.setIcon(iconStandRight);
                } 
                else
                    if (player.getIcon() == iconRunLeft && !player.getDead()) {
                        player.setIcon(iconStandLeft);
                    }
            }
    }

    public void run() {
        while(true){
            collisions = checkCollision.check();
            
            bottom();
            top();
            move();
            if (collisions.contains(4)) {
                y = -1;
            }
            if (collisions.contains(5) && player.getVSpeed() > 3) {
                y += 4;
            }
            tempPlayerBounds = player.getBounds();
            tempPanelBounds = player.getFrame().getGamePanel().getBounds();
            
            if (tempPanelBounds.x > 0) {
                player.getFrame().getGamePanel().setBounds(0,50,tempPanelBounds.width,tempPanelBounds.height);
            }
    
            if (tempPlayerBounds.x > 604 && tempPanelBounds.x <= 0 && tempPanelBounds.x > -12960) {
                player.getFrame().getGamePanel().setBounds(tempPanelBounds.x - speed,tempPanelBounds.y,tempPanelBounds.width,tempPanelBounds.height);
            }
            
            player.setBounds(tempPlayerBounds.x + speed,tempPlayerBounds.y + (int)player.getVSpeed() + y,tempPlayerBounds.width,tempPlayerBounds.height);
            y = 0;
            
            try{
                Thread.sleep(33); 
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    // GET ICONS
    public ImageIcon getIconStandRight() {
        return iconStandRight;
    }

    public ImageIcon getIconStandLeft() {
        return iconStandLeft;
    }

    public ImageIcon getIconRunLeft() {
        return iconRunLeft;
    }

    public ImageIcon getIconRunRight() {
        return iconRunRight;
    }

    public ImageIcon getIconJumpLeft() {
        return iconJumpLeft;
    }

    public ImageIcon getIconJumpRight() {
        return iconJumpRight;
    }
}