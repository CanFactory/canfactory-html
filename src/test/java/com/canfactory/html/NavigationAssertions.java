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

import static org.testng.Assert.assertNotNull;

/**
 * These test the ability to navigate through the HTML and pick out matching sections
 */

@Test
public class NavigationAssertions {

    public void shouldLocateFirstMatchingElement(){
        HtmlFragment htlm = fromResource("sample.html");

        HtmlFragment h1 = htlm.first("h1");
        assertNotNull(h1);
        h1.assertHasText("Sample");
    }

    private HtmlFragment fromResource(String name) {
        return new HtmlFragment(this.getClass().getResourceAsStream("/com/canfactory/html/" + name));

    }

}
