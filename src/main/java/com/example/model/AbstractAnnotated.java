
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.model;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.metamodel.MapAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * Support superclass for annotated entity types.
 */
public abstract class AbstractAnnotated extends AbstractPersistent {

    private static final Map<Class<? extends AbstractAnnotated>, MapAttribute<?, String, JsonNode>>
      ANNOTATIONS_ATTRIBUTE_MAP = new HashMap<>();

    private Map<String, JsonNode> annotations = new HashMap<>();

    protected AbstractAnnotated() {
    }

    /**
     * Get annotation map for this instance.
     */
    // NOTE: subclass must override and provide JPA annotations
    public Map<String, JsonNode> getAnnotations() {
        return this.annotations;
    }
    public void setAnnotations(Map<String, JsonNode> annotations) {
        this.annotations = annotations;
    }

    @Override
    public void loadLazyCollections() {
        super.loadLazyCollections();
        this.getAnnotations().size();
    }
}
