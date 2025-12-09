
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.model;

import com.example.util.StringUtil;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Support super-class for persistent model classes.
 */
@MappedSuperclass
public abstract class AbstractPersistent implements HasDatabaseId {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private long id;
    private Date createTime;
    private Date updateTime;

    protected AbstractPersistent() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Override
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the time that this persistent instance was first persisted.
     */
    @Column(nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Get the time that this persistent instance was most recently updated.
     */
    @Column(nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Get a description of this instance suitable for the event log.
     *
     * <p>
     * The implementation in {@link AbstractPersistent} builds a string using the {@link #logType} and {@link #logId}.
     */
    @Transient
    public String getLogDescription() {
        return this.logType() + " " + this.logId();
    }

    /**
     * Used by {@link #getLogDescription} to determine the type of this instance.
     *
     * <p>
     * The implementation in {@link AbstractPersistent} returns the class simple name lowercased.
     *
     * @return a string describing this type of thing (e.g., "patient")
     */
    protected String logType() {
        return StringUtil.lower(this.getClass().getSimpleName());
    }

    /**
     * Used by {@link #getLogDescription} to determine the identifier for this instance.
     *
     * <p>
     * The implementation in {@link AbstractPersistent} returns {@code #NNN} where {@code NNN} is
     * the {@linkplain #getId persistent ID}.
     *
     * @return a string containing the identifier for this type of thing (e.g., patient ID), or null for none
     */
    protected String logId() {
        return "#" + this.getId();
    }

    /**
     * Load in lazily-loaded collections, if any.
     */
    public void loadLazyCollections() {
    }

// Object

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "#" + this.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        final AbstractPersistent that = (AbstractPersistent)obj;
        return this.id != 0 && this.id == that.id;
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode() ^ Long.hashCode(this.id);
    }

// Change management and notifications

    @PrePersist
    public void prePersist() {

        // Set create time
        Date now = new Date();
        this.createTime = now;
        this.updateTime = now;
    }

    @PreRemove
    @PreUpdate
    public void preUpdate() {

        // Set update time
        this.updateTime = new Date();
    }
}
