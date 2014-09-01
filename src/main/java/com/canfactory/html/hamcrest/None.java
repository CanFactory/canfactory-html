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
import org.hamcrest.TypeSafeMatcher;

// none must match
public class None extends TypeSafeMatcher<HtmlFragment> {
    private Matcher<HtmlElement> matcher;
    private int index;

    public None(Matcher<HtmlElement> matcher) {
        this.matcher = matcher;
    }

    @Factory
    public static Matcher<HtmlFragment> none(Matcher<HtmlElement> matcher) {
        return new None(matcher);
    }


    @Override
    public void describeTo(Description description) {
        description.appendText("Found unexpected match at element " + index + " in the fragment.");
        description.appendText("\nThe unexpected match is below:\n");
        description.appendDescriptionOf(matcher);
    }

    @Override
    protected boolean matchesSafely(HtmlFragment html) {
        if (!html.exists()) return false;

        index = 1;
        for (HtmlElement e : html.elements()) {
            if (matcher.matches(e)) return false;
            index++;
        }
        return true;
    }
}


