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

import com.canfactory.html.HtmlFragment.Factory;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

/**
 * These test assertion style behaviour
 */

@Test
public class AssertionScenarios {

    public void shouldContainText() throws IOException {
        HtmlFragment html = Factory.fromStream(this.getClass().getResourceAsStream("/com/canfactory/html/sample.html"));

        assertEquals (html.text(),"Sample Sample This is some really simple html for basic testing. See for more information.");


        //assertThat(outerHtml, equalTo(outerHtml));

    }

    public void shouldSupportUseHamCrestStyleMatchers() throws IOException{
        /*
        HtmlFragment outerHtml = new HtmlFragment(this.getClass().getResourceAsStream("/com/canfactory/outerHtml/sample.outerHtml"));

        assertThat(outerHtml, hasContent(outerHtml));

        assertThat(outerHtml, HasContent.hasHtml("This is some really simple outerHtml"));

        assertThat(outerHtml, hasAttributes({"class": "ribbon"}));

        String outerHtml = renderer.render();
        assertThat(new HtmlFragment(outerHtml).all("h1"), hasText("sdfiosjfij"));
        assertThat(new HtmlFragment(outerHtml).all("h1"), count(6));
        assertThat(new HtmlFragment(outerHtml).all("h1"), hasText("sdfiosjfij"));
        assertThat(new HtmlFragment(outerHtml).all(new HtmlMatcher(){}), hasText("sdfiosjfij"));
        assertThat(new HtmlFragment(outerHtml).all(new HtmlMatcher(){}), hasAttributes(map)); //or
        assertThat(new HtmlFragment(outerHtml).all(new HtmlMatcher(){}), hasAttributes(HtmlAttribute... attributes));

        assertThat(new HtmlFragment(outerHtml).all("img"), none());


        assertThat(new HtmlFragment(outerHtml).all("h1"), doesNoHaveText("sdfiosjfij"));
//        assertThat(new HtmlFragment(outerHtml).no("h1"), haveText("sdfiosjfij"));

    //    assertThat(new HtmlFragment(outerHtml).all("h1").withText("sss"), isNotPresent());




        .first("h1")    // HtmlElement - fail for no element
        .nth(4, "h1")   // HtmlElement - fail for no element
        .last("h1")     // HtmlElement - fail for no element
        .all("h1")      // ImmutableList<HtmlElement> or Iterator<HtmlElement> - fail for no element
        .all("form").all("input")

                .all("form input:first-child")

                .all("form").first("input")

Optional<? extends HtmlElement>     // don't have standard type om Java
Iterator<HtmlElement>   // check for one is painflul
ImmutableList<HtmlElement>   // higher order function but not standards

HtmlElement interface

            -> ?HtmlElement
            -> EmptyHtmlElement

HtmlElement
        .exists()


---------------------

        hasText()
        doesNotHaveText() // not(haveText())
        count(int)
        none()
        hasAttributes()
        doesNotHaveAttributes()
        hasClass(String... classname) hasClass("class1", "class2")
        doesNotHaveClass(String... classname)
        */
    }
}
