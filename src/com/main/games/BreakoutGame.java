
package com.main.games;

import com.main.Mouse;
import com.main.Vector;
import com.main.games.objects.Ball;
import com.main.games.objects.Brick;
import com.main.games.objects.Paddle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

public class BreakoutGame implements IGame{

    private Vector size;
    private ArrayList<Brick> bricks;
    private Paddle paddle;
    private Ball ball;
    private BallCollider collider;
    
    public BreakoutGame(Mouse mouse){
        size = new Vector(300, 700);
        bricks = new ArrayList<>();
        paddle = new Paddle(mouse);
        ball = new Ball(new Vector(150, 400), new Vector(2, -6));
        collider = new BallCollider(ball);
        collider.addCollidable(paddle);
        
        Color[] cs = {new Color(0x0077CA),
                new Color(0x84BD00),
                new Color(0xFFCD3A),
                new Color(0x804693),
                new Color(0x41B6E6)};
        
        Random random = new Random();
        for(int y = 0; y < 6; y++){
            for(int x = 0; x < 6 + (1 - y%2); x++){
                Color c = cs[random.nextInt(cs.length)];
                int s = ((x==0 || x==6) && y%2==0) ? 25 : 50;
                int o = (y%2==0 && x!=0) ? -25 : 0;
                bricks.add(new Brick(new Vector(x, y).multiply(new Vector(50, 25)).add(new Vector(o, 100)), 
                        new Vector(s, 25), c));
            }
        }
        
        collider.addCollidables(bricks);
    }
    
    public Vector getSize() {
        return size;
    }
    
    public boolean checkWin() {
        return false;
    }

    public void update(AffineTransform transform) {
        for(int i = bricks.size()-1; i >= 0; i--){
            if(bricks.get(i).isDead()){ bricks.remove(i); }
        }
        paddle.update(transform);
        ball.update();
        collider.update();
    }

    public void render(Graphics2D g) {
        for(Brick b : bricks){
            b.render(g);
        }
        paddle.render(g);
        ball.render(g);
    }
    
}
