
package com.ui;

import com.main.Vector;
import java.awt.Graphics2D;

public class FixedSizeElement implements IElement{
    
    private Vector size;
    
    public FixedSizeElement(){
        size = new Vector();
    }

    public FixedSizeElement setSize(Vector s){
        size = s;
        return this;
    }
    
    public Vector getSize() {
        return size;
    }

    public void render(Graphics2D g) {}
}
