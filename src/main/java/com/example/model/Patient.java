
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.model;

import com.example.orm.JsonNodeAttributeConverter;
import com.example.validation.PhoneNumber;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Information about a patient.
 */
@Entity
@DiscriminatorValue("P")
@Table(indexes = {
  @Index(name = "idx_Patient_lastName", columnList = "lastName"),
  @Index(name = "idx_Patient_number", columnList = "number")
})
public class Patient extends AbstractAnnotatedPerson {

    public static final int MAX_PID_LENGTH = 32;

    private Map<PidType, String> pids = new HashMap<>();
    private ActivationStatus activationStatus;

    Patient() {
    }

    public Patient(String lastName, String firstName) {
        super(lastName, firstName);
    }

    @Override
    public void loadLazyCollections() {
        super.loadLazyCollections();
        this.getPids().size();
    }

    @ElementCollection
    @MapKeyColumn(name = "name", length = 180)
    @Column(name = "value", columnDefinition = "mediumtext", nullable = false)
    @CollectionTable(name = "PatientAnnotation",
      joinColumns = @JoinColumn(name = "patient"),
      indexes = @Index(name = "idx_PatientAnnotation_name", columnList = "name"),
      foreignKey = @ForeignKey(name = "FK287AFF4A0F6ED11"))
    @Convert(converter = JsonNodeAttributeConverter.class, attributeName = "value")
    @Override
    public Map<String, JsonNode> getAnnotations() {
        return super.getAnnotations();
    }

    @Column(length = 64)
    @Override
    @PhoneNumber
    @Size(max = 64)
    public String getNumber() {
        return super.getNumber();
    }

    @ElementCollection
    @MapKeyColumn(name = "type", length = PidType.MAX_NAME_LENGTH, nullable = false)
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "pid", length = Patient.MAX_PID_LENGTH, nullable = false)
    @CollectionTable(name = "Pid",
      joinColumns = @JoinColumn(name = "patient", nullable = false),
      uniqueConstraints = @UniqueConstraint(columnNames = { "pid", "type" }))
    @NotNull
    public Map<PidType, String> getPids() {
        return this.pids;
    }
    public void setPids(Map<PidType, String> pids) {
        this.pids = new EnumMap<>(pids);
    }

    @Transient
    public String getFirstPid() {
        return Patient.getFirstPidFrom(this.pids);
    }

    public static String getFirstPidFrom(Map<PidType, String> pids) {
        return !pids.isEmpty() ? new EnumMap<>(pids).values().iterator().next() : null;
    }

    /**
     * Determine patient's activation status.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    public ActivationStatus getActivationStatus() {
        return this.activationStatus;
    }
    public void setActivationStatus(ActivationStatus activationStatus) {
        this.activationStatus = activationStatus;
    }

    @Override
    protected String logId() {
        return this.getFirstPid();
    }
}
