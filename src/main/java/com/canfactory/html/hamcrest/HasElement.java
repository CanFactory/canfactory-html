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
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

// does the fragment have at least one element? i.e, is it not empty
public class HasElement extends TypeSafeMatcher<HtmlFragment> {

    @Factory
    public static Matcher<HtmlFragment> exists() {
        return new HasElement();
    }

    public void describeTo(Description description) {
        description.appendText("An html element or fragment with content");
    }

    @Override
    protected boolean matchesSafely(HtmlFragment html) {
        return html.exists();
    }
}


