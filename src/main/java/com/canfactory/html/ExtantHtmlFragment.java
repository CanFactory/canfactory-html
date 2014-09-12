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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An valid (exists) {@link com.canfactory.html.HtmlFragment}. Use the static methods
 * on {@link com.canfactory.html.HtmlFragment.Factory} to create instances.
 */
public class ExtantHtmlFragment extends ToStringComparable implements HtmlFragment {

    protected Document document;
    protected Elements elements;

    ExtantHtmlFragment(String html) {
        document = Jsoup.parse(html);
    }

    ExtantHtmlFragment(Document jsoup) {
        this.document = jsoup;
    }

    ExtantHtmlFragment(Element element) {
        this.elements = new Elements(1);
        this.elements.add(element);
    }

    ExtantHtmlFragment(Elements elements) {
        this.elements = elements;
    }

    protected Document jsoupDoc() {
        if (document == null) {
            document = Jsoup.parse(elements.outerHtml());
        }
        return document;
    }

    protected Element firstElement() {
        return allElements().first();
    }

    protected Elements allElements() {
        if (elements == null) {
            elements = document.select("body > *");
        }
        return elements;
    }

    public boolean exists() {
        return true;
    }

    public HtmlElement first(String cssSelector) {
        return nth(1, cssSelector);
    }

    public HtmlElement nth(int index, String cssSelector) {
        if (index <= 0) throw new IllegalArgumentException("Index must be one based");
        Elements matched = jsoupDoc().select(cssSelector);
        if (matched.size() >= index && index > 0) {
            return new ExtantHtmlElement(matched.get(index - 1).outerHtml());
        } else {
            return new EmptyHtmlElement();
        }
    }

    public HtmlElement last(String cssSelector) {
        Elements matched = jsoupDoc().select(cssSelector);
        if (matched.size() > 0) {
            return new ExtantHtmlElement(matched.get(matched.size() - 1).outerHtml());
        } else {
            return new EmptyHtmlElement();
        }
    }

    public HtmlFragment all(String cssSelector) {
        Elements matched = jsoupDoc().select(cssSelector);
        return HtmlFragment.Factory.fromElements(matched);
    }

    public HtmlFragment all(Selector selector) {
        Elements elementsToTest;
        if (elements != null) {
            elementsToTest = elements;
        } else {
            elementsToTest = document.select("body > *");
        }

        List<Element> accumulator = new ArrayList<Element>();
        HtmlElements ancestors = HtmlElements.Factory.EMPTY;
        for (Element ele : elementsToTest) {
            if (!ele.tagName().equals("script")) {
                recursiveSelector(accumulator, selector, ancestors, ele);
            }
        }
        return HtmlFragment.Factory.fromElements(new Elements(accumulator));
    }

    public HtmlElement first(Selector selector) {
        HtmlElements all = all(selector).elements();
        if (all.size() > 0) {
            return all.iterator().next();
        } else {
            return EmptyHtmlElement.INSTANCE;
        }
    }

    public HtmlElement nth(int index, Selector selector) {
        if (index <= 0) throw new IllegalArgumentException("Index must be one based");
        HtmlElements all = all(selector).elements();
        if (all.size() >= index) {
            Iterator<HtmlElement> iter = all.iterator();
            for (int i = 0; i < index - 1; i++) iter.next();
            return iter.next();
        } else {
            return EmptyHtmlElement.INSTANCE;
        }
    }

    public HtmlElement last(Selector selector) {
        HtmlElements all = all(selector).elements();
        if (all.size() > 0) {
            return nth(all.size(), selector);
        } else {
            return EmptyHtmlElement.INSTANCE;
        }
    }

    private void recursiveSelector(List<Element> accumulator, Selector selector, HtmlElements ancestors, Element ele) {
        HtmlElement htmlElement = HtmlElement.Factory.fromElement(ele);
        if (selector.matches(ancestors, htmlElement)) {
            accumulator.add(ele);
        }
        for (Element child : ele.children()) {
            if (!child.tagName().equals("script")) {
                recursiveSelector(accumulator, selector, ancestors.append(htmlElement), child);
            }
        }
    }

    public HtmlElements elements() {
        Elements e = allElements();

        List<HtmlElement> results = new ArrayList<HtmlElement>(e.size());
        for (Element ele : e) {
            if (!ele.tagName().equals("script")) {
                results.add(HtmlElement.Factory.fromElement(ele));
            }
        }
        return HtmlElements.Factory.fromList(results);
    }

    public String text() {
        if (document != null) {
            return document.text();
        } else {
            return elements.text();
        }
    }

    public String outerHtml() {
        return allElements().outerHtml();
    }

    @Override
    public String toString() {
        return "An HtmFragment of\n" + outerHtml();
    }
}
