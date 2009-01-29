package com.kekanath.sudoku.applet;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.kekanath.sudoku.core.state.Position;

public class ColorSwitchRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO Auto-generated method stub
		Position pos = new Position(row, column);
		int index = pos.getSubPoolIndex(MainApplet.SIZE_OF_BOARD);
		setBackground((index % 2 == 0) ? Color.LIGHT_GRAY : Color.WHITE);
		return super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);
	}

}
