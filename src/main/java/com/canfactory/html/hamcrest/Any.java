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
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

// a short circuit style match
public class Any extends BaseHtmlMatcher<HtmlFragment> {

    private Matcher<HtmlFragment> matcher;

    public Any(Matcher<HtmlFragment> matcher) {
        this.matcher = matcher;
    }

    @Factory
    public static Matcher<HtmlFragment> any(Matcher<HtmlFragment> matcher) {
        return new Any(matcher);
    }

    @Override
    public void describeTo(Description description) {
    }

    @Override
    protected boolean matchesSafely(HtmlFragment html) {
        for (HtmlElement e : html.elements()) {
            if (matcher.matches(e)) return true;
        }
        return false;
    }
}


