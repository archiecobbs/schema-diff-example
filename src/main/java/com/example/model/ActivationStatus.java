
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.model;

import java.util.EnumSet;

/**
 * Encodes the system activation status of a patient.
 */
public enum ActivationStatus {

    /**
     * This patient has not been activated yet. This is the default state.
     */
    PENDING("Pending"),

    /**
     * Patient has been activated.
     */
    ACTIVATED("Activated"),

    /**
     * Patient activation has been blocked.
     */
    BLOCKED("Blocked");

    private final String description;

    ActivationStatus(String description) {
        this.description = description;
    }

    public static EnumSet<ActivationStatus> newEnumSet() {
        return EnumSet.noneOf(ActivationStatus.class);
    }

// Object

    @Override
    public String toString() {
        return this.description;
    }
}
