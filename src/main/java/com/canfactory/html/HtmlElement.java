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


import org.jsoup.nodes.Element;

import java.io.InputStream;


/**
 * Represents an HtmlElement, i.e. part of the page.
 *
 * It may have just 1 root node.
 *
 * To allow easy chaining of methods without worrying about null pointer exceptions two implementation are provided.
 * {@link com.canfactory.html.ExtantHtmlElement} represents a valid element with data, whereas
 * {@link com.canfactory.html.EmptyHtmlElement} represents an empty element with no data. It methods return empty
 * objects, so they can safely chained. This is kind of a crude implementation of the Option<HtmlElement> that would be
 * possible in Java 8.
 */
public interface HtmlElement {

    // does this exists, i.e. have data
    boolean exists();

    HtmlElement first(String cssSelector);

    HtmlElement nth(int index, String cssSelector);

    HtmlElement last(String cssSelector);

    HtmlFragment all(String cssSelector);

    String text();

    String outerHtml();

    Attributes attributes();

    public static class Factory {
        public static HtmlElement fromStream(InputStream is) {
            if (is != null) {
                return new ExtantHtmlElement(is);
            } else {
                throw new RuntimeException("InputStream is missing");
            }
        }

        public static HtmlElement fromString(String html) {
            return new ExtantHtmlElement(html);
        }

        public static HtmlElement fromElement(Element element) {
            return (element == null) ? EmptyHtmlElement.INSTANCE : new ExtantHtmlElement(element);
        }
    }
}
