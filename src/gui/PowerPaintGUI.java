/*
 * TCSS 305 C
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Utility.ToolAction;
import model.EllipseTool;
import model.LineTool;
import model.PencilTool;
import model.RectTool;

/**
 * Sets up the window with the menubar, toolbar and drawingpanel.
 * @author coryd343
 * @version 1.0
 */
public class PowerPaintGUI {
    
    /**
     * The main window.
     */
    private static JFrame myMain;
    
    /**
     * All drawing tools are used here.
     */
    private static DrawingPanel myDPanel;
    
    /**
     * The button group used for the tool menu items.
     */
    private static ButtonGroup myMenuGroup;
    
    /**
     * The button group used for the toolbar items.
     */
    private static ButtonGroup myToolbarGroup;
    
    /**
     * The minimum value of the slider.
     */
    private static final int SLIDE_MIN = 0;
    
    /**
     * The maximum value of the slider.
     */
    private static final int SLIDE_MAX = 20;
    
    /**
     * The default value of the slider.
     */
    private static final int SLIDE_START = 5;
    
    /**
     * The major tick-mark indicator of the slider.
     */
    private static final int SLIDE_MAJOR = 5;
    
    /**
     * The minor tick-mark indicator of the slider.
     */
    private static final int SLIDE_MINOR = 1;
    
    /**
     * The slider used to determine the stroke thickness.
     */
    private static JSlider mySlider;
    
    /**
     * The RGB value of the official Husky purple. Used for the default color selector value.
     */
    private static final Color UW_PURPLE = new Color(57, 0, 111, 255);
    
    /**
     * List of each action used to select the tool.
     */
    private static ArrayList<ToolAction> myToolActions;
    
    /**
     * Reference to the icon used for the main window.
     */
    private static ImageIcon myLogo;
    
    /**
     * Sets up the components.
     */
    public void start() {
        myMain = new JFrame("PowerPaint");
        myMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMain.setLocationByPlatform(true);
        myMain.setVisible(true);
        myLogo = new ImageIcon("./images/splatoon-roller.jpg");
        myMain.setIconImage(myLogo.getImage());
        myMenuGroup = new ButtonGroup();
        myToolbarGroup = new ButtonGroup();
        myToolActions = new ArrayList<ToolAction>();
        myDPanel = new DrawingPanel();
        setupActions();
        myMain.add(myDPanel, BorderLayout.CENTER);
        myMain.setJMenuBar(createMenuBar());
        final JToolBar tools = new JToolBar();
        addTools(tools);
        myMain.add(tools, BorderLayout.SOUTH);
        myMain.pack();
    }
    
