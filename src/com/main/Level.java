
package com.main;

import java.awt.Graphics2D;

public class Level {
    
    AabidLearnFile temp;
    
    public Level(){
        temp = new AabidLearnFile();
    }
    
    public void update(){
        temp.update();
    }
    
    public void render(Graphics2D g){
        temp.render(g);
    }
}
