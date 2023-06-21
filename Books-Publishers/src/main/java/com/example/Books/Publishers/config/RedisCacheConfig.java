package com.example.Books.Publishers.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableCaching
public class RedisCacheConfig {
	
	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
	   return RedisCacheConfiguration.defaultCacheConfig()
	   .entryTtl(Duration.ofMinutes(60)) // Changing the cache TTL
	   .disableCachingNullValues()
	   .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); 
	   //Defining serialization to retrieve data
	}

}
