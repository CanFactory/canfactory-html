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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Attributes {
    private List<Attribute> attributes;

    private Attributes() {
        attributes = Collections.emptyList();
    }

    public Attributes(org.jsoup.nodes.Attributes jsoupAttributes) {
        attributes = new ArrayList<Attribute>(jsoupAttributes.size());
        for (org.jsoup.nodes.Attribute jsoupAttr : jsoupAttributes) {
            attributes.add(new Attribute(jsoupAttr));
        }
    }

    public static Builder build() {
        return new Builder();
    }

    public List<String> names() {
        List<String> result = new ArrayList<String>(attributes.size());
        for (Attribute attr : attributes) {
            result.add(attr.name);
        }
        return result;
    }

    public boolean hasAttribute(String name) {
        for (Attribute attr : attributes) {
            if (attr.name().equals(name)) return true;
        }
        return false;
    }

    public String value(String name) {
        for (Attribute attr : attributes) {
            if (attr.name().equals(name)) return attr.value();
        }
        return "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Attribute attr : attributes) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(attr.toString());
        }
        return sb.toString();
    }

    public boolean contains(Attribute expectedAttr) {
        for (Attribute attr : attributes) {
            if (attr.equals(expectedAttr)) return true;
        }
        return false;
    }

    public static class Builder {
        private List<Attribute> attrs = new ArrayList<Attribute>();

        private Builder() {
            // force clients to use the static build() method on Attributes
        }

        public Builder attr(String name, String value) {
            attrs.add(new Attribute(name, value));
            return this;
        }

        public Builder attr(Attribute attr) {
            attrs.add(attr);
            return this;
        }

        public Attributes end() {
            Attributes result = new Attributes();
            result.attributes = attrs;
            return result;
        }

    }


    public static class Attribute {
        private String name;
        private String value;

        public Attribute(String name, String value) {
            assert (name != null);
            assert (value != null);
            this.name = name;
            this.value = value;
        }

        public Attribute(org.jsoup.nodes.Attribute jsoupAttr) {
            this.name = jsoupAttr.getKey();
            this.value = jsoupAttr.getValue();
        }

        public String name() {
            return name;
        }

        public String value() {
            return value;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append("=\"").append(value).append("\"");
            return sb.toString();
        }

        @Override
        public int hashCode() {
            return name.hashCode() ^ value.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Attribute) {
                Attribute otherAttribute = (Attribute) other;
                return this.name.equals(otherAttribute.name) && this.value.equals(otherAttribute.value);
            }
            return false;
        }
    }
}
