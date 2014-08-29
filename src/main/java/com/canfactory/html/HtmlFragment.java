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

import com.canfactory.html.hamcrest.HtmlElements;
import org.jsoup.select.Elements;

import java.io.InputStream;

public interface HtmlFragment {

    // does this exists, i.e. have data
    boolean exists();

    HtmlElement first(String cssSelector);

    HtmlElement nth(int index, String cssSelector);

    HtmlElement last(String cssSelector);

    HtmlFragment all(String cssSelector);

    HtmlElements elements();

    String text();

    String outerHtml();

    public static class Factory {
        public static HtmlFragment fromStream(InputStream is) {
            return new ExtantHtmlFragment(is);
        }

        public static HtmlFragment fromString(String html) {
            return new ExtantHtmlFragment(html);
        }

        public static HtmlFragment fromElements(Elements elements) {
            return elements.isEmpty() ? new EmptyHtmlFragment() : new ExtantHtmlFragment(elements);
        }
    }
}
