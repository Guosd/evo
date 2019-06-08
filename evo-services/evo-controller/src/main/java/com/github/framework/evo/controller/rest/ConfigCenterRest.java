package com.github.framework.evo.controller.rest;

import com.github.framework.evo.common.validate.group.PageGroup;
import com.github.framework.evo.controller.bizz.ConfigCenterBizz;
import com.github.framework.evo.controller.model.ConfigInfoDto;
import com.github.framework.evo.controller.model.ConfigItemQuery;
import com.github.framework.evo.controller.model.ConfigPropertyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ConfigInfoDto findPage(@Validated(PageGroup.class) @RequestBody ConfigItemQuery query) {
		return configCenterBizz.findPage(query);
	}

	@PutMapping("/config-item/{application}/{profile}/{label}/{key}")
	public void updateConfigProperty(@PathVariable("application") String application, @PathVariable("profile") String profile, @PathVariable("label") String label, @PathVariable("key") String key, @RequestBody ConfigPropertyDto configPropertyDto) {
		configCenterBizz.updateConfigProperty(application, profile, label, key, configPropertyDto);
	}

	@PostMapping("/config-item/refresh/{destination}")
	public void refreshConfigProperty(@PathVariable("destination") String destination) {
		configCenterBizz.refreshConfigProperty(destination);
	}
}
