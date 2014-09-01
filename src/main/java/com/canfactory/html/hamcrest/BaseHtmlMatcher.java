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
import org.hamcrest.TypeSafeMatcher;

public abstract class BaseHtmlMatcher<T extends HtmlFragment> extends TypeSafeMatcher<T> {
    private boolean isElement;

    @Override
    public void describeMismatchSafely(T item, Description mismatchDescription) {
        mismatchDescription.appendText("the actual html was \n\"").appendText(item.outerHtml()).appendText("\"");
    }

    protected boolean isFragment() {
        return !isElement;
    }


    protected boolean isElement() {
        return isElement;
    }


    protected void checkType(T html) {
        if (html instanceof HtmlElement) {
            isElement = true;
        }
    }
}

