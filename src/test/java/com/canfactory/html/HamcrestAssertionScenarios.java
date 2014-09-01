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

import static com.canfactory.html.hamcrest.Any.any;
import static com.canfactory.html.hamcrest.Each.each;
import static com.canfactory.html.hamcrest.HasAttribute.hasAttribute;
import static com.canfactory.html.hamcrest.HasAttribute.hasAttributes;
import static com.canfactory.html.hamcrest.HasClass.hasClass;
import static com.canfactory.html.hamcrest.HasClass.hasClasses;
import static com.canfactory.html.hamcrest.HasCount.hasCount;
import static com.canfactory.html.hamcrest.HasElement.exists;
import static com.canfactory.html.hamcrest.HasId.hasId;
import static com.canfactory.html.hamcrest.HasText.hasText;
import static com.canfactory.html.hamcrest.None.none;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

@Test
public class HamcrestAssertionScenarios {

    public void shouldAssertHasText() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        //
        assertThat(fragment.all("#colours"), each(hasText("Colour")));
        assertThat(fragment.all("li"), any(hasText("Green")));
        assertThat(fragment.all("li"), none(hasText("pink")));

        // check the empty condition - todo, these need pulling out into a seperate test as they should all fail
       // assertThat( HtmlElement.Factory.fromString(""), each(hasText("Colour")));
     //  assertThat( HtmlElement.Factory.fromString(""), any(hasText("Colour")));
      //  assertThat( HtmlElement.Factory.fromString(""), none(hasText("Colour")));


        assertThat(fragment.nth(2, "li"), hasText("Green"));
    }

    public void shouldAssertHasAttribute() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertThat(fragment.first("ul"), hasAttribute("id", "colours"));
        assertThat(fragment.first("ul"), hasAttribute(new Attribute("id", "colours")));
        assertThat(fragment.first("ul"), not(hasAttribute("id", "numbers")));
    }


    public void shouldAssertHasAttributes() {
        HtmlElement element = HtmlElement.Factory.fromString("<p id=\"p1\" name=\"para1\"></p>");

        Attribute name = new Attribute("name", "para1");
        Attribute id = new Attribute("id", "p1");
        assertThat(element, hasAttributes(id));
        assertThat(element, hasAttributes(id, name));
        assertThat(element, hasAttributes(name, id));
        assertThat(element, not(hasAttributes(new Attribute("name", "paraX"))));
        assertThat(element, not(hasAttributes(name, id, new Attribute("class", "classX"))));
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
