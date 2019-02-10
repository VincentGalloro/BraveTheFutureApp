
package com.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.*;

public class SnakeHead {
    public int xcoord;
    public int ycoord;
    int xvel;
    int yvel;
    boolean collision;
    
    public SnakeHead(int initxcoord, int initycoord, int xvel, int yvel){
        this.xcoord = initxcoord;
        this.ycoord = initycoord;
        this.xvel = 4;
        this.yvel = 4;
    }
    
    public void update(){
        xcoord += xvel;
        ycoord += yvel;
        
        collision=false;
        if (ycoord >= Main.HEIGHT - 90){
            yvel = -yvel;
            collision = true;
        }
        else if (ycoord <=50){
            yvel = -yvel;
            collision = true;
        }
        
        if (xcoord >= Main.WIDTH - 90){
            xvel = -xvel;
            collision = true;
        }
        else if (xcoord <=50){
            xvel = -xvel;
            collision = true;
        }
        
        if(collision){
            //JOptionPane.showMessageDialog(null, "Game Over!");
           // System.exit(0);
        }
    }
    
    public void render(Graphics2D g){
        g.setColor(Color.DARK_GRAY);
        g.fill3DRect(0, 10, 40, Main.HEIGHT, true);
        g.fill3DRect(Main.WIDTH - 40, 10, 40, Main.HEIGHT, true);
        g.fill3DRect(0, 10, Main.WIDTH, 40, true);
        g.fill3DRect(0, Main.HEIGHT - 40, Main.WIDTH, 40, true);
        
        g.setColor(Color.GREEN);
        g.fillOval(xcoord, ycoord, 75, 50);
         g.setColor(Color.BLACK);
        if (yvel < 0){
            if (xvel > 0){
                g.fillOval(xcoord + 40, ycoord + 10, 25, 10);
            }
            else{
                g.fillOval(xcoord + 20, ycoord + 10, 25, 10);
            }
        }
   
        else{
            if (xvel < 0){
                g.fillOval(xcoord + 10, ycoord + 10, 25, 10);
            }
            else{
                g.fillOval(xcoord + 40, ycoord + 10, 25, 10);
            }
        }
    }
}
