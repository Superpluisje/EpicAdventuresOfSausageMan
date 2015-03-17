package com.pascalrouw.sausageman;

import java.util.ArrayList;

/**
 * Reads the "layers" part of the WORLNAME.json file
 * 
 * @author Melvin Versluijs
 * @author Pascal Rouw
 * 
 * @version 2013.11.24
 */
public class JsonReaderLayers {
    private ArrayList < Float > data;
    private int width;
    private int height;
    private int opacity;
    private int x;
    private int y;
    private String name;
    private String type;
    private boolean visible;

    /**
     * Getter for data
     */
    public ArrayList < Float > getData() {
        return data;
    }

    /**
     * Getter for height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for opacity
     */
    public int getOpacity() {
        return opacity;
    }

    /**
     * Getter for x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for type
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for visible
     */
    public Boolean getVisible() {
        return visible;
    }
}
