package org.babyfish.jimmer.sql.example.kt.runtime.cache

import org.babyfish.jimmer.sql.example.kt.runtime.cache.properties.RedisProperties
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.core.io.ResourceLoader

@Configuration
@ConditionalOnProperty("spring.data.redis.host")
@EnableConfigurationProperties(RedisProperties::class)
class RedisConfig(
    private val resourceLoader: ResourceLoader,
    private val redisProperties: RedisProperties
) {

    @Bean
    fun redissonClient(): RedissonClient {
        val resource = resourceLoader.getResource(REDISSON_YAML_PATH)
        val config = Config.fromYAML(resource.inputStream)
        config.useSingleServer().apply {
            this.address = "redis://${redisProperties.host}:${redisProperties.port}"
            this.password = redisProperties.password
            this.database = redisProperties.database
        }
        return Redisson.create(config)
    }

    companion object {
        private const val REDISSON_YAML_PATH = "classpath:redisson.yml"
    }


}