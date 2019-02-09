package com.ui;

import com.main.Vector;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class RowElement implements IElement{

    private ArrayList<IElement> elements;
    private double seperation;
    
    public RowElement(){
        elements = new ArrayList<>();
    }
    
    public RowElement addElement(IElement e){
        elements.add(e);
        return this;
    }
    public RowElement setSeperation(double s){
        seperation = s;
        return this;
    }
    
    public Vector getSize() {
        Vector size = new Vector();
        size.x = elements.stream().mapToDouble((IElement e) -> e.getSize().x).sum() + seperation * (elements.size()-1);
        size.y = elements.stream().mapToDouble((IElement e) -> e.getSize().y).max().orElse(0);
        return size;
    }

    public void render(Graphics2D g) {
        AffineTransform save = g.getTransform();
        for(IElement e : elements){
            e.render(g);
            g.translate(e.getSize().x+seperation, 0);
        }
        g.setTransform(save);
    }
}