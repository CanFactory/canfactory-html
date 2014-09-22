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
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

/**
 * Represents an Html fragment, i.e. part of the page. This may possibly be a fully formed HtmlPage. It
 * could also actually be just a single element.
 *
 * It may have 1 or more root nodes.
 *
 * To allow easy chaining of methods without worrying about null pointer exceptions two implementation are provided.
 * {@link com.canfactory.html.ExtantHtmlFragment} represents a valid fragment with data, whereas
 * {@link com.canfactory.html.EmptyHtmlFragment} represents an empty fragment with no data. It methods return empty
 * objects, so they can safely chained. This is kind of a crude implementation of the Option<HtmlFragment> that would be
 * possible in Java 8.
 */
public interface HtmlFragment {

    // about the container ?? - these are ideally not part of the public API , then are only needed
    // to manipulate the collection
    boolean exists();

    /**
     * @deprecated - Use all() - its more consistent with the rest of the API
     */
    HtmlElements elements();

    HtmlElements all();


    // about searching / selecting

    HtmlElement first(String cssSelector);

    HtmlElement nth(int index, String cssSelector);

    HtmlElement last(String cssSelector);

    HtmlFragment all(String cssSelector);

    HtmlFragment all(Selector selector);

    HtmlElement first(Selector selector);

    HtmlElement nth(int index, Selector selector);

    HtmlElement last(Selector selector);


    // information on the html

    String text();

    String outerHtml();

    public interface Selector {
        boolean matches(HtmlElements ancestors, HtmlElement element);
    }

    public static class Factory {

        public static HtmlFragment fromStream(InputStream is) {
            if (is != null) {
                try {
                    return fromJsoupDoc(Jsoup.parse(is, "UTF-8", "http://example.com/"));
                } catch (IOException ioex) {
                    throw new RuntimeException(ioex);
                }
            } else {
                throw new RuntimeException("InputStream is missing");
            }
        }

        public static HtmlFragment fromString(String html) {
            if (html != null && html.length() > 0) {
                return new ExtantHtmlFragment(html);
            } else {
                return new EmptyHtmlFragment();
            }
        }

        public static HtmlFragment fromElements(Elements elements) {
            return elements.isEmpty() ? new EmptyHtmlFragment() : new ExtantHtmlFragment(elements);
        }

        public static HtmlFragment fromJsoupDoc(Document doc) {
            if (doc != null && doc.body() != null) {
                return new ExtantHtmlFragment(doc);
            } else {
                return new EmptyHtmlFragment();
            }
        }
    }
}
