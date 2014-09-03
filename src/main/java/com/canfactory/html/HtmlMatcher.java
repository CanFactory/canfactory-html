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

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HtmlMatcher {
    private ExtantHtmlFragment html;

    public HtmlMatcher(ExtantHtmlFragment html) {
        this.html = html;
    }

    // Does this outerHtml contain the expected text
    public MatchResult containsText(String expectedText) {
        for (Element ele : html.document.body().getAllElements()) {
            if (ele.hasText() && ele.text().contains(expectedText)) {
                return MatchResult.success();
            }
        }
        return MatchResult.fail();
    }

    public MatchResult is(ExtantHtmlFragment expected) {
        return is(expected.firstElement());
    }

    public MatchResult is(String expected) {
        return is(new ExtantHtmlFragment(expected));
    }


    // does this element match the
    private MatchResult is(Element expected) {
        Element actual = html.firstElement();
        // TODO - what if there is no element ? or more than one?
        List<String> messages = new ArrayList<String>();
        for (Attribute expectedAttr : expected.attributes()) {
            String actualAttr = actual.attributes().get(expectedAttr.getKey());
            if (actualAttr == null) {
                messages.add("Attr: " + expectedAttr.getKey() + " is missing");
            } else {
                if (!actualAttr.equals(expectedAttr.getValue())) {
                    messages.add("Attr: " + expectedAttr.getKey() +
                            " has " + actualAttr +
                            ", but " + expectedAttr.getValue() + " was expected");
                }
            }

        }
        if (expected.hasText()) {
            if (!actual.text().contains(expected.text())) {
                messages.add("Expected text of '" + expected.text() + "' not found in '" + actual.text() + "'");
            }
        }

        //for (String attr : expected.get)

//        for (Element ele : outerHtml.doc.body().getAllElements()) {
//            if (ele.hasText() && ele.text().contains(expectedText)) {
//                return MatchResult.success();
//            }
//        }
        return MatchResult.result(messages);
    }


    public static class MatchResult {
        private boolean passed;
        private List<String> messages;

        public MatchResult(boolean passed, List<String> messages) {
            this.passed = passed;
            this.messages = messages;
        }

        public boolean passed() {
            return passed;
        }

        public List<String> messages() {
            return messages;
        }

        public static MatchResult success() {
            return new MatchResult(true, Collections.EMPTY_LIST);
        }

        public static MatchResult fail() {
            return new MatchResult(false, Collections.EMPTY_LIST);
        }

        public static MatchResult fail(String... messages) {
            return new MatchResult(false, Arrays.asList(messages));
        }

        public static MatchResult result(List<String> messages) {
            return messages.isEmpty() ? success() : new MatchResult(false, messages);
        }

    }
}
