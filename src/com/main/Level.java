
package com.main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Level {
    
    public Level(){
        
    }
    
    public void update(){
    }
    
    public void render(Graphics2D g){
        g.setColor(Color.red);
        g.drawOval(100, 100, 100, 100);
    }
}
