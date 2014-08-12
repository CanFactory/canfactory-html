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

import java.io.IOException;

/**
 * These test assertion style behaviour
 */

@Test
public class AssertionScenarios {

    public void shouldContainText() throws IOException {
        Html html = new Html(this.getClass().getResourceAsStream("/com/canfactory/html/sample.html"));
        html.assertHasText("This is some really simple html");
    }
}
