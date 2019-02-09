
package com.main.games;

import com.main.Main;
import com.main.Vector;
import com.ui.PaddingElement;
import com.ui.RowElement;
import com.ui.ScaleElement;
import com.ui.TextElement;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

public class GameContainer {
    
    private IGame game;
    private ScaleElement frame, gameWindow;
    private PaddingElement padding;
    private TextElement text;

    public GameContainer(){
        frame = new ScaleElement();
        gameWindow = new ScaleElement();
        padding = new PaddingElement();
        text = new TextElement();
        
        frame.setSize(new Vector(Main.WIDTH, Main.HEIGHT));
        gameWindow.setSize(new Vector(Main.WIDTH, Main.HEIGHT));
        padding.setPadding(100);
        text.setText("LOSER");
        
        frame.setElement(padding.setElement(new RowElement().setSeperation(100).addElement(
                gameWindow).addElement(
                text)));
    }
    
    public void update(){
        if(game != null){
            AffineTransform at = new AffineTransform();
            frame.applyTrasform(at);
            padding.applyTrasform(at);
            gameWindow.applyTrasform(at);
            try {
                at.invert();
                game.update(at);
            } catch (NoninvertibleTransformException ex) {}
            
            if(game.checkWin()){
                text.setText("WINNER");
            }
        }
    }
    
    public void setGame(IGame game){
        this.game = game;
        this.gameWindow.setElement(game);
    }
    
    public void render(Graphics2D g){
        frame.render(g);
    }
}
