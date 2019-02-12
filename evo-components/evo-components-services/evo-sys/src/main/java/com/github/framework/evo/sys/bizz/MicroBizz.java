package com.github.framework.evo.sys.bizz;

import com.github.framework.evo.base.bizz.BaseXmlBizz;
import com.github.framework.evo.sys.dao.MicroDao;
import com.github.framework.evo.sys.dto.FuncDto;
import com.github.framework.evo.sys.dto.MicroDto;
import com.github.framework.evo.sys.entity.Micro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2018-04-23 20:03
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class MicroBizz extends BaseXmlBizz<MicroDao, Micro, Long, MicroDto> {
	@Autowired
	private FuncBizz funcBizz;

	@Transactional
	@Override
	public void delete(Long id) {
		for (FuncDto funcDto : funcBizz.findByMicro(id)) {
			funcBizz.delete(funcDto.getId());
		}

		super.delete(id);
	}
}
