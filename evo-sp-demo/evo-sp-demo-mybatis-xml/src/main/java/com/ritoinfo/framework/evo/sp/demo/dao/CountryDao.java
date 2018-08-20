package com.ritoinfo.framework.evo.sp.demo.dao;

import com.ritoinfo.framework.evo.sp.base.starter.dao.BaseXmlDao;
import com.ritoinfo.framework.evo.sp.demo.condition.CountryCondition;
import com.ritoinfo.framework.evo.sp.demo.entity.Country;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-07-12 13:12
 */
@Mapper
public interface CountryDao extends BaseXmlDao<Country, Long, CountryCondition> {
	List<Map<String, Object>> selectDemo();
}
