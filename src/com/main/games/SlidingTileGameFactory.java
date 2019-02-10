/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.games;

import com.main.Mouse;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author VGLaptop
 */
public class SlidingTileGameFactory implements GameFactory{
    
    private Mouse mouse;
    
    public SlidingTileGameFactory(Mouse mouse){
        this.mouse = mouse;
    }
    
    public IGame createGame(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res/brave_logo_1.jpg"));
        } catch (IOException ex) {}
        return new SlidingTileGame(img, new Point(6, 3), mouse);
    }
}
