package com.ritoinfo.framework.evo.sp.sys.bizz;

import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.MenuCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.MenuDao;
import com.ritoinfo.framework.evo.sp.sys.dto.MenuDto;
import com.ritoinfo.framework.evo.sp.sys.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2018-04-23 21:13
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class MenuBizz extends BaseBizz<MenuDao, Menu, Long, MenuCondition, MenuDto> {
}
