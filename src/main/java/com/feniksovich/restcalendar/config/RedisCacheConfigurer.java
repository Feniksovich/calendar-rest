package com.feniksovich.restcalendar.config;

import com.feniksovich.restcalendar.dto.CalendarDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisCacheConfigurer {

    /**
     * Переопределение сериализаторов для ключей и значений в Redis.
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        final RedisSerializer<CalendarDto> calendarDtoSerializer =
                new Jackson2JsonRedisSerializer<>(CalendarDto.class);

        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(SerializationPair.fromSerializer(calendarDtoSerializer));
    }
}


