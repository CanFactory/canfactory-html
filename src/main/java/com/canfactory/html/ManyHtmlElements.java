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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ManyHtmlElements implements HtmlElements
{

    private List<HtmlElement> data;

    public ManyHtmlElements(List<HtmlElement> data) {
        this.data = data;
    }

    public int size() {
        return data.size();
    }


    @Override
    public Iterator<HtmlElement> iterator() {
        return Collections.unmodifiableList(data).iterator();
    }
}
