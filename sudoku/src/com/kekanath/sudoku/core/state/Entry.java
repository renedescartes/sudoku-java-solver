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

import java.util.Set;
import java.util.TreeSet;

/**
 * This class corresponds to an entry in the Sudoku square.
 * Consists of a String which can be from 1-9 or null, and a flag saying
 * fixed and a flag for filled(in case entry was not filled) and a list of possibilities
 * @author <a href="mailto:kannan.ekanath@tavant.com">Kannan Ekanath</a>.
 * @version $Id$
 */
public class Entry {
    EntryContent value;

    boolean fixed;

    boolean filled;

    Set options;

    public Entry(EntryContent value, boolean fixed, boolean filled) {
        this.value = value;
        this.fixed = fixed;
        this.filled = filled;
        this.options = new TreeSet();
    }

    public EntryContent getContent() {
        return value;
    }

    public boolean isFixed() {
        return fixed;
    }

    public boolean isFilled() {
        return filled;
    }

    public Set getOptions() {
        return options;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Entry entry = (Entry) o;

        if (value != null ? !value.equals(entry.value) : entry.value != null) return false;

        return true;
    }

    public int hashCode() {
        return (value != null ? value.hashCode() : 0);
    }

    public String toString() {
        return "Value [" + value + "], options " + options;
    }


}
