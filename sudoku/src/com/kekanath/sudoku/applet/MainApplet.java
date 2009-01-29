package com.kekanath.sudoku.applet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;


public class MainApplet extends JApplet {

	public static final int SIZE_OF_BOARD = 9;

	public void init() {
		setVisible(true);

		JPanel panel = new JPanel();
		JTable table = new SudokuTable(SIZE_OF_BOARD, SIZE_OF_BOARD);
		
		JTextField status = new JTextField("Start entering numbers");
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(3, 1));
		southPanel.add(new SolveButton("Solve", table, status));
		southPanel.add(new ClearButton("Clear", table, status));
		southPanel.add(new LoadButton("Load Sample", table, status));
		
		panel.setLayout(new BorderLayout());
		panel.add(table, BorderLayout.CENTER);
		panel.add(status, BorderLayout.NORTH);
		panel.add(southPanel, BorderLayout.SOUTH);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel);
		setBounds(10, 10, 400, 400);
	}

}
