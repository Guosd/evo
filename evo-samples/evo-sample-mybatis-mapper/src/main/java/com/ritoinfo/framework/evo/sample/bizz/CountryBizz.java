package com.ritoinfo.framework.evo.sample.bizz;

import com.ritoinfo.framework.evo.base.bizz.BaseMapperBizz;
import com.ritoinfo.framework.evo.sample.dao.CountryDao;
import com.ritoinfo.framework.evo.sample.dto.CountryDto;
import com.ritoinfo.framework.evo.sample.entity.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-07-12 13:13
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class CountryBizz extends BaseMapperBizz<CountryDao, Country, Long, CountryDto> {
	public List<Map<String, Object>> selectDemo() {
		return dao.selectDemo();
	}
}
