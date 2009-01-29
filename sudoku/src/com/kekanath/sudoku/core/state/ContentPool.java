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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:kannan.ekanath@tavant.com">Kannan Ekanath</a>.
 * @version $Id$
 */
public class ContentPool {
    public static EntryContent EMPTY_CONTENT = new EntryContent(-1);

    Map map;

    public ContentPool(int size) {
        map = new HashMap();
        for(int i = 1; i <= size; i++)
            map.put(new Integer(i), new EntryContent(i));
    }

    public EntryContent getContent(int i) {
        return (EntryContent) map.get(new Integer(i));
    }

    public List getContentPoolCopy() {
        return new ArrayList(map.values());
    }
}
