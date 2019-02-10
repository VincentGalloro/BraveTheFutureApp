
package com.main;

import com.main.games.IGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.util.ArrayList;

public class SnakeGame implements IGame{
    
    public static final Font font1 = new Font("Courier", Font.BOLD, 50);
    
    private Mouse mouse;
    SnakeHead head;
    ArrayList<SnakeBody> body;
    ArrayList<BraveCoinsGenerator> coin;
    CollisionDetector detect;
    int initxcoord;
    int initycoord;
    int xvel;
    int yvel;
    int score;
    
    //random.nextInt(max - min + 1) + min
    public SnakeGame(Mouse mouse){
        this.mouse = mouse;
        Random random = new Random();
        
        score = 0;
        
        //Create head
        initxcoord = random.nextInt(Main.WIDTH - 90 - 90 + 1) + 90;
        initycoord = random.nextInt(Main.HEIGHT - 90 - 90 + 1) + 90;
        xvel = random.nextInt(4)+2;
        yvel = random.nextInt(4)+2;
        head = new SnakeHead(initxcoord, initycoord, xvel, yvel, mouse);
       
        //Create body
        body = new ArrayList<SnakeBody>();
        body.add(new SnakeBody(initxcoord, initycoord));
        
        //Creat coins
        coin = new ArrayList<BraveCoinsGenerator>();
        for(int i = 0; i < 5; i++){
            coin.add(new BraveCoinsGenerator(random.nextInt((int)getSize().x - 90 - 90 + 1) + 90, 
            random.nextInt((int)getSize().y - 90 - 90 + 1) + 90));
        }
        
        //Create collisoin detector
        detect = new CollisionDetector(head.xcoord, head.ycoord, coin);
    }
    
    public void update(AffineTransform at){
        Random random = new Random();
        head.update(getSize(), mouse.getTransformedPos(at));
        detect.update(head.xcoord, head.ycoord, coin);
        detect.check();
        for (int x = coin.size() - 1; x >= 0; x--){
           if (coin.get(x).hit_flag == true){
               score ++;
               coin.remove(x);
               coin.add(new BraveCoinsGenerator(random.nextInt((int)getSize().x - 90 - 90 + 1) + 90,
               random.nextInt((int)getSize().y - 90 - 90 + 1) + 90));
               body.add(new SnakeBody(-100, -100));
           }
       }  
        for (int x = 0; x < body.size(); x++){
            if(x==0){
                body.get(x).update(head.xcoord , head.ycoord);
            }
            else{
                SnakeBody b = body.get(x-1);
                body.get(x).update(b.xcoord, b.ycoord);
            }
       }  

    }
    
    public void render(Graphics2D g){
        g.setColor(Color.DARK_GRAY);
        g.fill3DRect(0, 0, 40, (int)getSize().y, true);
        g.fill3DRect((int)getSize().x - 40, 0, 40, (int)getSize().y, true);
        g.fill3DRect(0, 0, (int)getSize().x, 40, true);
        g.fill3DRect(0, (int)getSize().y - 40, (int)getSize().x, 40, true);
        
        //Create Scoreboard
        g.setColor(new Color(0xc71566));
        g.fill3DRect((int)getSize().x - 140, 40, 100, 200, true);
        g.setColor(Color.BLACK);
        FontRenderContext frc = g.getFontRenderContext();
        TextLayout tl = new TextLayout(Integer.toString(score), font1, frc);
        tl.draw(g, (int)getSize().x - 100, 160);
        
        head.render(g);
        for (int x = 0; x < body.size(); x++){
            body.get(x).render(g);
        }
        for (int x = 0; x < coin.size(); x++){
            coin.get(x).render(g);
        }  
        
    }

    public boolean checkWin() {
        return false;
    }

    public Vector getSize() {
        return new Vector(700, 1300).multiply(1);
    }
}
