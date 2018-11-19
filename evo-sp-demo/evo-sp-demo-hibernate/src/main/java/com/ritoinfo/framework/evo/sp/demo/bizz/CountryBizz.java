package com.ritoinfo.framework.evo.sp.demo.bizz;

import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseHibernateBizz;
import com.ritoinfo.framework.evo.sp.demo.dao.CountryDao;
import com.ritoinfo.framework.evo.sp.demo.dto.CountryDto;
import com.ritoinfo.framework.evo.sp.demo.entity.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-07-12 13:13
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class CountryBizz extends BaseHibernateBizz<CountryDao, Country, Long, CountryDto> {
	@Transactional
	public List<Country> findByCode(String code) {
		CountryDto cd1 = new CountryDto();
		cd1.setName("27");
		cd1.setCode("27");
		Long id = this.create(cd1);

		CountryDto cd2 = new CountryDto();
		cd2.setId(id);
		cd2.setName("277");
		cd2.setCode("277");
		this.update(cd2);

		return dao.findByHql("from Country t where t.code = ?0", code);
	}
}
