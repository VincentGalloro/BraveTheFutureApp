
package com.main.games;

import com.main.SnakeGame;

public class SnakeGameFactory implements GameFactory{

    public IGame createGame() {
        return new SnakeGame();
    }
}
