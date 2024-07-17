package org.babyfish.jimmer.sql.example.runtime.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.babyfish.jimmer.meta.ImmutableProp;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.sql.cache.AbstractCacheFactory;
import org.babyfish.jimmer.sql.cache.Cache;
import org.babyfish.jimmer.sql.cache.CacheCreator;
import org.babyfish.jimmer.sql.cache.CacheFactory;
import org.babyfish.jimmer.sql.cache.chain.*;
import org.babyfish.jimmer.sql.cache.redis.spring.RedisCacheCreator;
import org.babyfish.jimmer.sql.cache.redisson.RedissonCacheLocker;
import org.babyfish.jimmer.sql.cache.redisson.RedissonCacheTracker;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.List;

// -----------------------------
// If you are a beginner, please ignore this class,
// for non-cache mode, this class will never be used.
// -----------------------------
@ConditionalOnProperty("spring.redis.host")
@Configuration
public class CacheConfig {

    @Bean
    public CacheFactory cacheFactory(
            RedissonClient redissonClient,
            RedisConnectionFactory connectionFactory,
            ObjectMapper objectMapper
    ) {
        CacheCreator creator = new RedisCacheCreator(connectionFactory, objectMapper)
                .withRemoteDuration(Duration.ofHours(1))
                .withLocalCache(100, Duration.ofMinutes(5))
                .withMultiViewProperties(40, Duration.ofMinutes(2), Duration.ofMinutes(24))
                .withSoftLock( // Optional, for better consistency
                        new RedissonCacheLocker(redissonClient),
                        Duration.ofSeconds(30)
                )
                .withTracking( // Optional, for application cluster
                        new RedissonCacheTracker(redissonClient)
                );

        return new AbstractCacheFactory() {

            // Id -> Object
            @Override
            public Cache<?, ?> createObjectCache(ImmutableType type) {
                return creator.createForObject(type);
            }

            // Id -> TargetId, for one-to-one/many-to-one
            @Override
            public Cache<?, ?> createAssociatedIdCache(ImmutableProp prop) {
                return creator.createForProp(prop, getFilterState().isAffected(prop.getTargetType()));
            }

            // Id -> TargetId list, for one-to-many/many-to-many
            @Override
            public Cache<?, List<?>> createAssociatedIdListCache(ImmutableProp prop) {
                return creator.createForProp(prop, getFilterState().isAffected(prop.getTargetType()));
            }

            // Id -> computed value, for transient properties with resolver
            @Override
            public Cache<?, ?> createResolverCache(ImmutableProp prop) {
                return creator.createForProp(prop, true);
            }
        };
    }
}
