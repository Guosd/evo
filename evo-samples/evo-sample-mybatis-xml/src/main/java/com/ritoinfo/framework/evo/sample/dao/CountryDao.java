package com.ritoinfo.framework.evo.sample.dao;

import com.ritoinfo.framework.evo.base.dao.BaseXmlDao;
import com.ritoinfo.framework.evo.sample.entity.Country;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-07-12 13:12
 */
@Mapper
public interface CountryDao extends BaseXmlDao<Country, Long> {
	List<Map<String, Object>> selectDemo();
}
