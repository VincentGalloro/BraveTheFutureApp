
package com.main.games;

import com.main.Mouse;
import com.main.SnakeGame;

public class SnakeGameFactory implements GameFactory{

    private Mouse mouse;
    
    public SnakeGameFactory(Mouse mouse){
        this.mouse = mouse;
    }
    
    public IGame createGame() {
        return new SnakeGame(mouse);
    }
}
