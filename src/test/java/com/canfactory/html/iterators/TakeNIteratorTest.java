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

import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;

/**
 * Created by ian on 23/02/2015.
 */
@Test
public class TakeNIteratorTest {
    private Iterator<String> rawList;

    public void shouldTakeFirstN() {
        rawList = Arrays.asList("a", "b", "c").iterator();
        assertEquals(consumeIter(new TakeNIterator<String>(rawList, 2)), Arrays.asList("a", "b"));
        assertEquals(consumeIter(new TakeNIterator<String>(rawList, 2)), Arrays.asList("c"));
        assertEquals(consumeIter(new TakeNIterator<String>(rawList, 2)), Collections.EMPTY_LIST);
    }

    protected List<String> consumeIter(Iterator<String> iter) {
        List<String> results = new ArrayList<String>();
        while (iter.hasNext()) {
            results.add(iter.next());
        }
        assertEndfOfIterator(iter);
        return results;
    }

    protected void assertEndfOfIterator(Iterator iter) {

        // random pick a strategy
        boolean testHasNextFirst = new Random(System.currentTimeMillis()).nextBoolean();
        //testHasNextFirst = true;

        if (testHasNextFirst) {
            assertFalse(iter.hasNext(), "hasNext() called first and should return false");

            try {
                iter.next();
                fail("NoSuchElementException expected - calling hasNext first");
            }    catch (NoSuchElementException nsex) {
                // expected
            }
        }
        else {
            try {
                iter.next();
                fail("NoSuchElementException expected - calling next first");
            }    catch (NoSuchElementException nsex) {
                // expected
            }


            assertFalse(iter.hasNext(), "hasNext() called last and should return false");
        }
    }
}
