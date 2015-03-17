package com.pascalrouw.sausageman;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class WorldScore
{
private ArrayList<ImageIcon> tileArray;
    
    public WorldScore(ArrayList<ImageIcon> tileArray) {
        this.tileArray = tileArray;
    }
    
    public ArrayList<Score> drawScore(Frame frame, ArrayList<JsonReaderLayers> layers, int tileWidth, int tileHeight, int width)
    {
        ArrayList<Score> labels = new ArrayList<Score>();
        ImageIcon tempimg;
        Score temp;
        int yPos = 0;
        int xPos = 0;
        for (int l = layers.size() - 1; l > -1; l--) {
            for (int x = 0; x < layers.get(l).getData().size(); x++) {
                Float imagenumber = layers.get(l).getData().get(x);
                int imageNumber = (int)Math.round(imagenumber) - 1;
                if ( imageNumber > 0 && layers.get(l).getVisible() && layers.get(l).getName().contains("score")) {
                    int e = 0;
                    temp = new Score();
                    if (imageNumber == 3252 - 1 ) {
                        tempimg = new ImageIcon(this.getClass().getResource("Resources/Images/coin.gif"));
                        temp.setIcon(tempimg);
                        temp.setBounds(xPos + 12,yPos + 12,24,24);
                        temp.setVisible(true);
                    }
                    
                    labels.add(temp);
                    frame.getGamePanel().add(temp);
                    frame.getGamePanel().setComponentZOrder(temp, 1);
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
