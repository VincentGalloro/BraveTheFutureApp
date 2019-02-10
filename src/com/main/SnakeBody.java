
package com.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class SnakeBody {
    ArrayList<Integer> xcoordinates;
    ArrayList<Integer> ycoordinates;
    int xcoord;
    int ycoord;
    
    public SnakeBody(int initxcoord, int initycoord){
        xcoordinates = new ArrayList<>();
        ycoordinates = new ArrayList<>();
        xcoordinates.add(initxcoord);
        ycoordinates.add(initycoord);
        xcoord = -1000;
        ycoord = -1000;
    }
    
    public void update(int head_xcoord, int head_ycoord){
        xcoordinates.add(head_xcoord);
        ycoordinates.add(head_ycoord);
        if(xcoordinates.size() < 6){ return; }
        this.xcoord = (int)xcoordinates.remove(0);
        this.ycoord = (int)ycoordinates.remove(0);
    }
    
    public void render(Graphics2D g){
        g.setColor(Color.GREEN);
        g.fillOval(xcoord, ycoord, 55, 30);
    }
}
