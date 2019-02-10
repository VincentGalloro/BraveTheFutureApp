
package com.main;

import java.awt.Color;
import java.awt.Graphics2D;

public class SnakeHead {
    
    
    
    Mouse mouse;
    public int xcoord;
    public int ycoord;
    double angle;
    boolean collision;
    
    public SnakeHead(int initxcoord, int initycoord, int xvel, int yvel, Mouse mouse){
        this.mouse = mouse;
        this.xcoord = initxcoord;
        this.ycoord = initycoord;
    }
    
    public void update(Vector size, Vector p){
        double turnSpeed = 0.1;
        p = p.subtract(new Vector(75, 50).half());
        double tAngle = new Vector(xcoord, ycoord).getAngle(p);
        if(Math.abs(angle - tAngle) < turnSpeed){
            angle = tAngle;
        }
        else{
            if((angle < tAngle) == (Math.abs(angle-tAngle) < Math.PI)){ angle += turnSpeed; }
            else{ angle -= turnSpeed; }
        }
        
        if(angle < 0){ angle += Math.PI*2; }
        if(angle >= Math.PI*2){ angle -= Math.PI*2; }
        
        Vector v = new Vector().move(angle, 6);
        double xvel = v.x;
        double yvel = v.y;
        
        xcoord += xvel;
        ycoord += yvel;
        
        collision=false;
        if (ycoord >= size.y - 90){
            yvel = -Math.abs(yvel);
            collision = true;
        }
        else if (ycoord <=50){
            yvel = Math.abs(yvel);
            collision = true;
        }
        
        if (xcoord >= size.x - 90){
            xvel = -Math.abs(xvel);
            collision = true;
        }
        else if (xcoord <=50){
            xvel = Math.abs(xvel);
            collision = true;
        }
        
        angle = new Vector().getAngle(new Vector(xvel, yvel));
        
        if(collision){
            //JOptionPane.showMessageDialog(null, "Game Over!");
           // System.exit(0);
        }
    }
    
    public void render(Graphics2D g){
        
        g.setColor(Color.GREEN);
        g.fillOval(xcoord, ycoord, 75, 50);
         g.setColor(Color.BLACK);
         
        Vector v = new Vector().move(angle, 1);
        double xvel = v.x;
        double yvel = v.y;
         
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
