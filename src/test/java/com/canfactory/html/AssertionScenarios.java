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

import com.canfactory.html.hamcrest.HasContent;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.canfactory.html.hamcrest.HasContent.hasContent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * These test assertion style behaviour
 */

@Test
public class AssertionScenarios {

    public void shouldContainText() throws IOException {
        HtmlFragment html = new HtmlFragment(this.getClass().getResourceAsStream("/com/canfactory/html/sample.html"));
        html.assertHasText("This is some really simple html");


        assertThat(html, equalTo(html));

    }

    public void shouldSupportUseHamCrestStyleMatchers() throws IOException{
        HtmlFragment html = new HtmlFragment(this.getClass().getResourceAsStream("/com/canfactory/html/sample.html"));

        assertThat(html, hasContent(html));

        assertThat(html, HasContent.hasContent("This is some really simple html"));



    }
}
