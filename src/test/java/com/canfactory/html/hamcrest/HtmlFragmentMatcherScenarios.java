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

import com.canfactory.html.HtmlElement;
import com.canfactory.html.HtmlFragment;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeMatcher;
import org.testng.annotations.Test;

import static com.canfactory.html.hamcrest.Count.count;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class HtmlFragmentMatcherScenarios {

    public void shouldMatchOnEachElement() {
        HtmlFragment fragment = HtmlFragment.Factory.fromString("<li>Item 1</li><li>Item 2</li>");

        assertTrue(new Each(new SimpleTextMatcher("Item")).matchesSafely(fragment));
        assertFalse(new Each(new SimpleTextMatcher("Item 1")).matchesSafely(fragment));
        assertFalse(new Each(new SimpleTextMatcher("Not found")).matchesSafely(fragment));
    }

    public void shouldAlwaysFailEachForAnEmptyFragment() {
        HtmlFragment fragment = HtmlFragment.Factory.fromString("");

        assertFalse(new Each(new SimpleTextMatcher("Not found")).matchesSafely(fragment));
    }

    public void shouldCreateGoodDescriptionIfEachMatchFails() {
        Each each = new Each(new SimpleTextMatcher("One"));
        each.matchesSafely(HtmlFragment.Factory.fromString("<li>One</li><li>Two</li>"));
        Description description = new StringDescription();
        each.describeTo(description);

        assertEquals(description.toString(), "Failed to match condition at element 2 in the fragment.\n" +
                "The failed match is below:\n" +
                "Failed to find \"One\"");
    }

    public void shouldMatchOnAnyElement() {
        HtmlFragment fragment = HtmlFragment.Factory.fromString("<li>Item 1</li><li>Item 2</li>");

        assertTrue(new Any(new SimpleTextMatcher("Item")).matchesSafely(fragment));
        assertTrue(new Any(new SimpleTextMatcher("Item 1")).matchesSafely(fragment));
        assertFalse(new Any(new SimpleTextMatcher("Not found")).matchesSafely(fragment));
    }

    public void shouldAlwaysFailAnyForAnEmptyFragment() {
        HtmlFragment fragment = HtmlFragment.Factory.fromString("");

        assertFalse(new Any(new SimpleTextMatcher("Not found")).matchesSafely(fragment));
    }

    public void shouldCreateGoodDescriptionIfAnyMatchFails() {
        Any any = new Any(new SimpleTextMatcher("Apple"));
        any.matchesSafely(HtmlFragment.Factory.fromString("<li>One</li><li>Two</li>"));
        Description description = new StringDescription();
        any.describeTo(description);

        assertEquals(description.toString(), "Failed to match condition for any element in the fragment.\n" +
                "The failed match is below:\n" +
                "Failed to find \"Apple\"");
    }

    public void shouldMatchOnNoneElements() {
        HtmlFragment fragment = HtmlFragment.Factory.fromString("<li>Item 1</li><li>Item 2</li>");

        assertFalse(new None(new SimpleTextMatcher("Item")).matchesSafely(fragment));
        assertFalse(new None(new SimpleTextMatcher("Item 1")).matchesSafely(fragment));
        assertTrue(new None(new SimpleTextMatcher("Not found")).matchesSafely(fragment));
    }


    public void shouldAlwaysFailNoneForAnEmptyFragment() {
        HtmlFragment fragment = HtmlFragment.Factory.fromString("");

        assertFalse(new None(new SimpleTextMatcher("Not found")).matchesSafely(fragment));
    }

    public void shouldCreateGoodDescriptionIfNoMatchFails() {
        None none = new None(new SimpleTextMatcher("One"));
        none.matchesSafely(HtmlFragment.Factory.fromString("<li>One</li><li>Two</li>"));
        Description description = new StringDescription();
        none.describeTo(description);

        assertEquals(description.toString(), "Found unexpected match at element 1 in the fragment.\n" +
                "The unexpected match is below:\n" +
                "not Failed to find \"One\"");
    }


    public void shouldAssertCount() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertThat(fragment.all("ul"), count(4));
        assertThat(fragment.all("#colours li"), count(3));
        assertThat(fragment.all("li"), not(count(0)));
    }

    private HtmlFragment loadExample(String exampleFileName) {
        return HtmlFragment.Factory.fromStream(this.getClass().getResourceAsStream("/com/canfactory/html/" + exampleFileName));
    }
}
