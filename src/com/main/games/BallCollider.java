
package com.main.games;

import com.main.Vector;
import com.main.games.objects.Ball;
import com.main.games.objects.ICollidable;
import java.util.ArrayList;

public class BallCollider {
    
    private ArrayList<ICollidable> collidables;
    private Ball ball;
    
    public BallCollider(Ball ball){
        collidables = new ArrayList<>();
        this.ball = ball;
    }
    
    public void addCollidable(ICollidable c){ collidables.add(c); }
    public void addCollidables(ArrayList<? extends ICollidable> c){ collidables.addAll(c); }
    
    public void update(){
        for(ICollidable c : collidables){
            if(c.isColliding(ball.getPos(), new Vector(ball.getRadius()).twice())){
                c.handleCollision(ball);
            }
        }
        for(int i = collidables.size()-1; i >= 0; i--){
            if(collidables.get(i).isDead()){ collidables.remove(i); }
        }
    }
}
