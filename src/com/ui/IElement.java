package com.ui;

import com.main.Vector;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public interface IElement {

    public Vector getSize();
    
    public void render(Graphics2D g);
}