package com.ritoinfo.framework.evo.data.redis.config;

import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.data.redis.annotation.RedisKey;
import com.ritoinfo.framework.evo.data.redis.exception.RedisOperateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;
import java.util.Arrays;

/**
 * User: Kyll
 * Date: 2018-03-04 18:50
 */
@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Bean
	public RedisTemplate<String, Serializable> redisTemplate() {
		RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);

		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Override
	public CacheManager cacheManager() {
		return RedisCacheManager.builder(redisConnectionFactory)
				.cacheDefaults(
						RedisCacheConfiguration.defaultCacheConfig()
								.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
								.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
								.disableKeyPrefix()
								.entryTtl(Duration.ofHours(1)))
				.build();
	}

	@Override
	public KeyGenerator keyGenerator() {
		return (o, method, params) -> {
			CacheConfig cacheConfig = method.getAnnotation(CacheConfig.class);
			if (cacheConfig == null) {
				cacheConfig = o.getClass().getAnnotation(CacheConfig.class);
				if (cacheConfig == null) {
					throw new RedisOperateException("缺少业务标识");
				}
			}

			if (cacheConfig.cacheNames().length == 0) {
				throw new RedisOperateException("缺少业务标识");
			}

			if (params.length == 0) {
				throw new RedisOperateException("缺少作为Key的参数");
			}

			Object keyParam = null;
			RedisKey redisKey = null;
			if (params.length == 1) {
				keyParam = params[0];
				redisKey = BeanUtil.getAnnotation(method, 0, RedisKey.class);
			} else {
				for (int i = 0; i < params.length; i++) {
					redisKey = BeanUtil.getAnnotation(method, i, RedisKey.class);
					if (redisKey != null) {
						keyParam = params[i];
						break;
					}
				}
			}

			if (keyParam == null) {
				throw new RedisOperateException("缺少RedisKey注解参数或者参数值为空");
			}

			String key;
			if (redisKey == null) {
				key = keyParam.toString();

				if (StringUtil.isBlank(key)) {
					throw new RedisOperateException("作为key的参数值为空");
				}
			} else {
				String[] fieldNames = redisKey.value();
				if (fieldNames.length == 0) {
					key = keyParam.toString();

					if (StringUtil.isBlank(key)) {
						throw new RedisOperateException("作为key的参数值为空");
					}
				} else {
					Arrays.sort(fieldNames);

					StringBuilder sb = new StringBuilder();
					for (String fieldName : fieldNames) {
						if (StringUtil.isBlank(fieldName)) {
							throw new RedisOperateException("作为key的属性名为空");
						} else {
							Object keyValueObject = BeanUtil.getFieldValue(keyParam, fieldName);
							if (keyValueObject == null) {
								throw new RedisOperateException("作为key的属性值为空");
							} else {
								String keyValue = keyValueObject.toString();
								if (StringUtil.isBlank(keyValue)) {
									throw new RedisOperateException("作为key的属性值为空");
								} else {
									sb.append(keyValue).append("_");
								}
							}
						}
					}

					key = sb.substring(0, sb.length() - 1);
				}
			}

			return RedisKeyGenerator.generate(o, cacheConfig.cacheNames()[0], key);
		};
	}
}
