package com.rajesh.dms;

import java.util.HashMap;
import java.util.Map;

public final class Document {

    private final Map<String, String> attributes;

    Document(Map<String, String> attributes) {
        this.attributes = new HashMap<>(attributes);
    }

    public String getAttribute(final String attributeName) {
        return this.attributes.get(attributeName);
    }

}
