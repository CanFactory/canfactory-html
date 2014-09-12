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
 * An empty {@link com.canfactory.html.HtmlElement}. All the methods return valid but empty objects so they can safely
 * chained without caring about null pointer exceptions.
 */
public class EmptyHtmlElement implements HtmlElement {

    private static String EMPTY = "";
    private static Attributes ATTRIBUTES = Attributes.build().end();
    public static HtmlElement INSTANCE = new EmptyHtmlElement();

    public boolean exists() {
        return false;
    }

    public HtmlElement first(String cssSelector) {
        return INSTANCE;
    }

    public HtmlElement nth(int index, String cssSelector) {
        return INSTANCE;
    }

    public HtmlElement last(String cssSelector) {
        return INSTANCE;
    }

    public HtmlFragment all(String cssSelector) {
        return EmptyHtmlFragment.INSTANCE;
    }

    public HtmlFragment all(Selector selector) {
         return EmptyHtmlFragment.INSTANCE;
    }

    public HtmlElement first(Selector selector) {
        return INSTANCE;
    }

    public HtmlElement nth(int index, Selector selector) {
        return INSTANCE;
    }

    public HtmlElement last(Selector selector) {
        return INSTANCE;
    }

    public String text() {
        return EMPTY;
    }

    public String outerHtml() {
        return EMPTY;
    }

    public Attributes attributes() {
        return ATTRIBUTES;
    }

    public String tagName() {
        return null;
    }

    public String classNames() {
        return EMPTY;
    }

    public HtmlElements elements() {
        return HtmlElements.Factory.empty();
    }
}
