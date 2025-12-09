
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Contains a person's demographic information.
 */
@Embeddable
public class Demographics {

    public static final String SEX_PATTERN = "[MFU]";
    public static final String ZIP_PATTERN = "^[0-9]{5}(-[0-9]{4})?$";

    private LocalDate birthDate;
    private String sex;
    private String zip;

    /**
     * Get the person's birthdate.
     */
    public LocalDate getBirthDate() {
        return this.birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Get the person's sex (single uppercase character).
     */
    @Column(length = 1)
    @Pattern(regexp = SEX_PATTERN)
    @Size(max = 1)
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Get the person's ZIP.
     */
    @Column(length = 16)
    @Pattern(regexp = ZIP_PATTERN)
    @Size(max = 16)
    public String getZIP() {
        return this.zip;
    }
    public void setZIP(String zip) {
        this.zip = zip;
    }
}
