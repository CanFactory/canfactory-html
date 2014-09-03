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
public class OnceTest {

    public void shouldPass() {
        Once once = new Once(new SimpleTextMatcher("Dog"));

        assertTrue(once.matchesSafely(HtmlFragment.Factory.fromString("<li>Dog</li><li>Cat</li><li>Rabbit</li>")));
    }

    public void shouldFailIfNotPresent() {
        Once once = new Once(new SimpleTextMatcher("Rhino"));

        boolean result = once.matchesSafely(HtmlFragment.Factory.fromString("<li>Dog</li><li>Cat</li><li>Rabbit</li>"));
        Description description = new StringDescription();
        once.describeTo(description);

        assertEquals(description.toString(), "Failed to match once for any element in the fragment, instead matched 0 times.\nFailed to find \"Rhino\"");
        assertFalse(result);
    }

    public void shouldFailIfMoreThanOnePresent() {
        Once once = new Once(new SimpleTextMatcher("Dog"));

        boolean result = once.matchesSafely(HtmlFragment.Factory.fromString("<li>Dog</li><li>Dog</li><li>Cat</li><li>Rabbit</li>"));
        Description description = new StringDescription();
        once.describeTo(description);

        assertEquals(description.toString(), "Failed to match once for any element in the fragment, instead matched 2 times.\nFailed to find \"Dog\"");
        assertFalse(result);
    }
}
