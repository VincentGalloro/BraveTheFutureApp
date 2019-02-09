package com.ui;

import com.main.Vector;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class ColumnElement implements IElement{
    
    private ArrayList<IElement> elements;
    private double seperation;
    
    public ColumnElement(){
        elements = new ArrayList<>();
    }
    
    public ColumnElement addElement(IElement e){
        elements.add(e);
        return this;
    }
    public ColumnElement setSeperation(double s){
        seperation = s;
        return this;
    }
    
    public Vector getSize() {
        Vector size = new Vector();
        size.x = elements.stream().mapToDouble((IElement e) -> e.getSize().x).max().orElse(0);
        size.y = elements.stream().mapToDouble((IElement e) -> e.getSize().y).sum() + seperation * (elements.size()-1);
        return size;
    }

    public void render(Graphics2D g) {
        AffineTransform save = g.getTransform();
        for(IElement e : elements){
            e.render(g);
            g.translate(0, e.getSize().y+seperation);
        }
        g.setTransform(save);
    }
}