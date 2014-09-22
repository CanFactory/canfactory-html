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

/**
 * An empty {@link com.canfactory.html.HtmlFragment}. All the methods return valid but empty objects so they can safely
 * chained without caring about null pointer exceptions.
 */
public class EmptyHtmlFragment implements HtmlFragment {

    private static String EMPTY = "";
    public static HtmlFragment INSTANCE = new EmptyHtmlFragment();

    public boolean exists() {
        return false;
    }

    public HtmlElement first(String cssSelector) {
        return EmptyHtmlElement.INSTANCE;
    }

    public HtmlElement nth(int index, String cssSelector) {
        return EmptyHtmlElement.INSTANCE;
    }

    public HtmlElement last(String cssSelector) {
        return EmptyHtmlElement.INSTANCE;
    }

    public HtmlFragment all(String cssSelector) {
        return INSTANCE;
    }

    public HtmlFragment all(Selector selector) {
        return EmptyHtmlElement.INSTANCE;
    }

    public HtmlElement first(Selector selector) {
        return EmptyHtmlElement.INSTANCE;
    }

    public HtmlElement nth(int index, Selector selector) {
        return EmptyHtmlElement.INSTANCE;
    }

    public HtmlElement last(Selector selector) {
        return EmptyHtmlElement.INSTANCE;
    }

    public HtmlElements elements() {
        return HtmlElements.Factory.empty();
    }

    public HtmlElements all() {
        return HtmlElements.Factory.empty();
    }

    public String text() {
        return EMPTY;
    }

    public String outerHtml() {
        return EMPTY;
    }
}
