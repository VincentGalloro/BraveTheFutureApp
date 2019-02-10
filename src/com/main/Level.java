
package com.main;

import com.main.firebase.ViewFirebase;
import com.main.firebase.ViewFirebaseDummy;
import com.main.games.BreakoutGame;
import com.main.games.GameContainer;
import com.ui.TextElement;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Level {
    
    private Mouse mouse;
    private GameContainer container;
    private TextElement text;
    
    private ViewFirebase firebase;
    private BufferedImage frame;
    
    public Level(Mouse mouse){
        this.mouse = mouse;
        container = new GameContainer();
        text = new TextElement();
        
        firebase = new ViewFirebaseDummy();
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res/brave_logo_1.jpg"));
            frame = ImageIO.read(new File("res/frame.png"));
        } catch (IOException ex) {}
        container.setGame(new BreakoutGame(mouse));
    }
    
    public void update(){
        mouse.update();
        container.update();
        
        //text.setText(firebase);
    }
    
    public void render(Graphics2D g){
        container.render(g);
        
        g.drawImage(frame, 0, 0, Main.WIDTH, Main.HEIGHT, null);
        
        AffineTransform save = g.getTransform();
        
        g.translate(Main.WIDTH-200, 50);
        text.render(g);
        
        g.setTransform(save);
    }
}
