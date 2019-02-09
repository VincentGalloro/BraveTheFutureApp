package com.ui;

import com.main.Vector;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class PaddingElement implements IContainer{

    private IElement element;
    private double padding;
    
    public void applyTrasform(AffineTransform at){
        at.translate(padding, padding);
    }
    
    public PaddingElement setElement(IElement e){
        element = e;
        return this;
    }
    public PaddingElement setPadding(double p){
        padding = p;
        return this;
    }
    
    public IElement getElement(){
        return element;
    }
    
    public Vector getSize() {
        if(element == null){ return new Vector(); }
        return element.getSize().add(padding*2);
    }

    public void render(Graphics2D g) {
        if(element != null){
            AffineTransform save = g.getTransform();
            g.translate(padding, padding);
            element.render(g);
            g.setTransform(save);
        }
    }
}