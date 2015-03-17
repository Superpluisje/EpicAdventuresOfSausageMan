package com.pascalrouw.sausageman;

import javax.swing.JLabel;
import java.util.Timer;
import java.util.TimerTask;

public class Trap extends JLabel {
    private Trap deze;
    
    public Trap() {
        this.deze = this;
    }
    
    public void rotate() {
           
    }
    
    
    // SPIKES
    public void blink(int delayTime, int blinkTime) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            private boolean visible;
            private int i;
            
            public void run() {
                if (i == 7) {
                    deze.setVerticalAlignment(JLabel.CENTER);
                }
                if (i == 8) {
                    if (visible) {
                        deze.setVisible(false);
                        visible = false;
                    } 
                    else {
                        deze.setVerticalAlignment(JLabel.BOTTOM);
                        deze.setVisible(true);
                        visible = true;
                    }
                    i = 0;
                }
                i++;
            }
        }, delayTime, blinkTime / 8);
    }
}
