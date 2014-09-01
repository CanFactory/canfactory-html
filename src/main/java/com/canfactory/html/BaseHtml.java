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

/**
 * Common functionality shared across {@link com.canfactory.html.HtmlElement} and
 * {@link com.canfactory.html.HtmlFragment}.
 */
public interface BaseHtml {
    boolean exists();

    HtmlElement first(String cssSelector);

    HtmlElement nth(int index, String cssSelector);

    HtmlElement last(String cssSelector);

    HtmlFragment all(String cssSelector);

    String text();

    String outerHtml();
}
