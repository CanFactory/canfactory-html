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
import com.canfactory.html.HtmlElements;
import com.canfactory.html.HtmlFragment;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;


public class HasText extends BaseHtmlMatcher<HtmlFragment> {
    private String expectedText;
    private boolean isFragment;

    public HasText(String text) {
        this.expectedText = text;
    }

    @Factory
    public static Matcher<HtmlFragment> hasText(String text) {
        return new HasText(text);
    }

    @Override
    public void describeTo(Description description) {
        if (isFragment) {
            description.appendText("An HtmlFragment where all elements contained the text ").appendValue(expectedText);
        } else {
            description.appendText("An HtmlElement containing the text ").appendValue(expectedText);
        }
    }

    @Override
    protected boolean matchesSafely(HtmlFragment html) {
        checkType(html);
        if (isElement()) {
            return html.text().contains(expectedText);
        } else {
            HtmlElements elements = html.elements();
            for (HtmlElement e : elements) {
                if (!e.text().contains(expectedText)) {
                    return false;
                }
            }
            return true;
        }
    }
}


