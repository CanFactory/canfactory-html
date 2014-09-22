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

import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Wraps a list of {@link com.canfactory.html.HtmlElement}s. to allow safe operations
 * over the list.
 * <p/>
 * Add more methods as required.
 */
public interface HtmlElements extends Iterable<HtmlElement> {

    int size();

    <T> Iterable<T> map(Functor<T> functor);

    HtmlElements append(HtmlElement htlmElement);

    public static class Factory {

        static HtmlElements EMPTY = new ManyHtmlElements(new ArrayList<HtmlElement>(0));

        public static HtmlElements fromList(List<HtmlElement> elements) {
            if (elements != null) {
                if (elements.size() > 1) {
                    return new ManyHtmlElements(elements);
                } else if (elements.size() == 1) {
                    return new SingleHtmlElement(elements.get(0));
                } else {
                    return EMPTY;   // should ideally have its own implementation;
                }

            } else {
                throw new RuntimeException("elements is missing");
            }
        }

        public static HtmlElements fromElement(HtmlElement elements) {
            if (elements != null) {
                return new SingleHtmlElement(elements);
            } else {
                throw new RuntimeException("element is missing");
            }
        }

        public static HtmlElements empty() {
            return EMPTY;   // should ideally have its own implementation;
        }


        public static HtmlFragment fromElements(Elements elements) {
            return elements.isEmpty() ? new EmptyHtmlFragment() : new ExtantHtmlFragment(elements);
        }
    }


    public interface Functor<T> {
        T map(HtmlElement e);
    }


    public class TranformIterator<T> implements Iterator<T> {
        private Iterator<HtmlElement> rawIterator;
        private Functor<T> functor;

        public TranformIterator(Iterator<HtmlElement> raw, Functor<T> functor) {
            this.rawIterator = raw;
            this.functor = functor;
        }

        public boolean hasNext() {
            return rawIterator.hasNext();
        }

        public T next() {
            return functor.map(rawIterator.next());
        }

        public void remove() {
            throw new RuntimeException("Iterator.remove() not supported");
        }
    }

    public class TranformIterable<T> implements Iterable<T> {

        private Iterable<HtmlElement> rawIterable;
        private Functor<T> functor;

        public TranformIterable(Iterable<HtmlElement> raw, Functor<T> functor) {
            this.rawIterable = raw;
            this.functor = functor;
        }

        @Override
        public Iterator<T> iterator() {
            return new TranformIterator<T>(rawIterable.iterator(),functor);
        }
    }
}
