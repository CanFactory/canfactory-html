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
import org.testng.annotations.Test;

import static com.canfactory.html.hamcrest.Any.any;
import static com.canfactory.html.hamcrest.Each.each;
import static com.canfactory.html.hamcrest.HasClass.hasClass;
import static com.canfactory.html.hamcrest.HasClass.hasClasses;
import static com.canfactory.html.hamcrest.HasElement.exists;
import static com.canfactory.html.hamcrest.HasId.hasId;
import static com.canfactory.html.hamcrest.HasText.hasText;
import static com.canfactory.html.hamcrest.None.none;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

@Test
public class HtmlElementMatcherScenarios {

    public void shouldAssertHasText() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        // check over many using the each, any and none group matchers
        assertThat(fragment.all("#colours"), each(hasText("Colour")));
        assertThat(fragment.all("li"), any(hasText("Green")));
        assertThat(fragment.all("li"), none(hasText("Pink")));

        // asserting a single element
        assertThat(fragment.first("ul li"), hasText("Red"));
        assertThat(fragment.nth(2, "li"), hasText("Green"));
        assertThat(fragment.last("#colours"), hasText("Blue"));

    }



    public void shouldAssertHasClass() {
        HtmlElement element = HtmlElement.Factory.fromString("<p class=\"class1 class2\"></p>");

        assertThat(element, hasClass("class1"));
        assertThat(element, hasClass("class2"));
        assertThat(element, not(hasClass("classX")));
    }

    public void shouldAssertHasClasses() {
        HtmlElement element = HtmlElement.Factory.fromString("<p class=\"class1 class2\"></p>");

        assertThat(element, hasClasses("class1"));
        assertThat(element, hasClasses("class1", "class2"));
        assertThat(element, hasClasses("class2", "class1"));
        assertThat(element, not(hasClasses("classX")));
    }

    public void shouldAssertHasId() {
        HtmlElement element = HtmlElement.Factory.fromString("<div id=\"component-1\"></div>");

        assertThat(element, hasId("component-1"));
        assertThat(element, not(hasId("component-2")));

        HtmlElement elementWithNoId = HtmlElement.Factory.fromString("<div></div>");

        assertThat(elementWithNoId, not(hasId("component-with-no-id")));
    }


    public void shouldExist() {
        assertThat(HtmlElement.Factory.fromString("<p>Lorem Ipsum...</p>"), exists());
        assertThat(HtmlElement.Factory.fromString(null), not(exists()));

        assertThat(HtmlFragment.Factory.fromString("<p>Lorem Ipsum...</p>"), exists());
        assertThat(HtmlFragment.Factory.fromString(null), not(exists()));
    }

    private HtmlFragment loadExample(String exampleFileName) {
        return HtmlFragment.Factory.fromStream(this.getClass().getResourceAsStream("/com/canfactory/html/" + exampleFileName));
    }
}
