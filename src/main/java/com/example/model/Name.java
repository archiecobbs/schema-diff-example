
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.model;

import com.example.util.StringUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Contains a person's name.
 */
@Embeddable
public class Name implements Cloneable, Comparable<Name> {

    private String lastName;
    private String firstName;
    private String middleName;

    public Name() {
    }

    /**
     * Get the person's last name.
     */
    @Column(length = 255)
    @Size(max = 255)
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName != null ? StringUtil.upper(lastName) : null;
    }

    /**
     * Get the person's first name.
     */
    @Column(length = 255)
    @Size(max = 255)
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName != null ? StringUtil.upper(firstName) : null;
    }

    /**
     * Get the person's middle name.
     */
    @Column(length = 255)
    @Size(max = 255)
    public String getMiddleName() {
        return this.middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName != null ? StringUtil.upper(middleName) : null;
    }

    /**
     * Is this name all empty?
     */
    @Transient
    public boolean isEmpty() {
        return this.equals(new Name());
    }

    /**
     * Get person's full name.
     */
    @Transient
    public String getFullName() {
        return StringUtil.getFullName(this.lastName, this.firstName, this.middleName);
    }

    /**
     * Get person's "safe" name, which is the patient's name in partial form to keep them from being identifiable.
     */
    @Transient
    public String getSafeName() {
        return StringUtil.getSafeName(this.lastName, this.firstName, this.middleName);
    }

    /**
     * Update this instance using the information.
     */
    public void updateName(String lastName, String firstName, String middleName) {
        if (lastName != null || this.lastName == null)
            this.setLastName(lastName);
        if (firstName != null || this.firstName == null)
            this.setFirstName(firstName);
        if (middleName != null || this.middleName == null)
            this.setMiddleName(middleName);
    }

// Object

    @Override
    public String toString() {
        return this.getFullName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Name))
            return false;
        final Name that = (Name)obj;
        return Objects.equals(this.lastName, that.lastName)
          && Objects.equals(this.firstName, that.firstName)
          && Objects.equals(this.middleName, that.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.lastName)
          ^ Objects.hashCode(this.firstName)
          ^ Objects.hashCode(this.middleName);
    }

// Cloneable

    @Override
    public Name clone() {
        try {
            return (Name)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

// Comparable

    /**
     * Sorts by full name.
     */
    @Override
    public int compareTo(Name that) {
        final String thisName = this.getFullName();
        final String thatName = that.getFullName();
        if ((thisName != null) != (thatName != null))
            return thisName != null ? -1 : 1;
        if (thisName != null && thatName != null)
            return thisName.compareTo(thatName);
        return 0;
    }
}
