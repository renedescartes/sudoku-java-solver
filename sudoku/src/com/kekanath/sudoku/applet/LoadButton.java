package com.kekanath.sudoku.applet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class LoadButton extends JButton implements ActionListener {

	JTable table;

	JTextField status;

	String[][] sampleQuestion = new String[][] {
			{"", "", "", "", "2", "6", "", "", "9"},
			{"", "9" , "", "", "4", "", "", "", "6"},
			{"1", "" , "5", "", "7", "", "", "", ""},
			{"", "" , "9", "", "", "5", "", "", ""},
			{"5", "3", "", "", "", "", "", "7", "8"},
			{"", "" , "", "4", "", "", "2", "", ""},
			{"", "" , "", "", "5", "", "1", "", "3"},
			{"7", "" , "", "", "1", "", "", "4", ""},
			{"9", "", "", "3", "8", "", "", "", ""},
	};
	public LoadButton(String text, JTable table, JTextField status) {
		super(text);
		this.table = table;
		this.status = status;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		setData(sampleQuestion);
		table.repaint();
		status.setText(" Sample Puzzle Loaded");
	}

	void setData(String[][] answer) {
		TableModel model = table.getModel();
		int rows = model.getRowCount();
		int columns = model.getColumnCount();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				model.setValueAt(answer[i][j], i, j);
			}
		}
	}

}
