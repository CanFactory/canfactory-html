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
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

// does the element have the expected id
public class HasId extends BaseHtmlElementMatcher {

    private String expectedId;

    public HasId(String expectedId) {
        this.expectedId = expectedId;
    }

    @Factory
    public static Matcher<HtmlElement> hasId(String expectedId) {
        return new HasId(expectedId);
    }

    @Override
    protected boolean matchesSafely(HtmlElement html) {
        return html.attributes().value("id").equals(expectedId);
    }

    public void describeTo(Description description) {
        description.appendText("An HtmlElement with the ID ").appendValue(expectedId);
    }
}
