package com.kekanath.sudoku.applet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class ClearButton extends JButton implements ActionListener {

	JTable table;

	JTextField status;

	public ClearButton(String text, JTable table, JTextField status) {
		super(text);
		this.table = table;
		this.status = status;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		TableModel model = table.getModel();
		int rows = model.getRowCount();
		int columns = model.getColumnCount();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				model.setValueAt("", i, j);
			}
		}
		table.setModel(model);
		table.repaint();
		status.setText("Puzzle Cleared");
	}

}
