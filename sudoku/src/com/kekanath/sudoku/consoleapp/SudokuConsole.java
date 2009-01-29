/*
 *   Copyright (c)2000 Tavant Technologies
 *   All Rights Reserved.
 *
 *   This software is furnished under a license and may be used and copied
 *   only  in  accordance  with  the  terms  of such  license and with the
 *   inclusion of the above copyright notice. This software or  any  other
 *   copies thereof may not be provided or otherwise made available to any
 *   other person. No title to and ownership of  the  software  is  hereby
 *   transferred.
 *
 *   The information in this software is subject to change without  notice
 *   and  should  not be  construed as a commitment  by Tavant Technologies.
 *
 *
 *   Author: kannan.ekanath
 *   Date: Aug 3, 2005
 */

package com.kekanath.sudoku.consoleapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.kekanath.sudoku.core.board.BoardManager;

/**
 * @author <a href="mailto:kannan.ekanath@tavant.com">Kannan Ekanath</a>.
 * @version $Id$
 */
public class SudokuConsole {

	public static final int SIZE = 9;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage is -> java -jar sudoku.jar (filename)");
			System.exit(1);
		}
		int[][] question = getEntries(args[0]);
		// int[][] question = {
		// {5, -1, 6, -1, 7, 1, 4, -1, -1},
		// {-1, 2, 9, -1, -1, -1, 7, 1, -1},
		// {9, -1, -1, -1, 4, -1, -1, -1, 5},
		// {-1, -1 , -1, -1, -1, -1, -1, -1, -1},
		// {-1, -1 , -1, 8, -1, 9, -1, -1, -1},
		// {-1, -1 , -1, -1, -1, -1, -1, -1, -1},
		// {3, -1 , -1, -1, 2, -1, -1, -1, 9},
		// {-1, 7 , 3, -1, -1, -1, 1, 8, -1},
		// {-1, -1 , 4, 6, 9, -1, 5, -1, 3},
		//
		//
		// };
		long time1 = System.currentTimeMillis();
		BoardManager manager = BoardManager.getInstance(question);
		manager.printBoard(System.out);
		manager.fillOpenSlotsinBoard();
		manager.printBoard(System.out);
		long time2 = System.currentTimeMillis();
		System.out.println("Solved in [" + (time2 - time1) + "] milliseconds");
		// int[][] evil = {
		// {-1, -1, -1, -1, 2, 6, -1, -1, 9},
		// {-1, 9, -1, -1, 4, -1, -1, -1, 6},
		// {1, -1, 5, -1, 7, -1, -1, -1, -1},
		// {-1, -1, 9, -1, -1, 5, -1, -1, -1},
		// {5, 3, -1, -1, -1, -1, -1, 7, 8},
		// {-1, -1, -1, 4, -1, -1, 2, -1, -1},
		// {-1, -1, -1, -1, 5, -1, 1, -1, 3},
		// {7, -1, -1, -1, 1, -1, -1, 4, -1},
		// {9, -1, -1, 3, 8, -1, -1, -1, -1},
		// };
		//        
		// long time1 = System.currentTimeMillis();
		// BoardManager manager = BoardManager.getInstance(evil);
		// manager.printBoard(System.out);
		// manager.fillOpenSlotsinBoard();
		// manager.printBoard(System.out);
		// long time2 = System.currentTimeMillis();
		// System.out.println("Solution in [" + (time2 - time1) + "]
		// milliseconds");

		// int[][] miniQuestion = {
		// {1, 2,-1,-1},
		// {-1,-1,-1,-1},
		// {2,-1,-1,-1},
		// {-1,-1,-1,3},
		// };
		// manager = BoardManager.getInstance(miniQuestion);
		// manager.printBoard(System.out);
		// manager.fillOpenSlotsinBoard();
		// manager.printBoard(System.out);
	}

	private static int[][] getEntries(String fileName) {
		int[][] entries = new int[SIZE][SIZE];
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(
					new File(fileName)));
			String line = null;
			int lineNumber = 1;
			while ((line = fileReader.readLine()) != null) {
				if (lineNumber > SIZE) {
					System.err.println("Please provide [" + SIZE + "] rows");
					System.exit(1);
				}
				String[] splitLine = line.split(" ");
				if (splitLine.length != SIZE) {
					System.err.println("Please provide [" + SIZE
							+ "] columns in row [" + lineNumber + "]");
					System.exit(1);
				}
				for (int i = 0; i < SIZE; i++) {
					if ("X".equals(splitLine[i])) {
						entries[lineNumber - 1][i] = -1;
					} else {
						entries[lineNumber - 1][i] = Integer
								.parseInt(splitLine[i]);
					}
				}
				lineNumber++;
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found [" + fileName + "]");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Could not read file [" + fileName + "]");
			System.exit(1);
		} catch (NumberFormatException e) {
			System.err
					.println("Please provide only numbers or symbol X if empty");
			System.exit(1);
		}
		return entries;
	}
}
