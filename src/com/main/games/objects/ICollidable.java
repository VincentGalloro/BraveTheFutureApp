
package com.main.games.objects;

import com.main.Vector;

public interface ICollidable {
    
    public boolean isColliding(Vector pos, Vector size);
    public void handleCollision(Ball b);
    public boolean isDead();
}
