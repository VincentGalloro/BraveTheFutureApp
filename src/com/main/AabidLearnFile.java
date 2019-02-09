
package com.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class AabidLearnFile {
    
    private int counter;
    private double radius, offset;
    
    public AabidLearnFile(){
        radius = 20;
    }
    
    public void update(){
        counter++;
        
        offset = Math.sin(counter/60d * Math.PI*2 * 0.5)*400;
    }
    
    public void render(Graphics2D g){
        g.setColor(Color.BLACK);
        g.drawRect(100, 100, 100, 100);
        g.setColor(Color.red);
        g.drawOval(600, 200, 100, 100);
        g.setColor(Color.GREEN);
        g.fillRect(200, 500, 50, 50);
        
        g.setColor(Color.red);
        g.fill(new Ellipse2D.Double(Main.WIDTH/2 - radius + offset, Main.HEIGHT/2 - radius, radius*2, radius*2));
    }
}