    /**
     * Creates a fully loaded menu bar.
     * @return the menu bar.
     */
    private static JMenuBar createMenuBar() {
        final JMenuBar menus = new JMenuBar();
        
        final JMenu file = new JMenu("File");
        final JMenuItem undo = createMenuItem("Undo all changes");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myDPanel.getShapes().clear();
                myDPanel.repaint();
            }
        });
        file.add(undo);
        file.addSeparator();
        final JMenuItem exit = createMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myMain.dispose();
            }
        });
        exit.setMnemonic(KeyEvent.VK_X);
        file.add(exit);
        
        file.setMnemonic(KeyEvent.VK_F);
        menus.add(file);
        
        final JMenu options = new JMenu("Options");
        final JCheckBoxMenuItem squareOnly = createCBMenuItem("Square/Circle only");
        squareOnly.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myDPanel.myTool.setSquareLock();
            }
            
        });
        options.add(squareOnly);
        options.addSeparator();
        final JMenu thickness = new JMenu("Thickness");
        mySlider = new JSlider(JSlider.HORIZONTAL, SLIDE_MIN,
                                                SLIDE_MAX, SLIDE_START);
        setSlider(mySlider);
        mySlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                myDPanel.myTool.setThickness(mySlider.getValue());
            }
            
        });
        thickness.add(mySlider);
        options.add(thickness);
        options.addSeparator();
        final JMenuItem color = createMenuItem("Color...");
        color.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //Ideally, this should set the DrawingPanel's color field.
                myDPanel.myTool.setColor(JColorChooser.showDialog(color,
                                  "Whoa, the colors!", UW_PURPLE));
            }
            
        });
        options.add(color);
        options.setMnemonic(KeyEvent.VK_O);
        menus.add(options);
        
        final JMenu tools = new JMenu("Tools");
        addTools(tools);
        tools.setMnemonic(KeyEvent.VK_T);
        menus.add(tools);
        
        final JMenu help = new JMenu("Help");
        final JMenuItem about = createMenuItem("About...");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(myMain, 
                                              "TCSS 305 PowerPaint\n" + "Winter 2016\n"
                                              + "Cory Davis"
                                              , "About", JOptionPane.INFORMATION_MESSAGE,
                                              myLogo);
            }
        });
        help.add(about);
        help.setMnemonic(KeyEvent.VK_H);
        menus.add(help);
        
        return menus;
    }
    
    /**
     * Sets the default values of the slider.
     * @param theSlider the Thickness setting slider
     */
    private static void setSlider(final JSlider theSlider) {
        theSlider.setMajorTickSpacing(SLIDE_MAJOR);
        theSlider.setMinorTickSpacing(SLIDE_MINOR);
        theSlider.setPaintTicks(true);
        theSlider.setPaintLabels(true);
    }
    
    /**
     * Used to create a menu item and set its mnemonic.
     * @param theName the string to label the item.
     * @return the menu item.
     */
    private static JMenuItem createMenuItem(final String theName) {
        final JMenuItem item = new JMenuItem(theName);
        setMnemonic(item, theName);
        return item;
    }
    
    /**
     * Used to create a menu checkbox item and set its mnemonic.
     * @param theName the string to label the item.
     * @return the menu checkbox item.
     */
    private static JCheckBoxMenuItem createCBMenuItem(final String theName) {
        final JCheckBoxMenuItem item = new JCheckBoxMenuItem(theName);
        setMnemonic(item, theName);
        return item;
    }
    
    /**
     * Used to create a JRadioButtonMenuItem item and set its mnemonic.
     * @param theTool the ToolAction which this button will activate.
     * @param theGroup the ButtonGroup to add the buttons to.
     * @return the JRadioButtonMenuItem.
     */
    private static JRadioButtonMenuItem createRadioMenuItem(final ToolAction theTool,
                                                            final ButtonGroup theGroup) {
        final JRadioButtonMenuItem item = new JRadioButtonMenuItem(theTool);
        //add actionListener functionality for part B.
        setMnemonic(item, theTool.getName());
        theGroup.add(item);
        return item;
    }
    
    /**
     * Creates a button to add to the toolbar.
     * @param theTool the toolAction which this button will activate.
     * @param theGroup the button group each button will be added to.
     * @return the toolbar toggle button.
     */
    private static JToggleButton createToolbarItem
        (final ToolAction theTool, final ButtonGroup theGroup) {
        //ImageIcon icon = getClass().getResource("/images/pencil.gif");
        final JToggleButton item = new JToggleButton(theTool);
        theGroup.add(item);
        //add actionListener functionality for part B.
        setMnemonic(item, theTool.getName());
        return item;
    }
    
    /**
     * Iterates the list of actions and populates the menu and toolbar.
     * @param theComponent the button to activate the tool.
     */
    private static void addTools(final JComponent theComponent) {        
        if (theComponent instanceof JMenu) {
            for (final ToolAction tool : myToolActions) {
                theComponent.add(createRadioMenuItem(tool, myMenuGroup));
            }
        } else { //JComponent is a JToolBar
            for (final ToolAction tool : myToolActions) {
                theComponent.add(createToolbarItem(tool, myToolbarGroup));
            }
        }
    }
    
    private static void setMnemonic(final AbstractButton theComponent, String theName) {
        final char firstLetter = theName.charAt(0);
        theComponent.setMnemonic(firstLetter);
    }
    
    private void setupActions() {
        myToolActions.add(new ToolAction("Pencil", new ImageIcon("./images/pencil_bw.gif"),
                                         new PencilTool(myDPanel.getColor(), myDPanel.getThickness()), myDPanel));
        myToolActions.add(new ToolAction("Line", new ImageIcon("./images/line_bw.gif"),
                                         new LineTool(myDPanel.getColor(), myDPanel.getThickness()), myDPanel)); 
        myToolActions.add(new ToolAction("Rectangle", new ImageIcon("./images/rectangle_bw.gif"),
                                         new RectTool(myDPanel.getColor(), myDPanel.getThickness()), myDPanel));
        myToolActions.add(new ToolAction("Ellipse", new ImageIcon("./images/ellipse_bw.gif"),
                                         new EllipseTool(myDPanel.getColor(), myDPanel.getThickness()), myDPanel));
    }
}
