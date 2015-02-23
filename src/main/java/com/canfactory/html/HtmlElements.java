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


import com.canfactory.html.iterators.TakeNIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Wraps a list of {@link com.canfactory.html.HtmlElement}s. to allow safe operations
 * over the list.
 * <p/>
 * Add more methods as required.
 */
public interface HtmlElements extends Iterable<HtmlElement> {

    int size();

    <T> Iterable<T> map(Functor<T> functor);

    /**
     * Partitions elements in fixed size iterable collections.
     * @param size the number of elements per group
     * @return An iterator producing HtmlElements collections of size size, except the last will be less than size size
     * if the elements don't divide evenly
     */
    Iterable<HtmlElements> grouped(int size);

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

    }


    public interface Functor<T> {
        T map(HtmlElement e);
    }


    public class TransformIter<T> implements Iterator<T> {
        private Iterator<HtmlElement> rawIterator;
        private Functor<T> functor;

        public TransformIter(Iterator<HtmlElement> raw, Functor<T> functor) {
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
            return new TransformIter<T>(rawIterable.iterator(),functor);
        }
    }

    public class GroupedIterator implements Iterator<HtmlElements> {
        private Iterator<HtmlElement> source;
        private HtmlElements next;
        private boolean nextRead;
        private int groupSize;

        public GroupedIterator(HtmlElements source, int size) {
            this.source = source.iterator();
            this.groupSize = size;
        }

        @Override
        public boolean hasNext() {
            if (!nextRead) {
                readNext();
                nextRead = true;
            }
            return next != null;
        }

        @Override
        public HtmlElements next() {
            if (!nextRead) {
                readNext();
            }
            if (next != null) {
                nextRead = false;
                return next;
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new RuntimeException("Iterator.remove() not supported");
        }

        private void readNext() {
            Iterator<HtmlElement> iter = source;
            if (iter.hasNext()) {
                next = consumeIter(new TakeNIterator<HtmlElement>(iter, groupSize));
            } else {
                next = null;
            }
        }

        protected HtmlElements consumeIter(Iterator<HtmlElement> iter) {
            List<HtmlElement> results = new ArrayList<HtmlElement>();
            while (iter.hasNext()) {
                results.add(iter.next());
            }
            return HtmlElements.Factory.fromList(results);
        }
    }
}
