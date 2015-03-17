package com.pascalrouw.sausageman;

import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import java.awt.Rectangle;
import java.lang.Thread;

public class Player extends JLabel {
    private Frame frame;
    private Player deze;

    private boolean dead;

    private PlayerMovementThread playerMovement;
    private ArrayList<Surface> surfaceLabels;
    private ArrayList<Trap> trapLabels;

    private boolean left;
    private boolean right;
    private boolean jumped;

    private ImageIcon iconDie;

    private int xSpawn;
    private int ySpawn;
    private double vSpeed;

    private int score;
    private int health;
    private boolean hit;

    public Player(Frame frame, ArrayList<Surface> surfaceLabels, ArrayList<Trap> trapLabels, ArrayList<Score> scoreLabels) {
        this.frame = frame;
        this.deze = this;

        this.surfaceLabels = surfaceLabels; 
        this.trapLabels = trapLabels;

        iconDie = new ImageIcon(this.getClass().getResource("Resources/Images/die.gif"));
        playerMovement = new PlayerMovementThread(this, surfaceLabels, trapLabels, scoreLabels); 
        Thread mover = new Thread(playerMovement);
        mover.start();
    }

    public void die() {
        if (dead == false) {
            dead = true;
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                    private int i;
                    public void run() {
                        deze.setLeft(false);
                        deze.setRight(false);
                        if (i == 0) {
                            ImageIcon die = new ImageIcon(this.getClass().getResource("Resources/Images/die.gif"));
                            die.getImage().flush();
                            deze.setBounds(deze.getBounds().x,deze.getBounds().y,128,deze.getBounds().height);
                            deze.setIcon(die);
                            playSound("death.wav", -16f);
                        } 
                        else 
                        if(i == 19) {
                            deze.setBounds(getXSpawn(),getYSpawn(),72,72);
                        }
                        else
                        if (i == 20) {
                            deze.setIcon(playerMovement.getIconStandRight());
                            Rectangle tempPanelBounds = getFrame().getGamePanel().getBounds();
                            getFrame().getGamePanel().setBounds(0,tempPanelBounds.y,tempPanelBounds.width,tempPanelBounds.height);
                            dead = false;
                            jumped = false;
                            hit = false;
                            health = 3;
                            addScore(-10);
                            if (getScore() < 0) {
                                setScore(0);
                            }
                            updateScore();
                            updateHealth();
                            this.cancel();
                        }
                        i++;
                    }
                }, 0, 100);
        }
    }

    public void health(int hp) {
        if(health > 0 && hit == false){
            health = health + hp;
            updateHealth();
            vSpeed = -10;
            hit = true;
            if ( health > 0 ) {
                final Timer hitTimer = new Timer();
                hitTimer.scheduleAtFixedRate(new TimerTask(){
                    int i = 0;
                    @Override
                    public void run() {
                        if ( i > 1 ) {
                            if (deze.isVisible()) {
                                deze.setVisible(false);
                            } 
                            else {
                                deze.setVisible(true);
                            }
                         }
                        
                        if (i == 10) {
                            deze.setVisible(true);
                            deze.hit = false;
                            hitTimer.cancel();
                         }
                        i++;
                     }
                }, 0, 200);
            }
        }
        if (health == 0) {
            die();
        }
    }

    public void playSound(String soundFile, float volume) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("Resources/Sounds/" + soundFile));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            clip.start();
        } 
        catch (Exception f) {
            f.printStackTrace();
        }
    }

    public void updateScore(){
        frame.getScore().setText("x " + score + "");
    }
    
    public void updateHealth(){
        ImageIcon heartIcon = frame.getHeartIcon();
        ImageIcon heartIconEmpty = frame.getHeartIconEmpty();
        if (health == 2) {
            if (frame.getHeart1().getIcon() == heartIconEmpty) {
                frame.getHeart1().setIcon(heartIcon);
            }
            if (frame.getHeart2().getIcon() == heartIconEmpty) {
                frame.getHeart2().setIcon(heartIcon);
            }
            if (frame.getHeart3().getIcon() == heartIcon) {
                frame.getHeart3().setIcon(heartIconEmpty);
            }
        }
        if (health == 1) {
            if (frame.getHeart1().getIcon() == heartIconEmpty) {
                frame.getHeart1().setIcon(heartIcon);
            }
            if (frame.getHeart2().getIcon() == heartIcon) {
                frame.getHeart2().setIcon(heartIconEmpty);
            }
            if (frame.getHeart3().getIcon() == heartIcon) {
                frame.getHeart3().setIcon(heartIconEmpty);
            }
        }
        if (health == 0) {
            if (frame.getHeart1().getIcon() == heartIcon) {
                frame.getHeart1().setIcon(heartIconEmpty);
            }
            if (frame.getHeart2().getIcon() == heartIcon) {
                frame.getHeart2().setIcon(heartIconEmpty);
            }
            if (frame.getHeart3().getIcon() == heartIcon) {
                frame.getHeart3().setIcon(heartIconEmpty);
            }
        }
        if (health == 3) {
            if (frame.getHeart1().getIcon() == heartIconEmpty) {
                frame.getHeart1().setIcon(heartIcon);
            }
            if (frame.getHeart2().getIcon() == heartIconEmpty) {
                frame.getHeart2().setIcon(heartIcon);
            }
            if (frame.getHeart3().getIcon() == heartIconEmpty) {
                frame.getHeart3().setIcon(heartIcon);
            }
        }
    }

    /***************************************************** GETTERS AND SETTERS *****************************************************/

    public void addScore(int score){
        this.score += score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    /**
     * Returns boolean Dead
     */
    public boolean getDead() {
        return dead;
    }

    public void setXSpawn(int xSpawn) {
        this.xSpawn = xSpawn;
    }

    public void setYSpawn(int ySpawn) {
        this.ySpawn = ySpawn;
    }

    public int getXSpawn() {
        return xSpawn;
    }

    public int getYSpawn() {
        return ySpawn;
    }

    public Frame getFrame() {
        return frame;
    }

    // GET MOVEMENT
    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean getLeft() {
        return left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean getRight() {
        return right;
    }

    public boolean getJumped() {
        return jumped;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public double getVSpeed(){
        return vSpeed;
    }

    public void setVSpeed(double vSpeed){
        this.vSpeed = vSpeed;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
}
