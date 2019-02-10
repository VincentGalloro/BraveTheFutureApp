
package com.main;

import com.main.firebase.ViewFirebase;
import com.main.firebase.ViewFirebaseDummy;
import com.main.games.BreakoutGameFactory;
import com.main.games.GameContainer;
import com.main.games.SlidingTileGameFactory;
import com.main.games.SnakeGameFactory;
import com.main.menu.MenuButton;
import com.ui.ColumnElement;
import com.ui.IContainer;
import com.ui.IListContainer;
import com.ui.PaddingElement;
import com.ui.ScaleElement;
import com.ui.TextElement;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

enum State{
    INTRO,
    MENU,
    GAME;
}

public class Level {
    
    private Mouse mouse;
    private GameContainer container;
    private TextElement text;
    
    private ViewFirebase firebase;
    private BufferedImage frame, intro;
    
    private ArrayList<MenuButton> menuButtons;
    private IContainer menu, pad;
    private IListContainer col;
    
    private State state;
    
    public Level(Mouse mouse){
        this.mouse = mouse;
        container = new GameContainer();
        text = new TextElement();
        state = State.INTRO;
        
        BufferedImage[] menus = new BufferedImage[3];
        
        try {
            frame = ImageIO.read(new File("res/frame.png"));
            intro = ImageIO.read(new File("res/intro.jpg"));
            
            menus[0] = ImageIO.read(new File("res/slidingTiles.PNG"));
            menus[1] = ImageIO.read(new File("res/breakout.PNG"));
            menus[2] = ImageIO.read(new File("res/snake.PNG"));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        //container.setGame(new SnakeGame());
        
        firebase = new ViewFirebaseDummy();
        
        menuButtons = new ArrayList<>();
        menuButtons.add(new MenuButton(menus[0], new SlidingTileGameFactory(mouse), mouse));
        menuButtons.add(new MenuButton(menus[1], new BreakoutGameFactory(mouse), mouse));
        menuButtons.add(new MenuButton(menus[2], new SnakeGameFactory(mouse), mouse));
        
        col = new ColumnElement().setSeperation(20);
        for(MenuButton mb : menuButtons){ col.addElement(mb); }
        
        pad = new PaddingElement().setPadding(60).setElement(col);
        menu = new ScaleElement().setSize(new Vector(Main.WIDTH, Main.HEIGHT)).setElement(pad);
                
    }
    
    public void update(){
        mouse.update();
        
        if(state == State.INTRO){
            if(mouse.buttonsClick[Mouse.LEFT]){
                state = State.MENU;
            }
        }
        else if(state == State.GAME){
            container.update();
            
            if(mouse.buttonsClick[Mouse.RIGHT]){
                state = State.MENU;
            }
        }
        else if(state == State.MENU){
            for(int i = 0; i < menuButtons.size(); i++){
                AffineTransform at = new AffineTransform();
                menu.applyTrasform(at);
                pad.applyTrasform(at);
                col.applyTrasform(at, i);
                try {
                    at.invert();
                } catch (NoninvertibleTransformException ex) {}
                menuButtons.get(i).update(at);
                if(menuButtons.get(i).isClicked()){
                    container.setGame(menuButtons.get(i).createGame());
                    state = State.GAME;
                    break;
                }
            }
        }
        
        //text.setText(firebase);
    }
    
    public void render(Graphics2D g){
        if(state == State.GAME){
            container.render(g);
        }
        else if(state == State.MENU){
            menu.render(g);
        }
        else if(state == State.INTRO){
            int s = 45;
            int t = 10;
            g.drawImage(intro, t, s, Main.WIDTH-t*2, Main.HEIGHT-s*2, null);
        }
        
        g.drawImage(frame, 0, 0, Main.WIDTH, Main.HEIGHT, null);
        
        AffineTransform save = g.getTransform();
        
        g.translate(Main.WIDTH-200, 50);
        text.render(g);
        
        g.setTransform(save);
    }
}
