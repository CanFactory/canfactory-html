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

import java.util.*;

public class HasClass extends BaseHtmlElementMatcher {
    private List<String> expectedClasses;

    public HasClass(List<String> expectedClasses) {
        this.expectedClasses = expectedClasses;
    }

    @Factory
    public static Matcher<HtmlElement> hasClass(String expectedClass) {
        return new HasClass(Arrays.asList(expectedClass));
    }

    @Factory
    public static Matcher<HtmlElement> hasClasses(String... expectedClasses) {
        return new HasClass(Arrays.asList(expectedClasses));
    }


    @Override
    public void describeTo(Description description) {
        String classes = null;
        for (String c : expectedClasses) {
            if (classes == null) {
                classes = c;
            } else {
                classes = classes + (" " + c);
            }
        }
        description.appendText("An HtmlElement with the class(es) ").appendValue(classes);
    }

    @Override
    protected boolean matchesSafely(HtmlElement html) {
        Set<String> toLookFor = new HashSet<String>(expectedClasses);
        for (String actualClass : html.attributes().value("class").split(" ")) {
            toLookFor.remove(actualClass.trim());
        }
        return toLookFor.isEmpty();
    }
}


