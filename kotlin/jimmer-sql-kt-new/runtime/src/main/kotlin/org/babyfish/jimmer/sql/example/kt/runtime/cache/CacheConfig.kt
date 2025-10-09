package org.babyfish.jimmer.sql.example.kt.runtime.cache

import com.fasterxml.jackson.databind.ObjectMapper
import org.babyfish.jimmer.meta.ImmutableProp
import org.babyfish.jimmer.meta.ImmutableType
import org.babyfish.jimmer.spring.cache.RedisCacheCreator
import org.babyfish.jimmer.sql.cache.Cache
import org.babyfish.jimmer.sql.cache.CacheFactory
import org.babyfish.jimmer.sql.cache.redisson.RedissonCacheLocker
import org.babyfish.jimmer.sql.cache.redisson.RedissonCacheTracker
import org.babyfish.jimmer.sql.kt.cache.AbstractKCacheFactory
import org.redisson.api.RedissonClient
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.data.redis.connection.RedisConnectionFactory
import java.time.Duration

// -----------------------------
// If you are a beginner, please ignore this class,
// for non-cache mode, this class will never be used.
// -----------------------------
@ConditionalOnProperty("spring.data.redis.host")
@Configuration
class CacheConfig {

    @Bean
    fun cacheFactory(
        redissonClient: RedissonClient,
        connectionFactory: RedisConnectionFactory,
        objectMapper: ObjectMapper
    ): CacheFactory {

        val creator = RedisCacheCreator(connectionFactory, objectMapper)
            .withRemoteDuration(Duration.ofHours(1))
            .withLocalCache(100, Duration.ofMinutes(5))
            .withMultiViewProperties(40, Duration.ofMinutes(2), Duration.ofMinutes(24))
            .withSoftLock( // Optional, for better consistency
                RedissonCacheLocker(redissonClient),
                Duration.ofSeconds(30)
            )
            .withTracking( // Optional, for application cluster
                RedissonCacheTracker(redissonClient)
            )

        return object : AbstractKCacheFactory() {

            // Id -> Object
            override fun createObjectCache(type: ImmutableType): Cache<*, *>? =
                creator.createForObject<Any, Any>(type)

            // Id -> TargetId, for one-to-one/many-to-one
            override fun createAssociatedIdCache(prop: ImmutableProp): Cache<*, *>? =
                creator.createForProp<Any, Any>(prop, filterState.isAffected(prop.targetType))

            // Id -> TargetId list, for one-to-many/many-to-many
            override fun createAssociatedIdListCache(prop: ImmutableProp): Cache<*, List<*>>? =
                creator.createForProp<Any, List<*>>(prop, filterState.isAffected(prop.targetType))

            // Id -> computed value, for transient properties with resolver
            override fun createResolverCache(prop: ImmutableProp): Cache<*, *>? =
                creator.createForProp<Any, Any>(prop, true)
        }
    }
}
