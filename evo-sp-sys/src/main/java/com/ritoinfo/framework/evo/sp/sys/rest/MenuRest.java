package com.ritoinfo.framework.evo.sp.sys.rest;

import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.sys.bizz.MenuBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.MenuCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.MenuDao;
import com.ritoinfo.framework.evo.sp.sys.dto.MenuDto;
import com.ritoinfo.framework.evo.sp.sys.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-04-23 21:14
 */
@Slf4j
@RequestMapping("menu")
@RestController
public class MenuRest extends BaseRest<MenuBizz, MenuDao, Menu, Long, MenuCondition, MenuDto> {
}
