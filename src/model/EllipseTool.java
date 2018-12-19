package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class EllipseTool extends RectTool {
    
    private Ellipse2D myActualShape;
    private MyShape myShapeWrapper;
    //private Point myTLCorner;
    
    public EllipseTool(Color theColor, int theThickness) {
        super(theColor, theThickness);
        myActualShape = new Ellipse2D.Double();
        myShapeWrapper = new MyShape();
    }

    /*public EllipseTool(Point theStart, Point theEnd, Color theColor, int theThickness,
                       int theOrder) {
        super(theStart, theEnd, theColor, theThickness, theOrder);
        myShape = new Ellipse2D.Double(getTopLeftPoint().getX(), getTopLeftPoint().getY(),
                                       getWidth(), getHeight());
    }*/
    
    @Override
    public MyShape getShape() {
        Ellipse2D clone = new Ellipse2D.Double(getTopLeftPoint().getX(), getTopLeftPoint().getY(),
                                       getWidth(), getHeight());
        return new MyShape(clone, getColor(), getThickness(), getOrder());
    }
    
    private void setEllipse(Point theStart, Point theEnd) {
        myActualShape.setFrame(getTopLeftPoint().getX(), getTopLeftPoint().getY(),
                               getWidth(), getHeight());
    }
    
    @Override
    public void redrawShape(final Point theStart, final Point theEnd, Color theColor, int theThickness) {
        super.setTopLeftCorner();
        Point tl = getTopLeftPoint();
        Point br = new Point((int) tl.getX() + getWidth(), (int) tl.getY() - getHeight());
        setEllipse(tl, br);
        myShapeWrapper.setShape(myActualShape, getColor(), getThickness(), getOrder());
    }

}
