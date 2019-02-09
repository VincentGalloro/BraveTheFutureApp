
package com.ui;

import java.awt.geom.AffineTransform;

public interface IContainer extends IElement{
    
    public IContainer setElement(IElement e);
    public IElement getElement();
    
    public void applyTrasform(AffineTransform at);
}
