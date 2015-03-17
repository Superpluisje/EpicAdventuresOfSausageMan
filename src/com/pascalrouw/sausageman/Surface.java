package com.pascalrouw.sausageman;

import javax.swing.JLabel;

public class Surface extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	private boolean jumpTrough;
    
    public void setJumpTrough(boolean jumpTrough){
        this.jumpTrough = jumpTrough;
    }
    
    public boolean getJumpTrough(){
        return jumpTrough;
    }
}
