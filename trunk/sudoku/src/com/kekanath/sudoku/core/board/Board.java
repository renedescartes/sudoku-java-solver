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

import java.util.*;

/**
 * A board is basically the sudoku board that is presented by the user. It has a fixed
 * number entries in the form a square of fixed size. Each and every cell has an
 * {@link com.kekanath.sudoku.core.state.Entry} in addition to that it maintains some internal state.
 *
 * @author <a href="mailto:kannan.ekanath@tavant.com">Kannan Ekanath</a>.
 * @version $Id$
 */
public class Board {
    /**
     * It is a (Position, Entry) pair
     */
    Map entries;

    public Board(Map entries) {
        this.entries = entries;
    }

    public Map getEntries() {
        return entries;
    }
}
