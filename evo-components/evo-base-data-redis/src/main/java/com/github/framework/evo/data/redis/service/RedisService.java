package com.github.framework.evo.data.redis.service;

import com.github.framework.evo.common.uitl.StringUtil;
import com.github.framework.evo.data.redis.exception.RedisOperateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
	private RedisTemplate<String, Serializable> redisTemplate;

	public void set(String key, Serializable value) {
		try {
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			throw new RedisOperateException("写入失败", e);
		}
	}

	public void set(String key, Serializable value, Date expire) {
		set(key, value, expire.getTime() - System.currentTimeMillis());
	}

	public boolean set(String key, Serializable value, Long expire) {
		log.info("设置redis缓存: " + key + ", " + value + ", " + expire);

		Boolean result;
		try {
			redisTemplate.opsForValue().set(key, value);
			result = redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			throw new RedisOperateException("写入并设置超时失败", e);
		}
		return result == null ? false : result;
	}

	public Serializable get(String key) {
		log.info("获取redis缓存: " + key);

		Serializable value;
		try {
			value = redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			throw new RedisOperateException("读取失败", e);
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	public <T> T getRealObject(String key) {
		return (T) get(key);
	}

	public String getString(String key) {
		Serializable value = get(key);
		return value == null ? null : value.toString();
	}

	public Long getLong(String key) {
		String value = getString(key);
		return StringUtil.isBlank(value) ? null : Long.parseLong(value);
	}

	public Integer getInteger(String key) {
		String value = getString(key);
		return StringUtil.isBlank(value) ? null : Integer.parseInt(value);
	}

	public BigDecimal getBigDecimal(String key) {
		String value = getString(key);
		return StringUtil.isBlank(value) ? null : new BigDecimal(value);
	}

	public Double getDouble(String key) {
		BigDecimal value = getBigDecimal(key);
		return value == null ? null : value.doubleValue();
	}

	public Float getFloat(String key) {
		BigDecimal value = getBigDecimal(key);
		return value == null ? null : value.floatValue();
	}

	public boolean delete(String key) {
		log.info("删除redis缓存: " + key);

		Boolean result;
		try {
			result = redisTemplate.delete(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result == null ? false : result;
	}

	public Long delete(Collection<String> keys) {
		Long result;
		try {
			result = redisTemplate.delete(keys);
		} catch (Exception e) {
			throw new RedisOperateException("删除失败", e);
		}
		return result;
	}

	public boolean exist(String key) {
		Boolean result;
		try {
			result = redisTemplate.hasKey(key);
		} catch (Exception e) {
			throw new RedisOperateException("判断是否存在失败", e);
		}
		return result == null ? false : result;
	}
}
