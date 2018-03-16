package com.ritoinfo.framework.evo.sp.sys.bizz;

import com.ritoinfo.framework.evo.sp.base.bizz.BaseBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.FuncCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.FuncDao;
import com.ritoinfo.framework.evo.sp.sys.entity.Func;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-04 18:05
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class FuncBizz extends BaseBizz<FuncDao, Func, Long, FuncCondition> {
	public List<Func> getByUsername(String username) {
		return dao.getByUsername(username);
	}
}
