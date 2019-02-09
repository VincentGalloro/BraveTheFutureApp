
package com.main.games.objects;

import com.main.games.structs.Direction;
import com.main.games.structs.SmoothPoint;
import com.ui.IElement;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class SlidingTile {
    
    private int index;
    private SmoothPoint pos;
    private IElement element;
    
    public SlidingTile(int index, Point pos, IElement element){
        this.index = index;
        this.pos = new SmoothPoint(pos);
        this.element = element;
    }
    
    public Point getPos(){
        return pos.getPoint();
    }
    
    public int getIndex(){ return index; }
    
    public void move(Direction dir){
        pos.setPoint(dir.move(pos.getPoint()));
    }
    
    public void update(){
        pos.update();
    }
    
    public void render(Graphics2D g){
        AffineTransform save = g.getTransform();
        g.translate(pos.getSmooth().x, pos.getSmooth().y);
        element.render(g);
        g.setTransform(save);
    }
}
