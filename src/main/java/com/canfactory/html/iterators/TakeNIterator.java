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

package com.canfactory.html.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TakeNIterator<T> implements Iterator<T> {
    private int toTakeCount = -1;
    private int count = 0;
    private Iterator<T> source;

    public TakeNIterator(Iterator<T> source, int toTakeCount) {
        this.source = source;
        this.toTakeCount = toTakeCount;
    }

    @Override
    public boolean hasNext() {
        if (count == toTakeCount) return false;
        return source.hasNext();
    }

    public T next() {
        if (count == toTakeCount) throw new NoSuchElementException();
        count++;
        return source.next();
    }

    public void remove() {
        throw new RuntimeException("Iterator.remove() not supported");
    }

}
