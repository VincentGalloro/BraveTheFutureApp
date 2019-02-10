
package com.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CollisionDetector {
    int headxcoord;
    int headycoord;
    ArrayList<BraveCoinsGenerator> coin;
    
    public CollisionDetector(int headxcoord, int headycoord, ArrayList coins){
           this.headxcoord = headxcoord;
           this.headycoord = headycoord;
           this.coin = coins;
    }
    
    public void update(int headxcoord, int headycoord, ArrayList coins){
           this.headxcoord = headxcoord;
           this.headycoord = headycoord;
           this.coin = coins;
    }
    
    public void check(){
        double s = 25 + 25;
        for (int i = 0; i < coin.size(); i++){
            if ((headxcoord >= coin.get(i).xcoord - s && headxcoord <= coin.get(i).xcoord + s) && 
            (headycoord >= coin.get(i).ycoord - s && headycoord <= coin.get(i).ycoord + s)){
                System.out.println("HIT");
            }
        }
    }
}
