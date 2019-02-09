
package com.ui;

import com.main.Vector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rectangle implements IElement{

    private Vector size;
    private Color color;
    
    public Rectangle(Vector size, Color color){
        this.size = size;
        this.color = color;
    }
    
    public Vector getSize() {
        return size;
    }

    public void render(Graphics2D g) {
        g.setColor(color);
        g.fill(new Rectangle2D.Double(0, 0, size.x, size.y));
    }
}
