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

import com.canfactory.html.HtmlFragment.Selector;

/**
 * EXPERIMENTAL - will get merged into main API if useful
 */
@Beta
public class ExtractFragment {
    private Selector parentSelector;
    private Selector childSelector;

    public ExtractFragment(Selector parentSelector, Selector childSelector) {
        this.parentSelector = parentSelector;
        this.childSelector = childSelector;
    }

    public HtmlFragment findAll(HtmlFragment fragment) {
        return fragment.all(new Selector() {
            @Override
            public boolean matches(HtmlElements ancestors, HtmlElement element) {
                if (parentSelector.matches(ancestors, element)) {
                    return element.first(childSelector).exists();
                } else {
                    return false;
                }
            }
        });
    }

}
