package com.kekanath.sudoku.applet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import static com.kekanath.sudoku.applet.SwingUtil.*;
public class MainFrame extends JFrame {

	public static final int SIZE_OF_BOARD = 9;

	public MainFrame() {
		super("Sudoku Java Solver Application");
		JTable table = new SudokuTable(SIZE_OF_BOARD, SIZE_OF_BOARD);
		
		JLabel status = new JLabel("Start entering numbers [only digits between 0 and 9]");
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
		this.setJMenuBar(createMenuBar());
		pack();
		setBounds(50, 50, 600, 600);
		setVisible(true);
		centerComponentOnScreen(this);
	}

	public static JMenuBar createMenuBar() {
        // Add the menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(menuBar.getFont().deriveFont(16f));
        JMenu fileMenu = new JMenu("File");
        makeFontSize(fileMenu, 16f);
        final AboutDialog aboutDialog = new AboutDialog();
        
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                centerComponentOnScreen(aboutDialog);
				aboutDialog.setVisible(true);
            }
        });
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        makeFontSize(aboutMenuItem, 16f);
        fileMenu.add(aboutMenuItem);
        
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        quitMenuItem.setMnemonic(KeyEvent.VK_Q);
        makeFontSize(quitMenuItem, 16f);
        fileMenu.add(quitMenuItem);
        
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);
		return menuBar;
	}
}
