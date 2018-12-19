package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;

public class PencilTool extends AbstractTool {
    
    private Path2D.Double myActualShape;
    private MyShape myShapeWrapper;
    
    public PencilTool(Color theColor, int theThickness) {
        super(theColor, theThickness);
        myActualShape = new Path2D.Double();
        myShapeWrapper = new MyShape();
    }

    @Override
    public MyShape getShape() {
        Path2D.Double clone = (Double) myActualShape.clone();
        return new MyShape(clone, getColor(), getThickness(), getOrder());
    }

    @Override
    public void redrawShape(Point theStart, Point theEnd, Color theColor, int theThickness) {        
        if (myActualShape.getCurrentPoint() == null) {
            myActualShape.moveTo(theStart.getX(), theStart.getY());
        } else if (getStartPoint() == null){
            myActualShape.reset();
            myActualShape.lineTo(theEnd.getX(), theEnd.getY());
        } else {
            myActualShape.lineTo(theEnd.getX(), theEnd.getY());
        }
        
        myShapeWrapper.setShape(myActualShape, getColor(), getThickness(), getOrder());
        if(getStartPoint() == null) {
            myActualShape.reset();
            System.out.println("path reset");
        }
    }

}
