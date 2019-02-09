
package com.main.games;

import com.main.Mouse;
import com.main.Vector;
import com.main.games.objects.SlidingTile;
import com.main.games.structs.Direction;
import com.ui.BoxElement;
import com.ui.IElement;
import com.ui.ImageElement;
import com.ui.ScaleElement;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class SlidingTileGame implements IGame, IElement{

    private Mouse mouse;
    private ArrayList<SlidingTile> tiles;
    private SlidingTile[][] tileGrid;
    private Point size;
    
    private int shuffleMoves = 100;
    private int shuffleTime = 0;
    private Direction lShuffle;
    
    public SlidingTileGame(BufferedImage img, Point size, Mouse mouse){
        this.size = size;
        this.mouse = mouse;
        tiles = new ArrayList<>();
        tileGrid = new SlidingTile[size.x][size.y];
        
        for(int i = 0; i < size.x*size.y; i++){
            if(i==5){ continue; }
            
            Point p = new Point(i%size.x, i/size.x);
            BufferedImage tile = img.getSubimage(
                    p.x*img.getWidth()/size.x, 
                    p.y*img.getHeight()/size.y,
                    (p.x+1)*img.getWidth()/size.x - p.x*img.getWidth()/size.x,
                    (p.y+1)*img.getHeight()/size.y - p.y*img.getHeight()/size.y);
            addTile(new SlidingTile(i, new Point(i%size.x, i/size.x), 
                    new BoxElement().setOutlineColor(Color.BLACK).setElement(
                            new ScaleElement().setSize(new Vector(1)).setElement(new ImageElement().setImage(tile)))));
        }
    }
    
    public void addTile(SlidingTile st){
        tiles.add(st);
        tileGrid[st.getPos().x][st.getPos().y] = st;
    }
    
    public void moveTile(SlidingTile st, Direction dir){
        tileGrid[st.getPos().x][st.getPos().y] = null;
        st.move(dir);
        tileGrid[st.getPos().x][st.getPos().y] = st;
    }
    
    public Vector getSize() {
        return new Vector(size);
    }
    
    public void update(AffineTransform at) {
        for(SlidingTile st : tiles){
            st.update();
        }
        
        if(mouse.buttonsClick[Mouse.LEFT]){
            handleClick(mouse.getTransformedPos(at).floor().toPoint());
        }
        
        Random random = new Random();
        shuffleTime++;
        if(shuffleMoves > 0 && shuffleTime >= 5){
            shuffleTime = 0;
            shuffleMoves--;
            lShuffle = shuffleMove(random).opposite();
        }
    }
    
    public boolean checkWin(){
        if(shuffleMoves > 0){ return false; }
        for(int i = 0; i < size.x*size.y; i++){
            SlidingTile st = tileGrid[i%size.x][i/size.x];
            if(st != null && st.getIndex() != i){ return false; }
        }
        return true;
    }
    
    public Direction shuffleMove(Random random){
        for(int y = 0; y < size.y; y++){
            for(int x = 0; x < size.x; x++){
                if(tileGrid[x][y] == null){
                    ArrayList<Direction> dirs = new ArrayList<>();
                    for(Direction d : Direction.values()){
                        if(d == lShuffle){ continue; }
                        Point n = d.move(new Point(x, y));
                        if(withinBounds(n) && tileGrid[n.x][n.y] != null){
                            dirs.add(d);
                        }
                    }
                    Direction d = dirs.get(random.nextInt(dirs.size()));
                    Point n = d.move(new Point(x, y));
                    moveTile(tileGrid[n.x][n.y], d.opposite());
                    return d;
                }
            }
        }
        return null;
    }
    
    public boolean withinBounds(Point pos){
        return pos.x >= 0 && pos.x < size.x && pos.y >= 0 && pos.y < size.y;
    }
    
    public void handleClick(Point pos){
        //System.out.println(pos);
        if(!withinBounds(pos) || tileGrid[pos.x][pos.y]==null){ return; }
        for(Direction d : Direction.values()){
            Point n = d.move(pos);
            //System.out.println(n);
            if(withinBounds(n) && tileGrid[n.x][n.y]==null){
                moveTile(tileGrid[pos.x][pos.y], d);
                break;
            }
        }
    }

    public void render(Graphics2D g) {
        for(SlidingTile st : tiles){
            st.render(g);
        }
    }
}
