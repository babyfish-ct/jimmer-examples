package org.babyfish.jimmer.example.save.model

import org.babyfish.jimmer.sql.runtime.EntityManager

private class JimmerModule

/**
 * Under normal circumstances, users do not need to use this code. 
 * This code is for compatibility with version 0.7.47 and earlier.
 */
public val ENTITY_MANAGER: EntityManager = EntityManager.fromResources(
            JimmerModule::class.java.classLoader
        ) {
            it.name.startsWith("org.babyfish.jimmer.example.save.model.")
        }

