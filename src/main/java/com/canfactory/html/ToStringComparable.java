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
 * A simple but inefficient equality and comparison implemented in terms of toString
 */
public class ToStringComparable implements Comparable {

    private int hashCode = Integer.MIN_VALUE;

    @Override
    public boolean equals(Object other) {
        if (other != null && other.getClass().equals(this.getClass())) {
            return this.toString().equals(other.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (hashCode == Integer.MIN_VALUE) {
            hashCode = toString().hashCode();
        }
        return hashCode;
    }

    public int compareTo(Object other) {
        if (other != null && other.getClass().equals(this.getClass())) {
            return this.toString().compareTo(other.toString());
        }
        return -1;
    }
}
