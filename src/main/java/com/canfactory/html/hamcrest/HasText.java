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


public class HasText extends BaseHtmlFragmentMatcher {
    private String expectedText;

    public HasText(String text) {
        this.expectedText = text;
    }

    @Factory
    public static Matcher<HtmlFragment> hasText(String text) {
        return new HasText(text);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("An HtmlFragment containing the text ").appendValue(expectedText);
    }

    @Override
    protected boolean matchesSafely(HtmlFragment html) {
        return html.text().contains(expectedText);
    }
}

