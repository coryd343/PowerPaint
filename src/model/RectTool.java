package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class RectTool extends AbstractTool {
    
    private MyShape myShapeWrapper;
    private Rectangle2D myActualShape;
    private Point myTLPoint;
    
    public RectTool(Color theColor, int theThickness) {
        super(theColor, theThickness);
        myActualShape = new Rectangle2D.Double();
        myShapeWrapper = new MyShape();
        setTopLeftCorner();
    }
    
    /*public RectTool(Point theStart, Point theEnd, Color theColor,
                    int theThickness, int theOrder) {
        super(theStart, theEnd, theColor, theThickness, theOrder);
        myHeight = Math.abs((int) getStartPoint().getY() - (int) getNextPoint().getY());
        myWidth = Math.abs((int) getStartPoint().getX() - (int) getNextPoint().getX());
        setTopLeftCorner();
        myActualShape = new Rectangle2D.Double(myTLPoint.getX(), myTLPoint.getY(),
                                         myWidth, myHeight);
        
    }*/
    
    public int getHeight() {
        return Math.abs((int) getStartPoint().getY() - (int) getNextPoint().getY());
    }
    
    public int getWidth() {
        return Math.abs((int) getStartPoint().getX() - (int) getNextPoint().getX());
    }
    
    public Point getTopLeftPoint() {
        return (Point) myTLPoint.clone();
    }
    
    protected void setTopLeftCorner() {
        if (getStartPoint().getX() > getNextPoint().getX()) {
            if (getStartPoint().getY() > getNextPoint().getY()) {
                myTLPoint = new Point((int) getNextPoint().getX(),
                                      (int) getNextPoint().getY());
                
            } else {
                myTLPoint = new Point((int) getNextPoint().getX(),
                                      (int) getStartPoint().getY());
                
            }
        } else {
            if (getStartPoint().getY() > getNextPoint().getY()) {
                myTLPoint = new Point((int) getStartPoint().getX(),
                                      (int) getNextPoint().getY());
                
            } else {                
                myTLPoint = new Point((int) getStartPoint().getX(),
                                       (int) getStartPoint().getY());
                
            }
        }
    }
    
    private void setRect() {
        if (mySquareLock) {
            if (getWidth() < getHeight()) {
                myActualShape.setRect(myTLPoint.getX(), myTLPoint.getY(), getWidth(), getWidth());
            } else {
                myActualShape.setRect(myTLPoint.getX(), myTLPoint.getY(), getHeight(), getHeight());
            }
        } else {
            myActualShape.setRect(myTLPoint.getX(), myTLPoint.getY(), getWidth(), getHeight());
        }
    }

    @Override
    public MyShape getShape() {
        Rectangle2D clone = new Rectangle2D.Double(myTLPoint.getX(), myTLPoint.getY(), getWidth(), getHeight());
        
        return new MyShape(clone, getColor(), getThickness(), getOrder());
    }

    @Override
    public void redrawShape(final Point theStart, final Point theEnd,
                            final Color theColor, final int theThickness) {
        setTopLeftCorner();
        setRect();
        myShapeWrapper.setShape(myActualShape, theColor, theThickness, getOrder());
    }
}
