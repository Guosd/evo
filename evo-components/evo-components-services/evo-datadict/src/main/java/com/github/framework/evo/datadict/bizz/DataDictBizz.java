package com.github.framework.evo.datadict.bizz;

import com.github.framework.evo.data.redis.annotation.RedisKey;
import com.github.framework.evo.base.bizz.BaseXmlBizz;
import com.github.framework.evo.datadict.condition.DataDictCondition;
import com.github.framework.evo.datadict.dao.DataDictDao;
import com.github.framework.evo.datadict.dto.DataDictDto;
import com.github.framework.evo.datadict.entity.DataDict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-13 09:56
 */
@CacheConfig(cacheNames = "DataDict")
@Slf4j
@Transactional(readOnly = true)
@Service
public class DataDictBizz extends BaseXmlBizz<DataDictDao, DataDict, Long, DataDictDto> {
	@Cacheable
	public List<DataDictDto> findByCode(String code) {
		return super.find(DataDictCondition.builder().code(code).build());
	}

	public DataDictDto getByCodeAndKey(DataDictCondition condition) {
		List<DataDictDto> list = find(condition);

		DataDictDto dataDictDto = null;
		for (DataDictDto ddd : list) {
			if (condition.getCode().equals(ddd.getCode()) && condition.getKey().equals(ddd.getKey())) {
				dataDictDto = ddd;
				break;
			}
		}

		return dataDictDto;
	}

	@CacheEvict
	@Transactional
	@Override
	public Long create(@RedisKey("code") DataDictDto dto) {
		return super.create(dto);
	}

	@CacheEvict
	@Transactional
	@Override
	public void update(@RedisKey("code") DataDictDto dto) {
		super.update(dto);
	}

	@CacheEvict
	@Transactional
	public void delete(Long id, @RedisKey("code") String code) {
		super.delete(id);
	}
}
