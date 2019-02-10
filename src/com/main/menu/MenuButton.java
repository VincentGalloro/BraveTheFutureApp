
package com.main.menu;

import com.main.Mouse;
import com.main.Vector;
import com.main.games.GameFactory;
import com.main.games.IGame;
import com.ui.BoxElement;
import com.ui.IElement;
import com.ui.ImageElement;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class MenuButton implements IElement{
    
    private BoxElement box;
    private Mouse mouse;
    private GameFactory factory;
    private boolean clicked;
    
    public MenuButton(BufferedImage img, GameFactory factory, Mouse mouse){
        box = new BoxElement().setOutlineColor(Color.BLACK).setElement(new ImageElement().setImage(img));
        this.factory = factory;
        this.mouse = mouse;
    }
    
    public void update(AffineTransform at){
        Vector p = mouse.getTransformedPos(at);
        clicked = mouse.buttonsClick[Mouse.LEFT] && p.x >= 0 && p.x < box.getSize().x && p.y >= 0 && p.y < box.getSize().y;
    }
    
    public boolean isClicked(){ return clicked; }
    
    public IGame createGame(){
        return factory.createGame();
    }

    public Vector getSize() {
        return box.getSize();
    }

    public void render(Graphics2D g) {
        box.render(g);
    }
}
