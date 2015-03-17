package com.pascalrouw.sausageman;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class WorldTraps
{
    private ArrayList<ImageIcon> tileArray;
    
    public WorldTraps(ArrayList<ImageIcon> tileArray) {
        this.tileArray = tileArray;
    }
    
    public ArrayList<Trap> drawTraps(Frame frame, ArrayList<JsonReaderLayers> layers, int tileWidth, int tileHeight, int width)
    {
        ArrayList<Trap> labels = new ArrayList<Trap>();
        ImageIcon tempimg;
        Trap temp;
        int yPos = 0;
        int xPos = 0;
        for (int l = layers.size() - 1; l > -1; l--) {
            for (int x = 0; x < layers.get(l).getData().size(); x++) {
                Float imagenumber = layers.get(l).getData().get(x);
                int imageNumber = (int)Math.round(imagenumber) - 1;
                if ( imageNumber > 0 && layers.get(l).getVisible() && layers.get(l).getName().contains("traps")) {
                    int e = 0;
                    temp = new Trap();
                    if (imageNumber == 2425 - 1 || imageNumber == 2354 - 1 || imageNumber == 2423 - 1) {
                        tempimg = tileArray.get(imageNumber);
                        temp.setIcon(tempimg);
                        temp.setBounds(xPos,yPos,48,48);
                        temp.setVisible(true);
                    }
                    
                    
                    // SPIKED TRAPS
                    if (imageNumber == 3045 - 1) {
                        tempimg = tileArray.get(imageNumber);
                        temp.setVerticalAlignment(JLabel.BOTTOM);
                        temp.setIcon(tempimg);
                        temp.setBounds(xPos,yPos + 24,48,24);
                        temp.setVisible(true);
                        temp.blink(500, 1500);
                    }
                    
                    if (imageNumber == 3046 - 1) {
                        tempimg = tileArray.get(imageNumber);
                        temp.setVerticalAlignment(JLabel.BOTTOM);
                        temp.setIcon(tempimg);
                        temp.setBounds(xPos,yPos + 24,48,24);
                        temp.setVisible(true);
                        temp.blink(1000, 1500);
                    }
                    
                    if (imageNumber == 3047 - 1) {
                        tempimg = tileArray.get(imageNumber);
                        temp.setVerticalAlignment(JLabel.BOTTOM);
                        temp.setIcon(tempimg);
                        temp.setBounds(xPos,yPos + 24,48,24);
                        temp.setVisible(true);
                        temp.blink(1500, 1500);
                    }
                    
                    if (imageNumber == 3048 - 1) {
                        tempimg = tileArray.get(imageNumber);
                        temp.setVerticalAlignment(JLabel.BOTTOM);
                        temp.setIcon(tempimg);
                        temp.setBounds(xPos,yPos + 24,48,24);
                        temp.setVisible(true);
                        temp.blink(2000, 1500);
                    }
                    
                    labels.add(temp);
                    frame.getGamePanel().add(temp);
                    frame.getGamePanel().setComponentZOrder(temp, 0);
                    frame.getGamePanel().repaint();
                }       
                xPos += tileWidth;
                if (xPos == tileWidth * width)
                {
                    xPos = 0;
                    yPos += tileHeight;
                }
            }
            xPos = 0;
            yPos = 0;
        }
        return labels;
    }
}
