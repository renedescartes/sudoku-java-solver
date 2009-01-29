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

import com.kekanath.sudoku.core.state.Entry;
import com.kekanath.sudoku.core.state.Position;
import com.kekanath.sudoku.core.exception.SkuException;

import java.util.*;

/**
 * @author <a href="mailto:kannan.ekanath@tavant.com">Kannan Ekanath</a>.
 * @version $Id$
 */
public class BoardStateUtil {
	public int SIZE_OF_BOARD = 9;

	List horizontalEntries, verticalEntries, subPoolEntries;

	BoardStateUtil(int sizeOfBoard) {
		SIZE_OF_BOARD = sizeOfBoard;
		horizontalEntries = new ArrayList(SIZE_OF_BOARD);
		verticalEntries = new ArrayList(SIZE_OF_BOARD);
		subPoolEntries = new ArrayList(SIZE_OF_BOARD);
		for (int i = 0; i < SIZE_OF_BOARD; i++) {
			horizontalEntries.add(new TreeSet());
			verticalEntries.add(new TreeSet());
			subPoolEntries.add(new TreeSet());
		}
	}

	boolean removeEntry(List list, int position, Entry entry) {
		return ((Set) (list.get(position))).remove(entry.getContent());
	}

	boolean checkEntry(List list, int position, Entry entry) {
		return !((Set) (list.get(position))).contains(entry.getContent());
	}

	boolean addEntry(List list, int position, Entry entry) {
		return ((Set) (list.get(position))).add(entry.getContent());
	}

	boolean removeSubPoolEntry(Position position, Entry entry) {
		return removeEntry(subPoolEntries, position
				.getSubPoolIndex(SIZE_OF_BOARD), entry);
	}

	boolean removeVerticalEntry(Position position, Entry entry) {
		return removeEntry(verticalEntries, position.getY(), entry);
	}

	boolean removeHorizontalEntry(Position position, Entry entry) {
		return removeEntry(horizontalEntries, position.getX(), entry);
	}

	boolean doesEntryFitSubPool(Position position, Entry entryContent) {
		return checkEntry(subPoolEntries, position
				.getSubPoolIndex(SIZE_OF_BOARD), entryContent);
	}

	boolean doesEntryFitVertically(Position position, Entry entry) {
		return checkEntry(verticalEntries, position.getY(), entry);
	}

	boolean doesEntryFitHorizontally(Position position, Entry entry) {
		return checkEntry(horizontalEntries, position.getX(), entry);
	}

	void updateSubPoolEntry(Position position, Entry entry) {
		if (!addEntry(subPoolEntries, position.getSubPoolIndex(SIZE_OF_BOARD),
				entry))
			throw new SkuException("The entry [" + entry.getContent()
					+ "] was already present in sub pool number ["
					+ (position.getSubPoolIndex(SIZE_OF_BOARD) + 1) + "]");
	}

	void updateVerticalEntry(Position position, Entry entry) {
		if (!addEntry(verticalEntries, position.getY(), entry))
			throw new SkuException("The entry [" + entry.getContent()
					+ "] was already present in column number ["
					+ (position.getY() + 1) + "]");
	}

	void updateHorizontalEntry(Position position, Entry entry) {
		if (!addEntry(horizontalEntries, position.getX(), entry))

			throw new SkuException("The entry [" + entry.getContent()
					+ "] was already present in row number ["
					+ (position.getX() + 1) + "]");
	}

	List provideAvailableOptions(List allOptions, Position position) {
		allOptions.removeAll((Collection) horizontalEntries
				.get(position.getX()));
		allOptions.removeAll((Collection) verticalEntries.get(position.getY()));
		allOptions.removeAll((Collection) subPoolEntries.get(position
				.getSubPoolIndex(SIZE_OF_BOARD)));
		return allOptions;
	}
}
