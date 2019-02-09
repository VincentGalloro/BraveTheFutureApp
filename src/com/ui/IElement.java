
package com.ui;

import com.main.Vector;
import java.awt.Graphics2D;

public interface IElement {
    
    public Vector getSize();
    public void render(Graphics2D g);
}
