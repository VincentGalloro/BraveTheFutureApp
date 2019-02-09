
package com.ui;

import com.main.Vector;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageElement implements IElement{
    
    private BufferedImage img;
    
    public ImageElement(BufferedImage img){
        this.img = img;
    }
    
    public Vector getSize() {
        return new Vector(img.getWidth(), img.getHeight());
    }
    
    public void render(Graphics2D g){
        g.drawImage(img, 0, 0, null);
    }
}
