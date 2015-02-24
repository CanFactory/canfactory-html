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

import java.util.*;

import static org.testng.Assert.*;

@Test
public class ManyHtmlElementsTest {

    public void shouldGroupMultipleElements() {
        HtmlElements elements = listOfTags("p", Arrays.asList("a", "b", "c", "d", "e"));

        Iterator<HtmlElements> grouped = elements.grouped(2).iterator();
        assertEquals(grouped.next(),listOfTags("p", Arrays.asList("a","b")));
        assertEquals(grouped.next(),listOfTags("p", Arrays.asList("c","d")));
        assertEquals(grouped.next(),listOfTags("p", Arrays.asList("e")));
        assertFalse(grouped.hasNext());
        try {
            grouped.next();
            fail("NoSuchElementException expected");
        }
        catch (NoSuchElementException e){
            // expected
        }
    }


    private HtmlElements listOfTags(String tag, List<String> paragraphText) {
        List<HtmlElement> results = new ArrayList<HtmlElement>(paragraphText.size());
        for (String text : paragraphText){
            StringBuilder sb = new StringBuilder();
            sb.append("<").append(tag).append(">").append(text).append("</").append(tag).append(">");
            results.add(HtmlElement.Factory.fromString(sb.toString()));
        }
        return HtmlElements.Factory.fromList(results);
    }

}
