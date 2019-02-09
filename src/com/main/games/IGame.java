
package com.main.games;

import com.ui.IElement;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public interface IGame extends IElement{
    
    public boolean checkWin();
    
    public void update(AffineTransform transform);
    public void render(Graphics2D g);
    
}
