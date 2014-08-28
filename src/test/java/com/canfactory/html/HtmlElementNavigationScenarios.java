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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Scenarios for basic navigation through an HtmlElement using CSS style selectors
 */

@Test
public class HtmlElementNavigationScenarios {

    public void shouldReturnElementsOuterHtml() {
        HtmlElement element = HtmlElement.Factory.fromString("<p>This is some html</p>");
        assertEquals(element.outerHtml(), "<p>This is some html</p>");
    }

    public void shouldReturnFirstElementUsingSelector() {
        HtmlElement element = HtmlElement.Factory.fromString("<ul><li>Item 1</li><li>Item 2</li></ul>");
        assertEquals(element.first("li").text(), "Item 1");
    }

    public void shouldReturnLastElementUsingSelector() {
        HtmlElement element = HtmlElement.Factory.fromString("<ul><li>Item 1</li><li>Item 2</li></ul>");
        assertEquals(element.last("li").text(), "Item 2");
    }

    public void shouldReturnAllUsingSelector() {
        HtmlElement element = HtmlElement.Factory.fromString("<ul><li>Item 1</li><li>Item 2</li></ul>");
        assertEquals(element.all("li").outerHtml(), "<li>Item 1</li>\n" +
                "<li>Item 2</li>");
    }

    public void shouldReturnEmptyHtmlElementIfNoMatch() {
        HtmlElement element = HtmlElement.Factory.fromString("<ul><li>Item 1</li><li>Item 2</li></ul>");
        assertTrue(element.last("table") instanceof EmptyHtmlElement);
        assertTrue(element.first("table") instanceof EmptyHtmlElement);
        assertTrue(element.nth(1, "table") instanceof EmptyHtmlElement);
        assertTrue(element.all("table") instanceof EmptyHtmlFragment);
    }

}
