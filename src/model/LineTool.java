package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class LineTool extends AbstractTool {
    
    private Line2D myLine;
    private MyShape myShapeWrapper;
    
    public LineTool(Color theColor, int theThickness) {
        super(theColor, theThickness);
        myLine = new Line2D.Double(getStartPoint(), getNextPoint());
        myShapeWrapper = new MyShape();
    }
    
    /*public LineTool(Point theStart, Point theEnd, Color theColor,
                    int theThickness, int theOrder) {
        super(theStart, theEnd, theColor, theThickness, theOrder);
        myLine = new Line2D.Double(theStart, theEnd);
        myShapeWrapper = new MyShape(myLine, theColor, theThickness, theOrder);
    }*/
    
    @Override
    public MyShape getShape() {
        /*final Line2D aLine = (Line2D) myLine.clone();
        aLine.setLine(getStartPoint(), getNextPoint());
        return new MyShape(aLine, getColor(), getThickness(), getOrder());*/
        MyShape clone = new MyShape(myShapeWrapper.getShape(),
                                    myShapeWrapper.getColor(),
                                    myShapeWrapper.getThickness(),
                                    myShapeWrapper.getOrder());
        return clone;
    }

    @Override
    public void redrawShape(final Point theStart, final Point theEnd, final Color theColor, final int theThickness) {
        final Line2D aLine = (Line2D) myLine.clone();
        aLine.setLine(theStart, theEnd);
        myShapeWrapper.setShape(aLine, theColor, theThickness, getOrder());
        
    }

}
