package com.ui;

import com.main.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TextElement implements IElement{

    private static final Font font = new Font("Arial", Font.PLAIN, 24);
    
    private String text;
    private FontMetrics fontMetrics;
    
    public TextElement(){ 
        BufferedImage i = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = i.createGraphics();
        fontMetrics = g.getFontMetrics(font);
        text = "";
    }
    
    public TextElement setText(String t){ 
        text = t; 
        return this;
    }
    
    public Vector getSize() {
        return new Vector(fontMetrics.stringWidth(text), fontMetrics.getHeight());
    }
    public String getText() { return text; } 

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(text, 0, fontMetrics.getAscent());
    }
}