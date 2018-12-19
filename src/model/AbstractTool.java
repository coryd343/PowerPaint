package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;

public abstract class AbstractTool implements Tool {
    
    private Point myStart;
    private Point myEnd;
    private int myOrder;
    private Color myColor;
    private int myThickness;
    protected boolean mySquareLock;
    
    protected AbstractTool(Color theColor, int theThickness) {
        myStart = new Point(-1, -1);
        myEnd = new Point(-1, -1);
        myOrder = 0;
        myColor = theColor;
        myThickness = theThickness;
        mySquareLock = false;
    }
    
    protected AbstractTool(Point theStart, Point theEnd, Color theColor,
                           int theThickness, int theOrder) {
        Point startClone = (Point) theStart.clone();
        Point endClone = (Point) theEnd.clone();
        
        myStart = startClone;
        myEnd = endClone;
        myColor = theColor;
        myThickness = theThickness;
        myOrder = theOrder;
    }

    public void setStartPoint(Point theStartPoint) {
        myStart = theStartPoint;
        redrawShape(myStart, myStart, myColor, myThickness);
        //System.out.println("Start point set: " + theStartPoint.getX() + ", " + theStartPoint.getY());
    }

    public void setNextPoint(final Point theNextPoint) {
        myEnd = theNextPoint;
        redrawShape(myStart, myEnd, myColor, myThickness);
        //System.out.println("End point set: " + theNextPoint.getX() + ", " + theNextPoint.getY());
    }
    
    public void setColor(Color theColor) {
        myColor = theColor;
        redrawShape(myStart, myStart, myColor, myThickness);
    }
    
    public void setThickness(int theThickness) {
        myThickness = theThickness;
        redrawShape(myStart, myStart, myColor, myThickness);
    }
    
    public Point getStartPoint() {
        return (Point) myStart.clone();
    }
    
    public Point getNextPoint() {
        return (Point) myEnd.clone();
    }
    
    public abstract MyShape getShape();
    
    public Color getColor() {
        return myColor;
    }
    
    public int getThickness() {
        return myThickness;
    }
    
    public int getOrder() {
        return myOrder;
    }
    
    public abstract void redrawShape(Point theStart, Point theEnd,
                                     Color theColor, int theThickness);
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + ", Start: " + myStart.toString() + ", End: "
                        + myEnd.toString() + ", Color: " + myColor.toString() + ", Thickness: "
                        + myThickness + ", Order: " + myOrder;        
    }
    
    public void setSquareLock() {
        mySquareLock = !mySquareLock;
    }
}
