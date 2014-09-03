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
import com.canfactory.html.HtmlFragment;
import org.testng.annotations.Test;

import static com.canfactory.html.hamcrest.HasAttribute.hasAttribute;
import static com.canfactory.html.hamcrest.HasAttribute.hasAttributes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

@Test
public class HasAttributeTest {

    public void shouldAssertHasAttribute() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertThat(fragment.first("ul"), hasAttribute("id", "colours"));
        assertThat(fragment.first("ul"), hasAttribute(new Attribute("id", "colours")));
        assertThat(fragment.first("ul"), not(hasAttribute("id", "numbers")));
    }

    public void shouldAssertAttributePresenceWithoutValue() {
        HtmlFragment html = HtmlFragment.Factory.fromString("<ul><li class=\"one\">One</li><li>Two</ul>");

        assertThat(html.first("li"), hasAttribute("class"));
        assertThat(html.last("li"), not(hasAttribute("class")));
    }

    public void shouldAssertMultipleAttributes() {
        HtmlFragment html = HtmlFragment.Factory.fromString("<p class=\"one\" lang=\"fr\" >Une</p>");

        assertThat(html.first("p"), hasAttributes("class", "one", "lang", "fr"));
        assertThat(html.first("p"), not(hasAttributes("class", "one", "lang", "en")));
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

    private HtmlFragment loadExample(String exampleFileName) {
        return HtmlFragment.Factory.fromStream(this.getClass().getResourceAsStream("/com/canfactory/html/" + exampleFileName));
    }
}
