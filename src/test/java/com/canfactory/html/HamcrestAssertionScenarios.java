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

import com.canfactory.html.Attributes.Attribute;
import com.canfactory.html.HtmlFragment.Factory;
import org.testng.annotations.Test;

import static com.canfactory.html.hamcrest.HasAttr.hasAttr;
import static com.canfactory.html.hamcrest.HasCount.hasCount;
import static com.canfactory.html.hamcrest.HasText.hasText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

@Test
public class HamcrestAssertionScenarios {

    public void shouldAssertHasText() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertThat(fragment.all("li"), hasText("Green"));
        assertThat(fragment.all("li"), not(hasText("pink")));
    }

    public void shouldAssertHasAttribute() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertThat(fragment.first("ul"), hasAttr("id", "colours"));
        assertThat(fragment.first("ul"), hasAttr(new Attribute("id", "colours")));
        assertThat(fragment.first("ul"), not(hasAttr("id", "numbers")));
    }

    public void shouldAssertCount() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertThat(fragment.all("ul"), hasCount(4));
        assertThat(fragment.all("#colours li"), hasCount(3));
        assertThat(fragment.all("li"), not(hasCount(0)));
    }

    private HtmlFragment loadExample(String exampleFileName) {
        return Factory.fromStream(this.getClass().getResourceAsStream("/com/canfactory/html/" + exampleFileName));
    }
}
