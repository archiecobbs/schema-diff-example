
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.orm;

import com.example.util.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * JPA {@link Converter} between Jackson's {@link JsonNode} and {@link String}.
 */
@Converter
public class JsonNodeAttributeConverter implements AttributeConverter<JsonNode, String> {

    @Override
    public String convertToDatabaseColumn(JsonNode node) {
        return JacksonUtil.encode(node);
    }

    @Override
    public JsonNode convertToEntityAttribute(String value) {
        return JacksonUtil.parse(value);
    }
}
