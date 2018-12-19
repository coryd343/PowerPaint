package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;

public interface Tool {
    
    void setStartPoint(Point theStartPoint);
    
    void setNextPoint(Point theNextPoint);
    
    void setColor(Color theColor);
    
    void setThickness(int theThickness);
    
    void setSquareLock();
    
    Point getStartPoint();
    
    Point getNextPoint();
    
    MyShape getShape();
    
    Color getColor();
    
    int getThickness();
    
    @Override
    String toString();
}
