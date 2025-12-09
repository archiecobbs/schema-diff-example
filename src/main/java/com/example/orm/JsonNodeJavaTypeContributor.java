
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.orm;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;

/**
 * Registers custom Java types with Hibernate.
 *
 * <p>
 * This class is named in the service loader file {@code META-INF/services/org.hibernate.boot.model.TypeContributor}.
 */
public class JsonNodeJavaTypeContributor implements TypeContributor {

    @Override
    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        typeContributions.contributeJavaType(new JsonNodeJavaTypeDescriptor());
    }
}
