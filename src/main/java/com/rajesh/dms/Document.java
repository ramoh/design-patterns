package com.rajesh.dms;

import java.util.Map;

public class Document {

    private final Map<String, String> attributes;

    Document(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getAttribute(final String attributeName) {
        return this.attributes.get(attributeName);
    }

}
