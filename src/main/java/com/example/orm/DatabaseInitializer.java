
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.orm;

import com.google.common.base.Preconditions;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dellroad.stuff.schema.SQLCommandList;
import org.dellroad.stuff.spring.AbstractBean;

/**
 * Wraps a {@link DataSource} and runs a sequence of commands on first startup.
 */
public class DatabaseInitializer extends AbstractBean {

    private DataSource dataSource;
    private SQLCommandList setup;
    private SQLCommandList functions;

// Properties

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setSetup(SQLCommandList setup) {
        this.setup = setup;
    }

    public void setFunctions(SQLCommandList functions) {
        this.functions = functions;
    }

// Initialization

    @Override
    public void afterPropertiesSet() throws SQLException {
        Preconditions.checkState(this.dataSource != null, "no dataSource");
        Preconditions.checkState(this.setup != null, "no setup");
        Preconditions.checkState(this.functions != null, "no functions");
        this.log.info("{}: applying database initialization scripts", this.getClass().getSimpleName());
        try (Connection connection = this.dataSource.getConnection()) {
            this.setup.apply(connection);
            this.functions.apply(connection);
        }
    }
}
