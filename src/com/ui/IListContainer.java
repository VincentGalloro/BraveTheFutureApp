
package com.ui;

import java.awt.geom.AffineTransform;
import java.util.Iterator;

public interface IListContainer extends IElement{
    
    public int getElementCount();
    public IListContainer addElement(IElement e);
    public Iterator<IElement> getElements();
    
    public void applyTrasform(AffineTransform at, int index);
    
}
