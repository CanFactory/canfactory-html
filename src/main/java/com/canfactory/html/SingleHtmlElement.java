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
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SingleHtmlElement extends ToStringComparable implements HtmlElements {

    private HtmlElement element;

    SingleHtmlElement(HtmlElement element) {
        this.element = element;
    }

    public Iterator<HtmlElement> iterator() {
        return new SingleItemIter(element);
    }

    public <T> Iterable<T> map(Functor<T> functor) {
        return new TranformIterable<T>(this, functor);
    }

    @Override
    public Iterable<HtmlElements> grouped(int size) {
        final HtmlElements THIS = this;
        final int SIZE = size;
        return new Iterable<HtmlElements>() {
            @Override
            public Iterator<HtmlElements> iterator() {
                return new GroupedIterator(THIS, SIZE);
            }
        };
    }

    public int size() {
        return 1;
    }

    public HtmlElements append(HtmlElement newElement) {
        List<HtmlElement> elements = new ArrayList<HtmlElement>(2);
        elements.add(element);
        elements.add(newElement);
        return HtmlElements.Factory.fromList(elements);
    }

    public static class SingleItemIter implements Iterator<HtmlElement> {

        private HtmlElement item;

        SingleItemIter(HtmlElement item) {
            this.item = item;
        }

        public boolean hasNext() {
            return item != null;
        }

        public HtmlElement next() {
            if (item != null) {
                HtmlElement ret = item;
                item = null;
                return ret;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new RuntimeException("not supported");
        }
    }


    @Override
    public String toString() {
        return element.outerHtml();
    }
}
