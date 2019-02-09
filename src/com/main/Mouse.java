package com.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;

public class Mouse implements MouseListener, MouseMotionListener{

    public static final int LEFT = 0, RIGHT = 1, MIDDLE = 2;
    private final int[] BUTTON_CODES = {MouseEvent.BUTTON1, MouseEvent.BUTTON3, MouseEvent.BUTTON2};
    
    public boolean[] buttons = new boolean[BUTTON_CODES.length], buttonsClick = new boolean[BUTTON_CODES.length];
    private boolean[] buttonsLast = new boolean[BUTTON_CODES.length];
    private int x, y;
    private Vector pos;
    
    public Vector getTransformedPos(AffineTransform at){
        double[] out = {pos.x, pos.y};
        at.transform(out, 0, out, 0, 1);
        return new Vector(out[0], out[1]);
    }
    
    public void update(){
        for(int i = 0; i < BUTTON_CODES.length; i++){
            buttonsClick[i] = !buttonsLast[i] && buttons[i];
            buttonsLast[i] = buttons[i]; 
        }
        pos = new Vector(x, y);
    }
    
    public void mousePressed(MouseEvent e) {
        for(int i = 0; i < BUTTON_CODES.length; i++){
            if(e.getButton()== BUTTON_CODES[i]){
                buttons[i] = true;
                break;
            }
        }
    }
    public void mouseReleased(MouseEvent e) {
        for(int i = 0; i < BUTTON_CODES.length; i++){
            if(e.getButton()== BUTTON_CODES[i]){
                buttons[i] = false;
                break;
            }
        }
    }
    public void mouseDragged(MouseEvent e) { x = e.getX(); y = e.getY(); }
    public void mouseMoved(MouseEvent e) { x = e.getX(); y = e.getY(); }
    
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
}
