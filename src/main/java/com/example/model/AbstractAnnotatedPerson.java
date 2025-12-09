
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Information about a person who supports annotations.
 */
@MappedSuperclass
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("A")
public abstract class AbstractAnnotatedPerson extends AbstractAnnotated {

    /**
     * Sorts instances by name.
     */
    public static final Comparator<AbstractAnnotatedPerson> SORT_BY_NAME = Comparator.comparing(AbstractAnnotatedPerson::getName);

    private Name name = new Name();                                         // this field should never be null
    private Demographics demographics = new Demographics();                 // this field should never be null
    private String number;

    AbstractAnnotatedPerson() {
    }

    @SuppressWarnings("this-escape")
    protected AbstractAnnotatedPerson(String lastName, String firstName) {
        this.setLastName(lastName);
        this.setFirstName(firstName);
    }

    /**
     * Update this person's personal info using the information from the given names.
     */
    public void updateName(String lastName, String firstName, String middleName) {
        this.name.updateName(lastName, firstName, middleName);
    }

    /**
     * Get the name of this person.
     */
    @Embedded
    public Name getName() {
        return this.name;
    }
    public void setName(Name name) {
        this.name = name != null ? name : new Name();
    }

    /**
     * Get the demographics of this person.
     */
    @Embedded
    public Demographics getDemographics() {
        return this.demographics;
    }
    public void setDemographics(Demographics demographics) {
        this.demographics = demographics != null ? demographics : new Demographics();
    }

    /**
     * Get this person's cell phone number.
     */
    // NOTE: subclass must override and provide JPA annotations
    @Transient
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    @Transient
    public String getLogDescription() {
        final StringBuilder buf = new StringBuilder(64);
        buf.append(this.logType());
        final String fullName = this.getFullName();
        if (fullName != null)
            buf.append(" \"").append(fullName).append('"');
        final String logId = this.logId();
        if (logId != null)
            buf.append(' ').append(this.logId());
        return buf.toString();
    }

// Convenience methods

    @Transient
    public String getLastName() {
        return this.name != null ? this.name.getLastName() : null;
    }
    public void setLastName(String lastName) {
        this.name.setLastName(lastName);
    }

    @Transient
    public String getFirstName() {
        return this.name != null ? this.name.getFirstName() : null;
    }
    public void setFirstName(String firstName) {
        this.name.setFirstName(firstName);
    }

    @Transient
    public String getMiddleName() {
        return this.name != null ? this.name.getMiddleName() : null;
    }
    public void setMiddleName(String middleName) {
        this.name.setMiddleName(middleName);
    }

    @Transient
    public String getFullName() {
        return this.name != null ? this.name.getFullName() : null;
    }

    @Transient
    public LocalDate getBirthDate() {
        return this.demographics != null ? this.demographics.getBirthDate() : null;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.demographics.setBirthDate(birthDate);
    }

    @Transient
    public String getSex() {
        return this.demographics != null ? this.demographics.getSex() : null;
    }
    public void setSex(String sex) {
        this.demographics.setSex(sex);
    }

    @Transient
    public String getZIP() {
        return this.demographics != null ? this.demographics.getZIP() : null;
    }
    public void setZIP(String zip) {
        this.demographics.setZIP(zip);
    }
}
