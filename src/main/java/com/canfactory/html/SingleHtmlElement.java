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

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleHtmlElement implements HtmlElements {
    private HtmlElement e;

    public SingleHtmlElement(HtmlElement e) {
        this.e = e;
    }

    @Override
    public Iterator<HtmlElement> iterator() {
        return new SingleItemIter(e);
    }

    @Override
    public int size() {
        return 1;
    }

    public static class SingleItemIter implements Iterator<HtmlElement> {
        private HtmlElement item;

        SingleItemIter(HtmlElement item) {
            this.item = item;
        }

        @Override
        public boolean hasNext() {
            return item != null;
        }

        @Override
        public HtmlElement next() {
            if (item != null) {
                HtmlElement ret = item;
                item = null;
                return ret;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new RuntimeException("not supported");
        }
    }

    ;

}
