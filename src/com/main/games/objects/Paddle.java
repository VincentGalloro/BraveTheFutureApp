
package com.main.games.objects;

import com.main.Mouse;
import com.main.Vector;
import com.ui.BoxElement;
import com.ui.FixedSizeElement;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Paddle implements ICollidable{
    
    private static final Random random = new Random();
    
    private Mouse mouse;
    private Vector pos;
    private BoxElement paddle;
    
    public Paddle(Mouse mouse){
        this.mouse = mouse;
        
        pos = new Vector(0, 680);
        paddle = new BoxElement().setOutlineColor(Color.BLACK).setFillColor(new Color(0, 150, 255));
        paddle.setElement(new FixedSizeElement().setSize(new Vector(100, 20)));
    }
    
    public void update(AffineTransform at){
        pos.x = mouse.getTransformedPos(at).x;
    }
    
    public void render(Graphics2D g){
        AffineTransform save = g.getTransform();
        
        g.translate(pos.x - paddle.getSize().x/2, pos.y);
        paddle.render(g);
        
        g.setTransform(save);
    }

    public boolean isColliding(Vector pos, Vector size) {
        return Math.abs(pos.x - this.pos.x) <= (size.x+paddle.getSize().x)/2 &&
                Math.abs(pos.y - this.pos.y) <= (size.y+paddle.getSize().y)/2;
    }

    public void handleCollision(Ball b) {
        b.getVel().y = -Math.abs(b.getVel().y);
        b.getVel().x = random.nextDouble()*10-5;
    }
    
    public boolean isDead(){ return false; }
}
