
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.model;

public final class ColumnLength {

    // (2^24 - 1) <MEDIUMTEXT maximum #bytes> / 4 <MB4 encoding> - 3 <MEDIUMTEXT length prefix>
    public static final int MEDIUMTEXT = (((1 << 24) - 1) / 4) - 3;

    private ColumnLength() {
    }
}
