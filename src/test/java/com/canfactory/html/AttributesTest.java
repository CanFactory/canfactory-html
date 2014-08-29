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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class AttributesTest {

    public void shouldBuildFromJsoupAttributes() {
        Document doc = Jsoup.parse("<p class=\"highlight\" data-help=\"help message\">This is some html</p>");
        Attributes attrs = new Attributes(doc.getAllElements().select("p").get(0).attributes());

        assertEquals(attrs.names(), Arrays.asList("class", "data-help"));
        assertEquals(attrs.value("class"), "highlight");
        assertEquals(attrs.value("data-help"), "help message");
    }

    public void shouldBuildUsingBuilder() {
        Attributes attrs = Attributes.build().attr("class", "highlight").end();

        assertEquals(attrs.names(), Arrays.asList("class"));
        assertEquals(attrs.value("class"), "highlight");
    }

    public void shouldTestIfAttributePresent() {
        Attributes attrs = Attributes.build().attr("class", "highlight").end();

        assertTrue(attrs.hasAttribute("class"));
        assertFalse(attrs.hasAttribute("id"));
        assertTrue(attrs.contains(new Attribute("class","highlight")));
        assertFalse(attrs.contains(new Attribute("class","bold")));
    }

    public void shouldReturnEmptyStringForMissingAttribute() {
        Attributes attrs = Attributes.build().end();

        assertEquals(attrs.value("class"), "");
    }

    public void shouldBuildFormattedString() {
        Attributes attrs = Attributes.build().attr("id", "main-body").attr("class", "highlight").end();

        assertEquals(attrs.toString(),"id=\"main-body\" class=\"highlight\"");
    }
}
