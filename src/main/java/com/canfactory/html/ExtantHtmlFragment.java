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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * An valid (exists) {@link com.canfactory.html.HtmlFragment}. Use the static methods
 * on {@link com.canfactory.html.HtmlFragment.Factory} to create instances.
 */
public class ExtantHtmlFragment implements HtmlFragment {
    protected Document doc;
    protected Elements elements;

    ExtantHtmlFragment(String html) {
        doc = Jsoup.parse(html);
    }

    ExtantHtmlFragment(Document jsoup) {
        this.doc = jsoup;
    }

    ExtantHtmlFragment(Element element) {
        this.doc = Jsoup.parse(element.outerHtml());
    }

    ExtantHtmlFragment(Elements elements) {
        this.elements = elements;
    }

    protected ExtantHtmlFragment(InputStream is) {
        try {
            doc = Jsoup.parse(is, "UTF-8", "http://example.com/");
        } catch (IOException ioex) {
            throw new RuntimeException(ioex);
        }
    }

    protected Element firstElement() {
        if (elements != null) {
            return elements.get(0);
        } else {
            // there should be a neater way?
            return doc.select("body > *").first();
        }
    }

    protected Elements allJSoupElements() {
        if (elements != null) {
            return elements;
        } else {
            return doc.getAllElements();
        }
    }


    @Override
    public boolean exists() {
        return true;
    }

    public HtmlElement first(String cssSelector) {
        return nth(1, cssSelector);
    }

    @Override
    public HtmlElement nth(int index, String cssSelector) {
        if (index <= 0) throw new IllegalArgumentException("Index must be one based");
        Elements matched = allJSoupElements().select(cssSelector);
        if (matched.size() >= index && index > 0) {
            return new ExtantHtmlElement(matched.get(index - 1).outerHtml());
        } else {
            return new EmptyHtmlElement();
        }
    }

    @Override
    public HtmlElement last(String cssSelector) {
        Elements matched = allJSoupElements().select(cssSelector);
        if (matched.size() > 0) {
            return new ExtantHtmlElement(matched.get(matched.size() - 1).outerHtml());
        } else {
            return new EmptyHtmlElement();
        }
    }

    @Override
    public HtmlFragment all(String cssSelector) {
        Elements matched = allJSoupElements().select(cssSelector);
        return HtmlFragment.Factory.fromElements(matched);
    }

    @Override
    public HtmlElements elements() {
        Elements e;
        if (elements != null) {
            e = elements;
        } else {
            e = doc.select("body > *");
        }

        List<HtmlElement> results = new ArrayList<HtmlElement>(e.size());
        for (Element ele : e) {
            results.add(HtmlElement.Factory.fromElement(ele));

        }
        return new HtmlElements(results);
    }

    @Override
    public String text() {
        if (doc != null) {
            return doc.text();
        } else {
            return elements.text();
        }
    }

    @Override
    public String outerHtml() {
        if (elements != null) {
            return elements.outerHtml();
        } else {
            return doc.select("body > *").outerHtml();
        }
    }

    @Override
    public String toString() {
        return "An HtmFragment of\n" + outerHtml();
    }

}