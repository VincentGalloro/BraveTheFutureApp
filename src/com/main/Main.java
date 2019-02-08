package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Main extends Canvas implements Runnable{  
    
    public static final int WIDTH = 1300;
    public static final int HEIGHT = 700;
    private static final String TITLE = "";
    
    public static final Color BACKGROUND_COLOR = Color.WHITE;

    private JFrame frame;
    
    private Level level;
    //private Keyboard keyboard;
    //private Mouse mouse;
    
    public Main(){
        //TinySound.init();
        level = new Level();
        /*keyboard = new Keyboard();
        addKeyListener(keyboard);   
        mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);*/
    }

    public synchronized void start() {
        new Thread(this).start();
    }

    public void run() {	
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D/60D;
        double delta = 0;

        while (true){
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            while(delta >= 1){   
                delta -= 1;       
                update();     
            }
            render();
        }
    }
    
    public void update(){
        //keyboard.update();
        //mouse.update();
        level.update();
    }
    
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(2);
            return;
        }      
        Graphics g = bs.getDrawGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        level.render((Graphics2D)g);
        
        g.dispose();
        bs.show();     
    }
    
    public static void main(String[] args) {
        Main game = new Main();
	game.frame = new JFrame(TITLE);
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	game.frame.add(game);
        game.frame.pack();
	game.frame.setVisible(true);
	game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        
        game.start();
    }
}