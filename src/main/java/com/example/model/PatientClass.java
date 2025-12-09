
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.model;

import java.util.EnumSet;

/**
 * Possible values for {@linkplain Visit#getPatientClass patient class}.
 */
public enum PatientClass {
    UNKNOWN("Unknown"),
    INPATIENT("Inpatient"),
    OUTPATIENT("Outpatient"),
    EMERGENCY("Emergency"),
    PRE_ADMIT("Pre-Admit");

    private final String label;

    PatientClass(String label) {
        this.label = label;
    }

    public static EnumSet<PatientClass> newEnumSet() {
        return EnumSet.noneOf(PatientClass.class);
    }

    @Override
    public String toString() {
        return this.label;
    }
}
