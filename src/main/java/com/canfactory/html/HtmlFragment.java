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

import org.jsoup.select.Elements;

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
public interface HtmlFragment extends BaseHtml {

    HtmlElements elements();

    public static class Factory {
        public static HtmlFragment fromStream(InputStream is) {
            if (is != null) {
                return new ExtantHtmlFragment(is);
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
    }
}
