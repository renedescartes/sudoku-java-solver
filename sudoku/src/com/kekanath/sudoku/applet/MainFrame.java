package com.kekanath.sudoku.applet;

import java.awt.*;

import javax.swing.*;


public class MainFrame extends JFrame {

	public static final int SIZE_OF_BOARD = 9;

	public MainFrame() {
		super("Sudoku Java Solver Application");
		JTable table = new SudokuTable(SIZE_OF_BOARD, SIZE_OF_BOARD);
		table.setRowHeight(50);
		table.setFont(table.getFont().deriveFont(16f));
		
		JLabel status = new JLabel("Start entering numbers");
		status.setFont(status.getFont().deriveFont(16f));
		status.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1, 3));
		southPanel.add(new SolveButton("Solve", table, status));
		southPanel.add(new ClearButton("Clear", table, status));
		southPanel.add(new LoadButton("Load Sample", table, status));
		
		this.setLayout(new BorderLayout());
		this.add(table, BorderLayout.CENTER);
		this.add(status, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);

		pack();
		setBounds(50, 50, 600, 600);
		setVisible(true);
		centerComponentOnScreen(this);
	}

	public static void centerComponentOnScreen(Component component) {
        // Center on screen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((d.getWidth() - component.getWidth()) / 2);
        int y = (int) ((d.getHeight() - component.getHeight()) / 2);
        component.setLocation(x, y);
    }
}
