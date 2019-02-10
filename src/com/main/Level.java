
package com.main;

import com.main.firebase.ViewFirebase;
import com.main.firebase.ViewFirebaseDummy;
import com.main.games.GameContainer;
import com.main.games.SlidingTileGameFactory;
import com.main.menu.MenuButton;
import com.ui.ColumnElement;
import com.ui.IElement;
import com.ui.PaddingElement;
import com.ui.ScaleElement;
import com.ui.TextElement;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

enum State{
    MENU,
    GAME;
}

public class Level {
    
    private Mouse mouse;
    private GameContainer container;
    private TextElement text;
    
    private ViewFirebase firebase;
    private BufferedImage frame;
    
    private ArrayList<MenuButton> menuButtons;
    private IElement menu;
    
    private State state;
    
    public Level(Mouse mouse){
        this.mouse = mouse;
        container = new GameContainer();
        text = new TextElement();
        
        BufferedImage[] menus = new BufferedImage[1];
        try {
            frame = ImageIO.read(new File("res/frame.png"));
            
            menus[0] = ImageIO.read(new File("res/slidingTiles.PNG"));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        container.setGame(new SnakeGame());
        
        firebase = new ViewFirebaseDummy();
        
        menuButtons = new ArrayList<>();
        menuButtons.add(new MenuButton(menus[0], new SlidingTileGameFactory(mouse), mouse));
        
        ColumnElement menuCol = new ColumnElement();
        for(MenuButton mb : menuButtons){ menuCol.addElement(mb); }
        
        menu = new ScaleElement().setSize(new Vector(Main.WIDTH, Main.HEIGHT)).setElement(
                new PaddingElement().setPadding(60).setElement(menuCol));
    }
    
    public void update(){
        mouse.update();
        container.update();
        
        
        //text.setText(firebase);
    }
    
    public void render(Graphics2D g){
        container.render(g);
        //menu.render(g);
        
        g.drawImage(frame, 0, 0, Main.WIDTH, Main.HEIGHT, null);
        
        AffineTransform save = g.getTransform();
        
        g.translate(Main.WIDTH-200, 50);
        text.render(g);
        
        g.setTransform(save);
    }
}
