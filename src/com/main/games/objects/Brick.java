
package com.main.games.objects;

import com.main.Vector;
import com.ui.BoxElement;
import com.ui.FixedSizeElement;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Brick implements ICollidable{
    
    private Vector pos, size;
    private BoxElement element;
    private boolean dead;
    
    public Brick(Vector pos, Vector size, Color color){
        this.pos = pos;
        this.size = size;
        
        element = new BoxElement().setFillColor(color).setElement(new FixedSizeElement().setSize(size));
        element.setOutlineColor(Color.BLACK);
    }
    
    public void render(Graphics2D g){
        AffineTransform save = g.getTransform();
        g.translate(pos.x, pos.y);
        element.render(g);
        g.setTransform(save);
    }

    public boolean isColliding(Vector pos, Vector size) {
    
        return Math.abs(this.pos.x+size.x/2 - pos.x) <= (size.x+size.x)/2 &&
                Math.abs(this.pos.y+size.y/2 - pos.y) <= (size.y+size.y)/2;
    }

    public void handleCollision(Ball b) {
        dead = true;
        if(Math.abs(b.getPos().x-(pos.x+size.x/2)) < Math.abs(b.getPos().y-(pos.y+size.y/2))){
            b.getVel().y = -b.getVel().y;
        }
        else{
            b.getVel().x = -b.getVel().x;
        }
    }
    
    public boolean isDead(){
        return dead;
    }
}
