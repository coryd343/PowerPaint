package model;

import java.awt.Color;
import java.awt.Shape;

public class MyShape {
    
    private Shape myShape;
    private Color myColor;
    private int myThickness;
    private int myOrder;
    
    public MyShape() {
        
    }

    public MyShape(Shape theShape, Color theColor, int theThickness, int theOrder) {
        myShape = theShape;
        myColor = theColor;
        myThickness = theThickness;
        myOrder = theOrder;
    }
    
    public Shape getShape() {
        return myShape;
    }
    
    public Color getColor() {
        return myColor;
    }
    
    public int getThickness() {
        return myThickness;
    }
    
    public int getOrder() {
        return myOrder;
    }
    
    public void setShape(Shape theShape, Color theColor, int theThickness, int theOrder) {
        myShape = theShape;
        myColor = theColor;
        myThickness = theThickness;
        myOrder = theOrder;
    }

}
