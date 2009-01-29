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

package com.kekanath.sudoku.core.state;

/**
 * @author <a href="mailto:kannan.ekanath@tavant.com">Kannan Ekanath</a>.
 * @version $Id$
 */

public class Position {
	int x;

	int y;

	public static Position ALL_FILLED = new Position(-1, -1);

	// int[][] subPoolLocation = new int[][] {
	// {0,1,1,1,1,2,3,3,3},
	// {0,0,1,1,1,2,2,3,3},
	// {0,0,0,1,1,2,3,3,3},
	// {0,4,0,0,2,2,2,7,3},
	// {4,4,4,5,5,2,2,7,7},
	// {4,4,4,5,5,6,6,6,7},
	// {4,4,8,5,5,6,6,6,7},
	// {8,8,8,8,5,5,6,7,7},
	// {8,8,8,8,5,6,6,7,7},
	// };
	int[][] subPoolLocation = new int[][] { { 0, 1, 1, 1, 1, 2, 3, 3, 3 },
			{ 0, 0, 1, 1, 1, 2, 2, 3, 3 }, { 0, 0, 0, 1, 1, 2, 3, 3, 3 },
			{ 0, 4, 0, 0, 2, 2, 2, 7, 3 }, { 4, 4, 4, 5, 5, 2, 2, 7, 7 },
			{ 4, 4, 4, 5, 5, 6, 6, 6, 7 }, { 4, 4, 8, 5, 5, 6, 6, 6, 7 },
			{ 8, 8, 8, 8, 5, 5, 6, 7, 7 }, { 8, 8, 8, 8, 5, 6, 6, 7, 7 }, };

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// public int getSubPoolIndex(int sizeOfSquareSide) {
	// return subPoolLocation[x][y];
	// }

	public int getSubPoolIndex(int sizeOfSquareSide) {
		int root = (int) Math.sqrt(sizeOfSquareSide);
		int a = x / root;
		int b = y / root;
		return root * a + b;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final Position position = (Position) o;

		if (x != position.x)
			return false;
		if (y != position.y)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = x;
		result = 29 * result + y;
		return result;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
