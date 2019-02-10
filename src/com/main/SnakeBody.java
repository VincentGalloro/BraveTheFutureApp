
package com.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class SnakeBody {
    ArrayList<Integer> xcoordinates;
    ArrayList<Integer> ycoordinates;
    int xcoord;
    int ycoord;
    Color c;
    
    public SnakeBody(int initxcoord, int initycoord){
        xcoordinates = new ArrayList<>();
        ycoordinates = new ArrayList<>();
        xcoordinates.add(initxcoord);
        ycoordinates.add(initycoord);
        xcoord = -1000;
        ycoord = -1000;
        
        Color[] cs = {new Color(0x0077CA),
                new Color(0x84BD00),
                new Color(0xFFCD3A),
                new Color(0x804693),
                new Color(0x41B6E6)};
        Random random = new Random();
        c = cs[random.nextInt(cs.length)];
    }
    
    public void update(int head_xcoord, int head_ycoord){
        xcoordinates.add(head_xcoord);
        ycoordinates.add(head_ycoord);
        if(xcoordinates.size() < 6){ return; }
        this.xcoord = (int)xcoordinates.remove(0);
        this.ycoord = (int)ycoordinates.remove(0);
    }
    
    public void render(Graphics2D g){
        g.setColor(c);
        g.fillOval(xcoord, ycoord, 55, 30);
    }
}
