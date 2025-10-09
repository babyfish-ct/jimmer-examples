package org.babyfish.jimmer.sql.example.kt.runtime.cache.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(value = "spring.data.redis")
data class RedisProperties(
    val host: String,
    val port: Int,
    val password: String? = null,
    val database: Int
)