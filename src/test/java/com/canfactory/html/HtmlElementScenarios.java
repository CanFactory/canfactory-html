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

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * Scenarios for functionality specific to the HtlmElement
 */

@Test
public class HtmlElementScenarios {

    public void shouldReturnAttributes() {
        Attributes attrs = HtmlElement.Factory.fromString("<p class=\"highlight\" data-help=\"help message\">This is some html</p>").attributes();

        assertEquals(attrs.names(), Arrays.asList("class", "data-help"));
        assertEquals(attrs.value("class"), "highlight");
        assertEquals(attrs.value("data-help"), "help message");
    }


}
