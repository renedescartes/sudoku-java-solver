package com.kekanath.sudoku.applet;

import static com.kekanath.sudoku.applet.SwingUtil.makeTextBold;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * Creates a standard about dialog with a given author and a purpose
 * @author Kannan
 * @since 1.0
 */
public class AboutDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public AboutDialog() {
		super((Frame)null, "About" + " " + "Sudoku Solver", true);
		JPanel p = new JPanel();

		JTextArea copyRightMessage = new JTextArea("Developed to solve sudokus");
		copyRightMessage.setBorder(new EmptyBorder(5, 10, 5, 10));
		copyRightMessage.setEditable(false);
		copyRightMessage.setBackground(getBackground());
		p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		makeTextBold(copyRightMessage);
		p.add(copyRightMessage);

		JTextArea applicationMessage = new JTextArea("Visit http://code.google.com/p/sudoku-java-solver/");
		applicationMessage.setBorder(new EmptyBorder(5, 10, 5, 10));
		applicationMessage.setEditable(false);
		applicationMessage.setBackground(getBackground());
		makeTextBold(applicationMessage);
		
		p.add(applicationMessage);

		getContentPane().add(p, BorderLayout.CENTER);

		JButton btOK = new JButton("OK");
		ActionListener lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		};
		btOK.addActionListener(lst);
		p = new JPanel();
		p.add(btOK);
		getContentPane().add(p, BorderLayout.SOUTH);

		pack();
		setResizable(false);
	}
}
