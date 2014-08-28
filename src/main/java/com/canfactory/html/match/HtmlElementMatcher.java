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

package com.canfactory.html.match;

import com.canfactory.html.HtmlElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface HtmlElementMatcher {

    MatchResult match(HtmlElement element);

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
