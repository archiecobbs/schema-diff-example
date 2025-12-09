
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;

import java.io.StringWriter;

/**
 * JSON utility methods.
 */
public final class JacksonUtil {

    public static final JsonFactory JSON_FACTORY = new JsonFactory();

    private JacksonUtil() {
    }

    /**
     * Parse a JSON object or value.
     *
     * @param text JSON string, possibly null
     * @return parsed node, or null if {@code text} is null or contains only whitespace
     * @throws IllegalArgumentException if the string could not be parsed
     */
    public static JsonNode parse(String text) {
        if (text == null || text.trim().length() == 0)
            return null;
        Preconditions.checkArgument(!text.equals("undefined"), "can't convert \"undefined\" to JSON");
        final ObjectMapper mapper = new ObjectMapper(JSON_FACTORY);
        try {
            return mapper.readValue(text, JsonNode.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Encode a JSON object into its string form.
     *
     * @param node JSON node, must not be null
     * @return equivalent JSON text, or null if {@code node} is null
     * @throws IllegalArgumentException if {@code node} could not be serialized for some reason
     */
    public static String encode(JsonNode node) {
        return JacksonUtil.encode(node, false);
    }

    /**
     * Encode a JSON object into its string form.
     *
     * @param node JSON node
     * @param prettyPrint true to pretty print
     * @return equivalent JSON text, or null if {@code node} is null
     * @throws IllegalArgumentException if {@code node} could not be serialized for some reason
     */
    public static String encode(JsonNode node, boolean prettyPrint) {
        if (node == null)
            return null;
        final ObjectMapper mapper = new ObjectMapper(JSON_FACTORY);
        StringWriter buf = new StringWriter();
        try {
            if (prettyPrint)
                mapper.writerWithDefaultPrettyPrinter().writeValue(buf, node);
            else
                mapper.writeValue(buf, node);
        } catch (Exception e) {
            throw new IllegalArgumentException("could not encode JSON: " + e.getMessage(), e);
        }
        return buf.toString();
    }
}
