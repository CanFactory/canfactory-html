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

public class ExtantHtmlElement extends ExtantHtmlFragment implements HtmlElement {
    public ExtantHtmlElement(String html) {
        super(html);
        assertValidContent();
    }

    protected ExtantHtmlElement(InputStream is) {
        super(is);
        assertValidContent();
    }


    public ExtantHtmlElement(Element element) {
        super(element);
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public HtmlElement nth(int index, String cssSelector) {
        return super.nth(index,cssSelector);
    }

    @Override
    public HtmlElement last(String cssSelector) {
        return super.last(cssSelector);
    }

    @Override
    public HtmlElement first(String cssSelector) {
        return super.first(cssSelector);
    }

    @Override
    public HtmlFragment all(String cssSelector) {
        return super.all(cssSelector);
    }

    @Override
    public String text() {
        return super.text();
    }

    @Override
    public String outerHtml() {
        if (elements != null) {
            return elements.outerHtml();
        } else {
            return doc.select("body > *").outerHtml();
        }
    }

    private void assertValidContent() {
        if (doc != null) {
            if (doc.select("body > *").size() != 1) {
                throw new RuntimeException("This is not a valid HtmlElement - there must be a single root element");
            } else {
                return;
            }
        }
        throw new RuntimeException("This is not a valid HtmlElement - there must be a single root element");
    }
}
