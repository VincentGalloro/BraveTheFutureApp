package com.ui;

import com.main.Vector;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class BoxElement implements IContainer{

    private IElement element;
    private Color outline, fill;
    
    public void applyTrasform(AffineTransform at){}
    
    public BoxElement setElement(IElement e){
        element = e;
        return this;
    }
    
    public BoxElement setOutlineColor(Color o){
        outline = o;
        return this;
    }
    
    public BoxElement setFillColor(Color f){
        fill = f;
        return this;
    }
    
    public IElement getElement(){
        return element;
    }
    
    public Vector getSize() {
        if(element == null){ return new Vector(); }
        return element.getSize();
    }

    public void render(Graphics2D g) {
        Vector size = getSize();
        if(fill != null){
            g.setColor(fill);
            g.fill(new Rectangle2D.Double(0, 0, size.x, size.y));
        }
        if(element != null){
            element.render(g);
        }
        if(outline != null){
            g.setColor(outline);
            g.setStroke(new BasicStroke(0.02f));
            g.draw(new Rectangle2D.Double(0, 0, size.x, size.y));
        }
    }
}