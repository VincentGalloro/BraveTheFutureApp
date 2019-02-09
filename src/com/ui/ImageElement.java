package com.ui;

import com.main.Vector;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageElement implements IElement{

    private BufferedImage image;
    
    public ImageElement setImage(BufferedImage i){ 
        image = i; 
        return this;
    }

    public Vector getSize() {
        if(image==null){ return new Vector(); }
        return new Vector(image.getWidth(), image.getHeight());
    }

    public void render(Graphics2D g) {
        g.drawImage(image, 0, 0, null);
    }
}