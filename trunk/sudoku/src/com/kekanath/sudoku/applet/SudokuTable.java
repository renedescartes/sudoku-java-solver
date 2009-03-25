package com.kekanath.sudoku.applet;

import java.awt.Color;

import javax.swing.JTable;

public class SudokuTable extends JTable{

	public SudokuTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		setDefaultRenderer(Object.class, new ColorSwitchRenderer());
		putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		setRowSelectionAllowed(false);
		setSelectionBackground(Color.BLUE);
	}

}
