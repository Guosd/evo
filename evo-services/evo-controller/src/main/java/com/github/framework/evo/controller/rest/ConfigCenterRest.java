package com.github.framework.evo.controller.rest;

import com.github.framework.evo.controller.bizz.ConfigCenterBizz;
import com.github.framework.evo.controller.model.ConfigInfoDto;
import com.github.framework.evo.controller.model.ConfigItemQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2019-05-24 13:07
 */
@Slf4j
@RequestMapping("/config-center")
@RestController
public class ConfigCenterRest {
	@Autowired
	private ConfigCenterBizz configCenterBizz;

	@PostMapping("/config-item/page")
	public ConfigInfoDto findPage(@RequestBody ConfigItemQuery query) {
		return configCenterBizz.findPage(query);
	}
}
