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
public class EntryContent implements Comparable{

    Integer content;

    public EntryContent(int i) {
        this(new Integer(i));
    }

    public EntryContent(String s) {
        this(Integer.valueOf(s));
    }

    public EntryContent(Integer i) {
        content = i;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final EntryContent that = (EntryContent) o;

        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    public int hashCode() {
        return (content != null ? content.hashCode() : 0);
    }

    public String toString() {
        return content.toString();
    }

    public int compareTo(Object o) {
        return content.compareTo(((EntryContent)o).content);
    }
}
