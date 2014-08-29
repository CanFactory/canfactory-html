//-----------------------------------------------------------------------
// Copyright Can Factory Limited, UK
// http://www.canfactory.com - mailto:info@canfactory.com
//
// The copyright to the computer program(s) (source files, compiled
// files and documentation) herein is the property of Can Factory
// Limited, UK.
//
// The program(s) may be used and/or copied only with the written
// permission of Can Factory Limited or in accordance with the terms
// and conditions stipulated in the agreement/contract under which
// the program(s) have been supplied.
//-----------------------------------------------------------------------

package com.canfactory.html;

import java.util.ArrayList;
import java.util.List;

/**
 * Wraps a list of {@link com.canfactory.html.HtmlElement}s. to allow safe operations
 * over the list.
 * <p/>
 * Add more methods as required.
 */
public class HtmlElements {

    private List<HtmlElement> data;
    public static HtmlElements EMPTY = new HtmlElements(new ArrayList<HtmlElement>(0));

    public HtmlElements(List<HtmlElement> data) {
        this.data = data;

    }

    public int size() {
        return data.size();
    }
}