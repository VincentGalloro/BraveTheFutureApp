package com.ui;

import com.main.Vector;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class ScaleElement implements IContainer{
    
    private Vector size;
    private IElement element;

    public ScaleElement(){
        size = new Vector();
    }
    
    public void applyTrasform(AffineTransform at){        
        Vector eSize = element.getSize();

        double scale = size.divide(eSize).min();
        at.translate((size.x - eSize.x*scale)/2, (size.y - eSize.y*scale)/2);
        at.scale(scale, scale);
    }
    
    public ScaleElement setElement(IElement e){
        element = e;
        return this;
    }
    public ScaleElement setSize(Vector s){
        size = s;
        return this;
    }
    
    public Vector getSize() {
        return size;
    }
    
    public IElement getElement(){
        return element;
    }
    
    public void render(Graphics2D g){
        if(element != null){
            AffineTransform save = g.getTransform();
            
            Vector eSize = element.getSize();
            
            double scale = size.divide(eSize).min();
            g.translate((size.x - eSize.x*scale)/2, (size.y - eSize.y*scale)/2);
            g.scale(scale, scale);
            
            element.render(g);
            g.setTransform(save);
        }
    }
}