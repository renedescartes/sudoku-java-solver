package com.kekanath.sudoku.applet;

import java.awt.Color;

import javax.swing.*;

public class SudokuTable extends JTable{

	public SudokuTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		setDefaultRenderer(Object.class, new ColorSwitchRenderer());
		JTextField editField = new PositiveIntegerField(1);
		editField.setHorizontalAlignment(SwingConstants.CENTER);
		editField.setFont(editField.getFont().deriveFont(16f));
		setDefaultEditor(Object.class, new DefaultCellEditor(editField));
		putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		setRowSelectionAllowed(false);
		setSelectionBackground(Color.BLUE);
		setRowHeight(50);
		setFont(getFont().deriveFont(16f));
	}

}
