package com.pascalrouw.sausageman;

import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Rectangle;

public class PlayerCheckCollision {
    private Player player;
    private ArrayList<Surface> surfaceLabels;
    private ArrayList<Trap> trapLabels;
    private ArrayList<Score> scoreLabels;
    
    private Rectangle tempPlayerBounds;
    private Rectangle realPlayerBounds;
    
    private Rectangle upperBounds;
    private Rectangle lowerBounds;
    private Rectangle lowerBoundsLow;
    private Rectangle lowerBoundsHigh;
    private Rectangle leftBounds;
    private Rectangle rightBounds;
    // Boven = 0
    // Rechts = 1
    // Onder = 2
    // Links = 3
    // Player = 4;
    // Player vspeed = 5;

    
    
    public PlayerCheckCollision(Player player, ArrayList<Surface> surfaceLabels, ArrayList<Trap> trapLabels, ArrayList<Score> scoreLabels) {
        this.player = player;
        this.surfaceLabels = surfaceLabels;
        this.trapLabels = trapLabels;
        this.scoreLabels = scoreLabels;
        
        tempPlayerBounds = new Rectangle(0,0,0,0);
        realPlayerBounds = new Rectangle(0,0,0,0);
        
        upperBounds = new Rectangle(0,0,0,0);
        lowerBounds = new Rectangle(0,0,0,0);
        lowerBoundsLow = new Rectangle(0,0,0,0);
        lowerBoundsHigh = new Rectangle(0,0,0,0);
        leftBounds = new Rectangle(0,0,0,0);
        rightBounds = new Rectangle(0,0,0,0);
    }
    
    public ArrayList<Integer> check() {
        tempPlayerBounds.setBounds(player.getBounds());
        realPlayerBounds.setBounds(tempPlayerBounds.x + 17,tempPlayerBounds.y + 17,tempPlayerBounds.width - 34,tempPlayerBounds.height - 17);
        
        upperBounds.setBounds(tempPlayerBounds.x + 17,tempPlayerBounds.y - 4 + 14,tempPlayerBounds.width - 34, 4);
        lowerBounds.setBounds(tempPlayerBounds.x + 17,tempPlayerBounds.y + tempPlayerBounds.height, tempPlayerBounds.width - 34, 2);
        lowerBoundsLow.setBounds(tempPlayerBounds.x + 17,tempPlayerBounds.y + tempPlayerBounds.height+10, tempPlayerBounds.width - 34, 1);
        lowerBoundsHigh.setBounds(tempPlayerBounds.x + 17,tempPlayerBounds.y + tempPlayerBounds.height, tempPlayerBounds.width - 34, (int)player.getVSpeed());
        
        leftBounds.setBounds(tempPlayerBounds.x + 1, tempPlayerBounds.y + 17, 10, tempPlayerBounds.height - 17);
        rightBounds.setBounds(tempPlayerBounds.x + tempPlayerBounds.width - 17, tempPlayerBounds.y + 17, 10, tempPlayerBounds.height - 17);
        
        ArrayList<Integer> colliding = new ArrayList<Integer>();
        
        for (Surface tempLabel : surfaceLabels) {
            if (upperBounds.intersects( tempLabel.getBounds() ) && tempLabel.getJumpTrough() == false ) {
                colliding.add(0);
            }
            if (rightBounds.intersects( tempLabel.getBounds() ) && tempLabel.getJumpTrough() == false) {
                colliding.add(1);
            }
            if (lowerBounds.intersects(tempLabel.getBounds()) && lowerBoundsLow.intersects(tempLabel.getBounds())) {
                colliding.add(2);
            }
            if (leftBounds.intersects( tempLabel.getBounds() ) && tempLabel.getJumpTrough() == false) {
                colliding.add(3);
            }
            if (realPlayerBounds.intersects( tempLabel.getBounds() ) && tempLabel.getJumpTrough() == false) {
                colliding.add(4);
            }
            if (!lowerBounds.intersects(tempLabel.getBounds()) && lowerBoundsLow.intersects(tempLabel.getBounds())) {
                player.setVSpeed(1);
                colliding.add(5);
            }
        }
        checkWorldBounds();
        checkTraps();
        checkScore();
        return colliding;
    }
    
    public void checkWorldBounds() {
        if (tempPlayerBounds.y > 720) {
            player.die();
        }
    }
    
    public void checkTraps() {
        for (Trap tempLabel : trapLabels) {
            if (realPlayerBounds.intersects(tempLabel.getBounds()) && tempLabel.isVisible() ) {
                player.health(-1);
            }
        }
    }
    
    public void checkScore() {
        for (Score tempLabel : scoreLabels) {
            if (realPlayerBounds.intersects(tempLabel.getBounds()) && tempLabel.isVisible() ) {
                tempLabel.setVisible(false);
                player.playSound("score.wav", -8f);
                player.addScore(1);
                player.updateScore();
            }
        }
    }
}
