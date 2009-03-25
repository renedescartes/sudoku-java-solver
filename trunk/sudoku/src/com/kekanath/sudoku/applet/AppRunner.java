package com.kekanath.sudoku.applet;

import javax.swing.UIManager;

public class AppRunner {

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new MainFrame();
	}
}
