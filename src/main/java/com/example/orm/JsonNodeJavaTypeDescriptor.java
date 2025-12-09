
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.orm;

import com.example.util.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;

import org.hibernate.SharedSessionContract;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractJavaType;
import org.hibernate.type.descriptor.java.MutabilityPlan;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.jdbc.JdbcTypeIndicators;
import org.hibernate.type.descriptor.jdbc.JsonAsStringJdbcType;

/**
 * Hibernate Java type descriptor thingie for {@link JsonNode} values.
 */
@SuppressWarnings("serial")
public class JsonNodeJavaTypeDescriptor extends AbstractJavaType<JsonNode> {

    public JsonNodeJavaTypeDescriptor() {
        super(JsonNode.class, new JsonNodeMutabilityPlan());
    }

    @Override
    public JdbcType getRecommendedJdbcType(JdbcTypeIndicators context) {
        return JsonAsStringJdbcType.VARCHAR_INSTANCE;
    }

    @Override
    public String toString(JsonNode value) {
        return value != null ? JacksonUtil.encode(value) : "null";
    }

    @Override
    public JsonNode fromString(CharSequence value) {
        return value != null ? JacksonUtil.parse(value.toString()) : null;
    }

    @Override
    public <X> JsonNode wrap(X value, WrapperOptions options) {
        return this.fromString((String)value);
    }

    @Override
    public <X> X unwrap(JsonNode value, Class<X> type, WrapperOptions options) {
        return type.cast(this.toString(value));
    }

// JsonNodeMutabilityPlan

    @SuppressWarnings("serial")
    public static class JsonNodeMutabilityPlan implements MutabilityPlan<JsonNode> {

        @Override
        public JsonNode assemble(Serializable cached, SharedSessionContract session) {
            return cached != null ? JacksonUtil.parse((String)cached) : null;
        }

        @Override
        public JsonNode deepCopy(JsonNode value) {
            return value != null ? value.deepCopy() : null;
        }

        @Override
        public String disassemble(JsonNode value, SharedSessionContract session) {
            return value != null ? JacksonUtil.encode(value) : null;
        }

        @Override
        public boolean isMutable() {
            return true;
        }
    }
}
