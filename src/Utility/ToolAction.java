package Utility;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import gui.DrawingPanel;
import model.Tool;

public class ToolAction extends AbstractAction {
    
    /**
     * The serialization ID.
     */
    private static final long serialVersionUID = -3398064030496816122L;
    
    private DrawingPanel myPanel;
    private Tool myTool;
    private String myName;

    /*public ToolAction() {
        super();
    }

    public ToolAction(String theName) {
        super(theName);
    }*/

    public ToolAction(String theName, Icon theIcon, Tool theTool, DrawingPanel thePanel) {
        super(theName, theIcon);
        myName = theName;
        myTool = theTool;
        myPanel = thePanel;
        
        putValue(Action.SMALL_ICON, theIcon);
        
        final ImageIcon icon = (ImageIcon) theIcon;
        final Image largeImage =
            icon.getImage().getScaledInstance(15, -1, java.awt.Image.SCALE_SMOOTH);
        final ImageIcon largeIcon = new ImageIcon(largeImage);
        putValue(Action.LARGE_ICON_KEY, largeIcon);
        putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
        putValue(Action.SELECTED_KEY, true);
    }

    @Override
    public void actionPerformed(ActionEvent theEvent) {
        myPanel.setTool(myTool);
        //System.out.println("Tool set: " + myPanel.myTool.toString());
    }
    
    public String getName() {
        return myName;
    }

}
