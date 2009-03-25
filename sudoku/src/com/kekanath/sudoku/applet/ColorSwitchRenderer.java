package com.kekanath.sudoku.applet;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.kekanath.sudoku.core.state.Position;

public class ColorSwitchRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Position pos = new Position(row, column);
		int index = pos.getSubPoolIndex(MainFrame.SIZE_OF_BOARD);
		setBackground((index % 2 == 0) ? Color.LIGHT_GRAY : Color.WHITE);
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		((JLabel)c).setHorizontalAlignment(SwingConstants.CENTER);
		return c;
	}

}
