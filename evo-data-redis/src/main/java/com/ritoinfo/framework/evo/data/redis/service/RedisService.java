package com.ritoinfo.framework.evo.data.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * User: Kyll
 * Date: 2018-03-04 18:51
 */
@Slf4j
@Service
public class RedisService {
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	public Serializable get(Serializable key) {
		return redisTemplate.opsForValue().get(key);
	}

	public String getString(Serializable key) {
		return (String) get(key);
	}

	public void add(Serializable key, Serializable value, Date expire) {
		redisTemplate.opsForValue().set(key, value);
		redisTemplate.expire(key, expire.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	public void delete(Serializable key) {
		redisTemplate.delete(key);
	}

	public boolean exists(Serializable key) {
		return redisTemplate.hasKey(key);
	}
}
