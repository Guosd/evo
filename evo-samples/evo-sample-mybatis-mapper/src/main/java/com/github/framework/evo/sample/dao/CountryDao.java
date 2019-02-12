package com.github.framework.evo.sample.dao;

import com.github.framework.evo.sample.entity.Country;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-07-12 13:12
 */
public interface CountryDao extends Mapper<Country> {
	List<Map<String, Object>> selectDemo();
}
