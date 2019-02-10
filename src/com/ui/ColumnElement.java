package com.ui;

import com.main.Vector;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

public class ColumnElement implements IListContainer{
    
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
    
    public int getElementCount() {
        return elements.size();
    }

    public Iterator<IElement> getElements() {
        return elements.iterator();
    }

    public void applyTrasform(AffineTransform at, int index) {
        for(int i = 0; i < index; i++){
            at.translate(0, elements.get(i).getSize().y + seperation);
        }
    }
}