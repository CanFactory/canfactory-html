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
import com.canfactory.html.HtmlFragment.Selector;
import org.testng.annotations.Test;

@Test
public class ExtractFragmentTest {

    public void shouldExtract(){
        HtmlFragment fragment = loadExample("data-tables.html");

        ExtractFragment extractor = new ExtractFragment(new Selector() {
            @Override
            public boolean matches(HtmlElements ancestors, HtmlElement element) {
                return element.classNames().contains("data");
            }
        },new Selector() {
            @Override
            public boolean matches(HtmlElements ancestors, HtmlElement element) {
                return element.tagName().equals("p") && element.text().contains("Green");
            }
        });


        HtmlFragment results = extractor.findAll(fragment);

        System.out.println(results);

    }


    private HtmlFragment loadExample(String exampleFileName) {
        return Factory.fromStream(this.getClass().getResourceAsStream("/com/canfactory/html/" + exampleFileName));
    }
}
