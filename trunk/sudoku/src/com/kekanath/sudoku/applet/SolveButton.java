package com.kekanath.sudoku.applet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.kekanath.sudoku.core.board.BoardManager;
import com.kekanath.sudoku.core.exception.SkuException;

public class SolveButton extends JButton implements ActionListener {

	JTable table;
	
	JLabel status;

	public SolveButton(String text, JTable table, JLabel status) {
		super(text);
		this.table = table;
		this.status = status;
		addActionListener(this);
		setFont(getFont().deriveFont(16f));
	}

	public void actionPerformed(ActionEvent e) {
		try {
			int[][] question = getData();
			//printArray(question);
			long time1 = System.currentTimeMillis();
			BoardManager manager = BoardManager.getInstance(question);
			manager.fillOpenSlotsinBoard();
			long time2 = System.currentTimeMillis();
			String[][] boardArray = manager.getBoardArray();
			setData(boardArray);
			table.repaint();
			status.setText(" Solved in [" + (time2 - time1) + "] ms");
		} catch (SkuException ex) {
			status.setText(ex.getMessage()); 
		} 
		
		//printArray(getData());
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

	int[][] getData() {
		TableModel model = table.getModel();
		int rows = model.getRowCount();
		int columns = model.getColumnCount();
		int[][] question = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (model.getValueAt(i, j) == null || model.getValueAt(i, j).toString().equals("")) {
					question[i][j] = -1;
				} else {
					try {
						question[i][j] = Integer.parseInt(model.getValueAt(i, j)
							.toString());
					} catch (NumberFormatException ex) {
						throw new SkuException("Please enter a number in the cell [" + (j+1) + ", " + (i+1) + "]");
					}
				}
			}
		}
		return question;
	}

	void printArray(int[][] question) {
		int rows = question.length, columns = question[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.println("[" + i + ", " + j + "] -> "
						+ question[i][j]);
			}
		}
	}

}

