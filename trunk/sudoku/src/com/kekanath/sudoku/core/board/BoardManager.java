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

package com.kekanath.sudoku.core.board;

import com.kekanath.sudoku.core.exception.SkuException;
import com.kekanath.sudoku.core.state.ContentPool;
import com.kekanath.sudoku.core.state.Entry;
import com.kekanath.sudoku.core.state.EntryContent;
import com.kekanath.sudoku.core.state.Position;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author <a href="mailto:kannan.ekanath@tavant.com">Kannan Ekanath</a>.
 * @version $Id$
 */
public class BoardManager {

    int sizeOfBoard;

    Board board;

    ContentPool contentPool;

    BoardStateUtil boardStateUtil;

    public static BoardManager getInstance(int[][] entries) {
        return new BoardManager(new LinkedHashMap(), entries);
    }

    BoardManager(Map map, int[][] entries) {
        sizeOfBoard = entries.length;
        validateBoard(entries);
        board = new Board(map);
        contentPool = new ContentPool(sizeOfBoard);
        boardStateUtil = new BoardStateUtil(sizeOfBoard);
        initialise(entries);
    }

    void validateBoard(int[][] entryContents) {
        int length = entryContents.length;
        for (int i = 0; i < length; i++) {
            if (length == 0)
                throw new SkuException("Attempting to solve a zero length matrix");
            int root = (int) Math.sqrt(length);
            if (length != root * root)
                throw new SkuException("The length/number of elements needs to be a perfect square");
            if (entryContents[i].length != length)
                throw new SkuException("The given matrix might not be a square matrix");
            for (int j = 0; j < length; j++) {
                if (entryContents[i][j] > length)
                    throw new SkuException("In a matrix of [" + length + " X " + length + "] sudoku game" +
                            " dictates that all elements be less than [" + length + "]");
            }
        }
    }

    void initialise(int[][] entryContents) {
        for (int i = 0; i < sizeOfBoard; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                Position pos = new Position(i, j);
                boolean elementPresent = (entryContents[i][j] >= 1 && entryContents[i][j] <= 9);
                Entry entry = new Entry(contentPool.getContent(entryContents[i][j]), elementPresent, elementPresent);
                placeEntry(pos, entry);
            }
        }
        initialiseOptionsListForEntries();
    }

    void initialiseOptionsListForEntries() {
        Map map = board.getEntries();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Position pos = (Position) it.next();
            Entry entry = (Entry) map.get(pos);
            if (!entry.isFilled()) {
                List subOptions = boardStateUtil.provideAvailableOptions(
                        contentPool.getContentPoolCopy(), pos);
                entry.getOptions().addAll(subOptions);
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public void fillOpenSlotsinBoard() {
        if (!fillInternal())
            throw new SkuException("The given problem cannot be solved");
    }

    public void printBoard(PrintStream stream) {
        PrintWriter writer = new PrintWriter(stream);
        writer.write("\n");
        Map entries = board.getEntries();
        Iterator it = entries.keySet().iterator();
        int position = 0;
        while (it.hasNext()) {
            position++;
            Entry entry = (Entry) entries.get(it.next());
            writer.write(entry.isFilled() ? entry.getContent().toString() : "*");
            writer.write(" ");
            writer.write((position % sizeOfBoard == 0) ? "\n" : "");
        }
        writer.flush();
    }

	public void printBoardLine(PrintStream stream) {
        PrintWriter writer = new PrintWriter(stream);
        Map entries = board.getEntries();
        Iterator it = entries.keySet().iterator();
        int position = 0;
        while (it.hasNext()) {
            position++;
            Entry entry = (Entry) entries.get(it.next());
            writer.write(entry.isFilled() ? entry.getContent().toString() : "*");
            writer.write(",");
        }
        writer.flush();
    }
	
    Position getFirstUnfilledPosition() {
        Map entries = board.getEntries();
        Iterator it = entries.keySet().iterator();
        while (it.hasNext()) {
            Position pos = (Position) it.next();
            Entry entry = (Entry) entries.get(pos);
            if (!entry.isFilled()) {
                return pos;
            }
        }
        return Position.ALL_FILLED;
    }


    boolean fillInternal() {
        Position pos = getFirstUnfilledPosition();
        if (pos == Position.ALL_FILLED)
            return true;

        Entry oldEntry = (Entry) board.getEntries().get(pos);
        Set options = oldEntry.getOptions();
        if (options == null || options.isEmpty())
            return false;
        Iterator it = options.iterator();
        while (it.hasNext()) {
            Entry newEntry = new Entry((EntryContent) it.next(), false, true);
            if (doesEntryFit(pos, newEntry)) {
                placeEntry(pos, newEntry);
                /**
                 * When first solutions is obtained cut the crap and come out
                 */
                if (fillInternal())
                    return true;
                unplaceEntry(pos, oldEntry, newEntry);
            }
        }
        return false;
    }

    boolean doesEntryFit(Position position, Entry entry) {
        return boardStateUtil.doesEntryFitHorizontally(position, entry) &&
                boardStateUtil.doesEntryFitVertically(position, entry) &&
                boardStateUtil.doesEntryFitSubPool(position, entry);
    }

    void placeEntry(Position position, Entry entry) {
        board.getEntries().put(position, entry);
        if (entry.isFilled()) {
            boardStateUtil.updateHorizontalEntry(position, entry);
            boardStateUtil.updateVerticalEntry(position, entry);
            boardStateUtil.updateSubPoolEntry(position, entry);
        }
    }

    void unplaceEntry(Position position, Entry oldEntry, Entry newEntry) {
        board.getEntries().put(position, oldEntry);
        boardStateUtil.removeHorizontalEntry(position, newEntry);
        boardStateUtil.removeVerticalEntry(position, newEntry);
        boardStateUtil.removeSubPoolEntry(position, newEntry);
    }

    public String[][] getBoardArray() {
        String[][] result = new String[sizeOfBoard][sizeOfBoard];
        Map entries = board.getEntries();
        Iterator it = entries.keySet().iterator();
        while(it.hasNext()) {
            Position position = (Position) it.next();
            Entry entry = (Entry) entries.get(position);
            result[position.getX()][position.getY()] = entry.getContent().toString();
        }
        return result;
    }

}
