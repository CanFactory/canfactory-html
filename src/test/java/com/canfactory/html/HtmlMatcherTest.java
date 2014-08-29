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

package com.canfactory.html;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class HtmlMatcherTest {

    public void shouldContainText() {
        ExtantHtmlFragment html = loadExample("sample.html");
        assertTrue(new HtmlMatcher(html).containsText("This is some really simple html").passed());
        assertFalse(new HtmlMatcher(html).containsText("not found").passed());
    }

    public void shouldMatchElement() {
        ExtantHtmlFragment html = new ExtantHtmlFragment("<p class=\"content\">Lorem ipsum</p>");
        assertTrue(new HtmlMatcher(html).is("<p class=\"content\">Lorem ipsum</p>").passed());
        assertTrue(new HtmlMatcher(html).is("<p>Lorem ipsum</p>").passed());
        assertTrue(new HtmlMatcher(html).is("<p>Lorem</p>").passed());
        assertFalse(new HtmlMatcher(html).is("<p>LOREM IPSUM</p>").passed());
        assertFalse(new HtmlMatcher(html).is("<p class=\"body\">Lorem impsum</p>").passed());

    }

    private ExtantHtmlFragment loadExample(String exampleFileName) {
        return new ExtantHtmlFragment(this.getClass().getResourceAsStream("/com/canfactory/html/" + exampleFileName));
    }
}
