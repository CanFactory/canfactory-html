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

public abstract class BaseHtmlElementMatcher extends TypeSafeMatcher<HtmlElement> {
    private HtmlElement elementToMatch;

    @Override
    public void describeMismatchSafely(HtmlElement item, Description mismatchDescription) {
        mismatchDescription.appendText("The actual html was \n\"").appendText(item.outerHtml()).appendText("\"");
    }

    // the description for an unexpected successful match  (see None for an example)
    public void describeMatchSafely(Description matchDescription) {
        if (elementToMatch != null) {
            matchDescription.appendText("The matched html was \n\"").appendText(elementToMatch.outerHtml()).appendText("\"");
        }
    }

    // subclasses should call this as first line in their match method so that reporting on unexpected successful matches is correct
    protected void matchingOn(HtmlElement element) {
        this.elementToMatch = element;
    }

}

