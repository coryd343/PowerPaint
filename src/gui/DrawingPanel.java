package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import model.EllipseTool;
import model.LineTool;
import model.MyShape;
import model.PencilTool;
import model.RectTool;
import model.Tool;

public class DrawingPanel extends JPanel {
    
    private final Dimension DEFAULT_SIZE = new Dimension(500, 400);
    private Point myStart;
    private Point myEnd;
    private MIA myMIA;
    public Tool myTool;
    private ArrayList<MyShape> myShapes;
    //public ArrayList<Tool> myShapes;
    private int myThickness;
    private final int DEFAULT_THICKNESS = 5;
    private Color myColor;
    private final Color UW_PURPLE = new Color(57, 0, 111, 255);
    
    public DrawingPanel() {
        super();
        setPreferredSize(DEFAULT_SIZE);
        setBackground(Color.WHITE);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        myMIA  = new MIA();
        addMouseMotionListener(myMIA);
        addMouseListener(myMIA);
        myShapes = new ArrayList<MyShape>();
        setColor(UW_PURPLE);
        setThickness(DEFAULT_THICKNESS);
        myTool = new PencilTool(getColor(), getThickness());
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
        
        if ((int) myTool.getNextPoint().getX() != -1) {
            if (!myShapes.isEmpty()) {
                
                for (int i = 0; i < myShapes.size(); i++) {
                    g.setPaint(myShapes.get(i).getColor());
                    g.setStroke(new BasicStroke(myShapes.get(i).getThickness()));
                    g.draw(myShapes.get(i).getShape());
                }
            }
            g.setPaint(myTool.getColor());
            g.setStroke(new BasicStroke(myTool.getThickness()));
            myTool.getShape().toString();
            g.draw(myTool.getShape().getShape());
        }
    }

    public final void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    public final void setThickness(final int theThickness) {
        myThickness = theThickness;
    }
    
    public final void setTool(final Tool theTool) {
        myTool = theTool;
    }
    
    public final ArrayList<MyShape> getShapes() {
        return myShapes;
    }
    
    public final Color getColor() {
        return myColor;
    }
    
    public final int getThickness() {
        return myThickness;
    }
    
    class MIA extends MouseInputAdapter {
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myTool.setStartPoint(theEvent.getPoint());
            //myEnd = null;
        }
        
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            //fixes the end point for the line
            //myEnd = theEvent.getPoint();
            myTool.setNextPoint(theEvent.getPoint());
            myShapes.add(myTool.getShape());
        }
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            //redraws the line as the mouse is moved
            //myEnd = theEvent.getPoint();
            myTool.setNextPoint(theEvent.getPoint());
            repaint();
        }
    }
}
