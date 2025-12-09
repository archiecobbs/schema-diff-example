
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.util;

import com.google.common.base.Preconditions;

import java.util.Locale;

public final class StringUtil {

    private StringUtil() {
    }

    /**
     * Convert to upper case in a locale-independent way.
     *
     * @param string
     * @return upper case {@code string}
     * @throws IllegalArgumentException if {@code string} is null
     */
    public static String upper(String string) {
        Preconditions.checkArgument(string != null, "null string");
        return string.toUpperCase(Locale.ROOT);
    }

    /**
     * Convert to lower case in a locale-independent way.
     *
     * @param string
     * @return lower case {@code string}
     * @throws IllegalArgumentException if {@code string} is null
     */
    public static String lower(String string) {
        Preconditions.checkArgument(string != null, "null string");
        return string.toLowerCase(Locale.ROOT);
    }

    /**
     * Get the full name of a person, or as much of it as is available, for display purposes
     * formatted like <code>LAST, FIRST MIDDLE</code>.
     *
     * @param lastName last name, possibly null
     * @param firstName first name, possibly null
     * @param middleName middle name, possibly null
     * @return full name or null if this person has no name at all
     */
    public static String getFullName(String lastName, String firstName, String middleName) {
        final boolean hasLastName = lastName != null && lastName.length() > 0;
        final boolean hasFirstName = firstName != null && firstName.length() > 0;
        final boolean hasMiddleName = middleName != null && middleName.length() > 0;
        String firstPart = hasFirstName ? firstName + (!hasMiddleName ? "" : " " + middleName) : null;
        String fullName = hasLastName ? lastName + (firstPart != null ? ", " + firstPart : "") : firstPart;
        return fullName;
    }

    /**
     * Get the "safe" name of a person, or as much of it as is available, for display purposes
     * formatted like <code>FIRST L.</code>.
     *
     * @param lastName last name, possibly null
     * @param firstName first name, possibly null
     * @param middleName middle name, possibly null
     * @return safe name or null if this person has no name at all
     */
    public static String getSafeName(String lastName, String firstName, String middleName) {
        final boolean hasLastName = lastName != null && lastName.length() > 0;
        final boolean hasFirstName = firstName != null && firstName.length() > 0;
        final boolean hasMiddleName = middleName != null && middleName.length() > 0;
        if (hasFirstName)
            return hasLastName ? firstName + " " + lastName.substring(0, 1) + "." : firstName;
        if (hasMiddleName)
            return hasLastName ? middleName + " " + lastName.substring(0, 1) + "." : middleName;
        if (hasLastName)
            return lastName.substring(0, 1) + ".";
        return null;
    }
}
