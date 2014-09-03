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
 * An valid (exists) {@link com.canfactory.html.HtmlElement}. Use the static methods
 * on {@link com.canfactory.html.HtmlElement.Factory} to create instances.
 */
public class ExtantHtmlElement extends ExtantHtmlFragment implements HtmlElement {

    private Attributes attributes;

    ExtantHtmlElement(String html) {
        super(html);
        assertValidContent();
    }

    ExtantHtmlElement(InputStream is) {
        super(is);
        assertValidContent();
    }


    ExtantHtmlElement(Element element) {
        super(element);
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public HtmlFragment all(Selector selector) {
        return null;
    }

    public Attributes attributes() {
        if (attributes == null) {
            attributes = new Attributes(firstElement().attributes());
        }
        return attributes;
    }

    public String tagName() {
        return firstElement().tagName();
    }

    private void assertValidContent() {
        if (document != null) {
            if (document.select("body > *").size() != 1) {
                throw new RuntimeException("This is not a valid HtmlElement - there must be a single root element");
            } else {
                return;
            }
        }
        throw new RuntimeException("This is not a valid HtmlElement - there must be a single root element");
    }
}
