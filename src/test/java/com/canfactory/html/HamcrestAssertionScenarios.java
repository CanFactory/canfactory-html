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

import com.canfactory.html.Attributes.Attribute;
import org.testng.annotations.Test;

import static com.canfactory.html.hamcrest.HasAttr.hasAttr;
import static com.canfactory.html.hamcrest.HasAttr.hasAttrs;
import static com.canfactory.html.hamcrest.HasClass.hasClass;
import static com.canfactory.html.hamcrest.HasClass.hasClasses;
import static com.canfactory.html.hamcrest.HasCount.hasCount;
import static com.canfactory.html.hamcrest.HasElement.exists;
import static com.canfactory.html.hamcrest.HasText.hasText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

@Test
public class HamcrestAssertionScenarios {

    public void shouldAssertHasText() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertThat(fragment.all("li"), hasText("Green"));
        assertThat(fragment.all("li"), not(hasText("pink")));
    }

    public void shouldAssertHasAttribute() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertThat(fragment.first("ul"), hasAttr("id", "colours"));
        assertThat(fragment.first("ul"), hasAttr(new Attribute("id", "colours")));
        assertThat(fragment.first("ul"), not(hasAttr("id", "numbers")));
    }


    public void shouldAssertHasAttributes() {
        HtmlElement element = HtmlElement.Factory.fromString("<p id=\"p1\" name=\"para1\"></p>");

        Attribute name = new Attribute("name", "para1");
        Attribute id = new Attribute("id", "p1");
        assertThat(element, hasAttrs(id));
        assertThat(element, hasAttrs(id, name));
        assertThat(element, hasAttrs(name, id));
        assertThat(element, not(hasAttrs(new Attribute("name", "paraX"))));
        assertThat(element, not(hasAttrs(name, id, new Attribute("class", "classX"))));
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

    public void shouldAssertCount() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertThat(fragment.all("ul"), hasCount(4));
        assertThat(fragment.all("#colours li"), hasCount(3));
        assertThat(fragment.all("li"), not(hasCount(0)));
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
