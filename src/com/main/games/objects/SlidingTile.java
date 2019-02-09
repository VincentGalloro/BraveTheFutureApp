
package com.main.games.objects;

import com.main.games.structs.Direction;
import com.main.games.structs.SmoothPoint;
import com.ui.IElement;
import java.awt.Point;

public class SlidingTile {
    
    private int index;
    private SmoothPoint pos;
    private IElement element;
    
    public SlidingTile(int index, Point pos, IElement element){
        this.index = index;
        this.pos = new SmoothPoint(pos);
        this.element = element;
    }
    
    public void move(Direction dir){
        
    }
}
