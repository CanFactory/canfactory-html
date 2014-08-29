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

import com.canfactory.html.ExtantHtmlFragment;
import com.canfactory.html.HtmlFragment;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


public class HasContent extends TypeSafeMatcher<ExtantHtmlFragment> {
    public HasContent(HtmlFragment html) {
        //super("containing", ignoringCase, substring);
    }


    /**
     * Creates a matcher that matches if the examined {@link String} contains the specified
     * {@link String} anywhere.
     * <p/>
     * For example:
     * <pre>assertThat("myStringOfNote", containsString("ring"))</pre>
     *
     * @param substring the substring that the returned matcher will expect to find within any examined string
     */
    @Factory
    public static Matcher<ExtantHtmlFragment> hasContent(String substring) {
        return new HasContent(HtmlFragment.Factory.fromString(substring));
    }

    @Factory
    public static Matcher<ExtantHtmlFragment> hasContent(ExtantHtmlFragment html) {
        return new HasContent(html);
    }


//    @Override
//    public void describeMismatchSafely(String item, Description mismatchDescription) {
//        mismatchDescription.appendText("was \"").appendText(item).appendText("\"");
//    }

    @Override
    public void describeTo(Description description) {
//        description.appendText("a string ")
//                .appendText(relationship)
//                .appendText(" ")
//                .appendValue(substring);
//        if (ignoringCase) {
//            description.appendText(" ignoring case");
//        }
    }


    @Override
    protected boolean matchesSafely(ExtantHtmlFragment html) {
        return true;
    }
}


