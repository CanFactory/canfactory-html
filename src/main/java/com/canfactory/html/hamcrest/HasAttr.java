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

import com.canfactory.html.Attributes.Attribute;
import com.canfactory.html.HtmlElement;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HasAttr extends BaseHtmlMatcher<HtmlElement> {
    private List<Attribute> expectedAttrs;


    public HasAttr(List<Attribute> expectedAttrs) {
        this.expectedAttrs = expectedAttrs;
    }

    @Factory
    public static Matcher<HtmlElement> hasAttr(String name, String value) {
        return new HasAttr(Arrays.asList(new Attribute(name, value)));
    }

    @Factory
    public static Matcher<HtmlElement> hasAttr(Attribute attr) {
        return new HasAttr(Arrays.asList(attr));
    }

    @Factory
    public static Matcher<HtmlElement> hasAttrs(Attribute... attrs) {
        return new HasAttr(Arrays.asList(attrs));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("An HtmlElement containing the attr ").appendValue(expectedAttrs);
    }

    @Override
    protected boolean matchesSafely(HtmlElement html) {
        Set<Attribute> toLookFor = new HashSet<Attribute>(expectedAttrs);
        for (Attribute actualAttr : html.attributes()) {
            toLookFor.remove(actualAttr);
        }
        return toLookFor.isEmpty();
    }
}


