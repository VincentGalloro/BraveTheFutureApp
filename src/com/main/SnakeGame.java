
package com.main;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class SnakeGame {
    SnakeHead head;
    ArrayList<SnakeBody> body;
    ArrayList<BraveCoinsGenerator> coin;
    CollisionDetector detect;
    int initxcoord;
    int initycoord;
    int xvel;
    int yvel;
    
    //random.nextInt(max - min + 1) + min
    public SnakeGame() throws IOException{
        Random random = new Random();
        
        //Create head
        initxcoord = random.nextInt(Main.WIDTH - 90 - 90 + 1) + 90;
        initycoord = random.nextInt(Main.HEIGHT - 90 - 90 + 1) + 90;
        xvel = random.nextInt(4)+2;
        yvel = random.nextInt(4)+2;
        head = new SnakeHead(initxcoord, initycoord, xvel, yvel);
       
        //Create body
        body = new ArrayList<SnakeBody>();
        for(int i = 0; i < 10; i++){
            body.add(new SnakeBody(initxcoord, initycoord));
        }
        
        //Creat coins
        coin = new ArrayList<BraveCoinsGenerator>();
        for(int i = 0; i < 10; i++){
            coin.add(new BraveCoinsGenerator(random.nextInt(Main.WIDTH - 90 - 90 + 1) + 90, random.nextInt(Main.HEIGHT - 90 - 90 + 1) + 90));
        }
        
        //Create collisoin detector
        detect = new CollisionDetector(head.xcoord, head.ycoord, coin);
    }
    
    public void update(){
        head.update();
        detect.update(head.xcoord, head.ycoord, coin);
        detect.check();
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
        head.render(g);
        for (int x = 0; x < body.size(); x++){
            body.get(x).render(g);
            coin.get(x).render(g);
        }  
        
    }
}
