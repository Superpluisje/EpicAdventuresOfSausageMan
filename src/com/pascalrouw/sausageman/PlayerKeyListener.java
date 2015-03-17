package com.pascalrouw.sausageman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JLabel;

public class PlayerKeyListener implements KeyListener
{
    private Player player;
    private PlayerCheckCollision checkCollision;
    
    public PlayerKeyListener(Player player, ArrayList<Surface> surfaceLabels, ArrayList<Trap> trapLabels, ArrayList<Score> scoreLabels) {
        this.player = player;
        checkCollision = new PlayerCheckCollision(player, surfaceLabels, trapLabels, scoreLabels);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT ) {
            if (!player.getDead()) {
                player.setLeft(true);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT && !player.getDead()) {
            if (!player.getDead()) {
                player.setRight(true);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP && !player.getDead()) {
            ArrayList<Integer> collisions = checkCollision.check();
            if (player.getJumped() == false) {
                player.setVSpeed(-15);
                player.setJumped(true);
                player.playSound("jump.wav", -8f);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setLeft(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setRight(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}