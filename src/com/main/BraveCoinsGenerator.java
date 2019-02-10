
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

public class BraveCoinsGenerator {
    public BufferedImage coin;
    public int xcoord;
    public int ycoord;
    ImageObserver observer;
    
    public BraveCoinsGenerator(int xcoord, int ycoord) throws IOException{
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        coin = ImageIO.read(new File("BTFCoin.png" ));
    }
    
    public void update(){

    }
    
    public void render(Graphics2D g){
        g.drawImage(coin, xcoord, ycoord, observer);
    }
}
