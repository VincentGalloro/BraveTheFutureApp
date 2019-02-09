
package com.main.games.structs;

import com.main.Vector;
import java.awt.Point;

public class SmoothPoint {

    private Vector smooth;
    private Point pos;
    
    public SmoothPoint(Point pos){
        this.pos = pos;
        this.smooth = new Vector(pos);
    }
    
    public void setPoint(Point pos){ this.pos = pos; }
    
    public Point getPoint(){ return pos; }
    public Vector getSmooth(){ return smooth; }
    
    public void update(){
        smooth = smooth.multiply(0.9).add(new Vector(pos).multiply(0.1));
    }
}
