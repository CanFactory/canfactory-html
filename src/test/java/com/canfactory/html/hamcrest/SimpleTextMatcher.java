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
import org.hamcrest.TypeSafeMatcher;

public class SimpleTextMatcher extends TypeSafeMatcher<HtmlElement> {

    private String text;

    public SimpleTextMatcher(String text) {
        this.text = text;
    }

    public void describeTo(Description description) {
        description.appendText("Failed to find ").appendValue(text);
    }

    @Override
    protected boolean matchesSafely(HtmlElement htmlElement) {
        return htmlElement.text().contains(text);
    }
}
