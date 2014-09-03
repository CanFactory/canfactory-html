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

package com.canfactory.html.hamcrest;

import com.canfactory.html.HtmlFragment;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class OneTest {

    public void shouldPass() {
        One one = new One(new SimpleTextMatcher("Dog"));

        assertTrue(one.matchesSafely(HtmlFragment.Factory.fromString("<li>Dog</li><li>Cat</li><li>Rabbit</li>")));
    }

    public void shouldFailIfNotPresent() {
        One one = new One(new SimpleTextMatcher("Rhino"));

        boolean result = one.matchesSafely(HtmlFragment.Factory.fromString("<li>Dog</li><li>Cat</li><li>Rabbit</li>"));
        Description description = new StringDescription();
        one.describeTo(description);

        assertEquals(description.toString(), "Failed to match once for any element in the fragment, instead matched 0 times.\nFailed to find \"Rhino\"");
        assertFalse(result);
    }

    public void shouldFailIfMoreThanOnePresent() {
        One one = new One(new SimpleTextMatcher("Dog"));

        boolean result = one.matchesSafely(HtmlFragment.Factory.fromString("<li>Dog</li><li>Dog</li><li>Cat</li><li>Rabbit</li>"));
        Description description = new StringDescription();
        one.describeTo(description);

        assertEquals(description.toString(), "Failed to match once for any element in the fragment, instead matched 2 times.\nFailed to find \"Dog\"");
        assertFalse(result);
    }
}
