/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.games;

import com.main.Mouse;

/**
 *
 * @author VGLaptop
 */
public class BreakoutGameFactory implements GameFactory{
    
    private Mouse mouse;
    
    public BreakoutGameFactory(Mouse mouse){
        this.mouse = mouse;
    }
    
    public IGame createGame(){
        return new BreakoutGame(mouse);
    }
}
