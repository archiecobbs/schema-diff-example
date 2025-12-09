
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.model;

import com.google.common.base.Preconditions;

import java.util.EnumMap;

/**
 * Types of patient ID's.
 *
 * <p>
 * For now, instances are ordered by priority high to low: high priority ID's are more authoritative/sticky
 * than lower priority ones.
 */
public enum PidType {

    /**
     * Master Patient Index (MPI).
     */
    MPI("MPI"),

    /**
     * Epic ID.
     */
    EPIC("Epic"),

    /**
     * Cerner ID.
     */
    CERNER("Cerner"),

    /**
     * Temporary ID pending assignment of a real ID (e.g., emergency room visit).
     */
    TEMP("Temporary");

    public static final int MAX_NAME_LENGTH = 8;

    private final String description;

    PidType(String description) {
        Preconditions.checkArgument(this.name().length() <= MAX_NAME_LENGTH);
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

// Map

    @SuppressWarnings("serial")
    public static class Map<V> extends EnumMap<PidType, V> {

        public Map() {
            super(PidType.class);
        }

        @SuppressWarnings("this-escape")
        public Map(java.util.Map<PidType, V> map) {
            super(PidType.class);
            Preconditions.checkArgument(map != null, "null map");
            this.putAll(map);
        }
    }
}
