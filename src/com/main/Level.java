
package com.main;

import com.main.games.GameContainer;
import com.main.games.SlidingTileGame;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Level {
    
    private Mouse mouse;
    private GameContainer container;
    
    public Level(Mouse mouse){
        this.mouse = mouse;
        container = new GameContainer();
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res/brave_logo_1.jpg"));
        } catch (IOException ex) {}
        container.setGame(new SlidingTileGame(img, new Point(6, 3), mouse));
    }
    
    public void update(){
        mouse.update();
        container.update();
    }
    
    public void render(Graphics2D g){
        container.render(g);
    }
}
