package com.pascalrouw.sausageman;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import java.io.File;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.util.HashMap;

/**
 * 
 * @author Melvin Versluijs
 * @author Pascal Rouw
 * 
 * @version 2013.11.24
 */
public class WorldTiles {
    private ArrayList < ImageIcon > tileArray;
    
    /**
     * Split an image in blocks and put those in an ArrayList
     * @param imageFile     name of the image file
     * @param imageRows     number of tilerows in the image
     * @param imageCols     number of tilecoloms in the image
     */
    public ArrayList < ImageIcon > cutTiles( String imageFile, int imageRows, int imageCols ) {
        try 
        {
            tileArray = new ArrayList < ImageIcon >();
            BufferedImage image = ImageIO.read( this.getClass().getResourceAsStream( imageFile ) );
            int rows = imageRows;
            // AANTAL VERTICALE RIJEN
            int cols = imageCols;
            // AANTAL HORIZONTALE RIJEN
            int chunks = rows * cols;
            int chunkWidth = image.getWidth() / cols;
            int chunkHeight = image.getHeight() / rows;
            int count = 0;
            BufferedImage[] imgs = new BufferedImage[ chunks ];

            for ( int x = 0; x < rows; x++ ) 
            {

                for ( int y = 0; y < cols; y++ ) 
                {
                    imgs[ count ] = new BufferedImage( chunkWidth, chunkHeight, image.getType() );
                    Graphics2D gr = imgs[ count++ ].createGraphics();
                    gr.drawImage( image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null );
                    gr.dispose();
                }
            }

            for ( int i = 0; i < imgs.length; i++ ) 
            {
                tileArray.add( new ImageIcon( imgs[ i ] ) );
            }

            return tileArray;
        } 
        catch ( Exception e ) 
        {
            e.printStackTrace();
        }

        return tileArray;
    }
    
    public ArrayList<Surface> drawTiles(Frame frame, ArrayList<JsonReaderLayers> layers, int tileWidth, int tileHeight, int width)
    {
        ArrayList<Surface> labels = new ArrayList<Surface>();
        ImageIcon tempimg;
        Surface temp;
        int yPos = 0;
        int xPos = 0;
        for (int l = layers.size() - 1; l > -1; l--)
        {
            for (int x = 0; x < layers.get(l).getData().size(); x++)
            {
                
                Float imagenumber = layers.get(l).getData().get(x);
                int imageNumber = (int)Math.round(imagenumber) - 1;
                String layerName = layers.get(l).getName();
                if ( imageNumber >= 0 && layers.get(l).getVisible() && !layerName.contains("traps") && !layerName.contains("score"))
                {
                    
                    tempimg = tileArray.get(imageNumber);
                    temp = new Surface();
                    temp.setIcon(tempimg);
                    temp.setVerticalAlignment(JLabel.TOP);
                    temp.setBounds(xPos,yPos,48,48);
                    temp.setVisible(true);
                    
                    
                    
                    
                    frame.getGamePanel().add(temp);
                    if (layers.get(l).getName().contains("surface")) {
                        if (imageNumber + 1 == 1108 || imageNumber + 1 == 1109 || imageNumber + 1 == 1110) {
                            temp.setBounds(xPos,yPos,48,15);
                            temp.setJumpTrough(true);
                        }
                        //temp.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                        labels.add(temp);
                    }
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
