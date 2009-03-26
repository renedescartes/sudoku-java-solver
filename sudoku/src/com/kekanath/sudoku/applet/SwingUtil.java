package com.kekanath.sudoku.applet;

import java.awt.*;

import javax.swing.JComponent;
import javax.swing.JTextArea;

public class SwingUtil {
	public static void centerComponentOnScreen(Component component) {
        // Center on screen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((d.getWidth() - component.getWidth()) / 2);
        int y = (int) ((d.getHeight() - component.getHeight()) / 2);
        component.setLocation(x, y);
    }
	
	public static void makeTextBold(JTextArea textArea) {
    	//keep old settings just change the face to be bold
    	Font f = new Font(textArea.getFont().getName(), Font.BOLD, textArea.getFont().getSize());
    	textArea.setFont(f);
    }
	
	public static void makeFontSize(JComponent c, float f) {
		c.setFont(c.getFont().deriveFont(f));
	}
}
