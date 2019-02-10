
package com.main.games.objects;

import com.main.Vector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ball {
    
    private double radius;
    private Vector pos, vel;
    
    public Ball(Vector pos, Vector vel){
        this.pos = pos;
        this.vel = vel;
        this.radius = 10;
    }
    
    public void update(){
        pos = pos.add(vel);
        
        if(pos.x-radius < 0){ vel.x = Math.abs(vel.x); }
        if(pos.x+radius >= 300){ vel.x = -Math.abs(vel.x); }
        if(pos.y-radius < 0){ vel.y = Math.abs(vel.y); }
        if(pos.y+radius >= 700){ vel.y = -Math.abs(vel.y); }
    }
    
    public Vector getVel(){
        return vel;
    }
    
    public Vector getPos(){ return pos; }
    public double getRadius(){ return radius; }
    
    public void render(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fill(new Ellipse2D.Double(pos.x-radius, pos.y-radius, radius*2, radius*2));
    }
}
