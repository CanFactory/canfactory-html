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

import java.io.IOException;
import java.io.InputStream;

import static org.testng.Assert.fail;

public class Html {
    private Document doc;

    public Html(String html) {
        doc = Jsoup.parse(html);
    }

    public Html(InputStream is) {
        try {
            doc = Jsoup.parse(is, "UTF-8", "http://example.com/");
        } catch (IOException ioex) {
            throw new RuntimeException(ioex);
        }
    }

    public void assertHasText(String expectedText) {
        for (Element ele : doc.body().getAllElements()) {
            if (ele.hasText() && ele.text().contains(expectedText)) {
                return;
            }
        }
        fail("Cannot find " + expectedText);
    }

    /**
     * Find the first element in the current document that matches this tag. Heirachy doesn't
     * matter. And subsequent matching elements will be ignored
     *
     * @param tagName
     * @return
     */
    public Html first(String tagName) {
        for (Element ele : doc.body().getAllElements()) {
            if (ele.tagName().equals(tagName)) {
                return new Html(ele.outerHtml());
            }
        }
        return null;    // or NSE exception ? or an Option[Html] (and then flatMap to chain ?)
    }
}
