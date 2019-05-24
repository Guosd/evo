package com.github.framework.evo.controller.bizz;

import com.github.framework.evo.base.assist.BaseHelper;
import com.github.framework.evo.base.bizz.BaseJpaBizz;
import com.github.framework.evo.common.model.PageList;
import com.github.framework.evo.controller.dao.ConfigPropertyDao;
import com.github.framework.evo.controller.entity.ConfigProperty;
import com.github.framework.evo.controller.model.ConfigInfoDto;
import com.github.framework.evo.controller.model.ConfigItemDto;
import com.github.framework.evo.controller.model.ConfigItemQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * User: Kyll
 * Date: 2019-05-24 13:09
 */
@Slf4j
@Service
public class ConfigCenterBizz extends BaseJpaBizz<ConfigPropertyDao, ConfigProperty, Long, ConfigItemDto> {
	public ConfigInfoDto findPage(ConfigItemQuery query) {
		List<ConfigProperty> configPropertyList = dao.findAll(Sort.by(Sort.Direction.ASC, "label", "application", "profile", "key"));

		Set<String> profileSet = new LinkedHashSet<>();// 设置环境列 default, 环境1, 环境2...
		profileSet.add("default");

		Map<String, ConfigItemDto> configItemDtoMap = new TreeMap<>();// 设置配置属性关联的属性值
		for (ConfigProperty configProperty : configPropertyList) {
			profileSet.add(configProperty.getProfile());

			String key = configProperty.getKey();
			String profile = configProperty.getProfile();

			ConfigItemDto configItemDto = configItemDtoMap.get(key);
			if (configItemDto == null) {
				configItemDto = new ConfigItemDto();
				configItemDto.setLabel(configProperty.getLabel());
				configItemDto.setApplication(configProperty.getApplication());
				configItemDto.setKey(key);
				configItemDto.setValueMap(new HashMap<>());
				configItemDto.setComment(configItemDto.getComment());
				configItemDto.setCreateTime(configItemDto.getCreateTime());
				configItemDto.setUpdateTime(configItemDto.getUpdateTime());
				configItemDtoMap.put(key, configItemDto);
			}

			configItemDto.getValueMap().put(profile, configProperty.getValue());
		}

		int total = configItemDtoMap.size();
		int pageSize = query.getPageSize();
		int start = query.getPageNo() * pageSize;
		int end = start + pageSize;
		if (end > total) {
			end = total;
		}

		PageList<ConfigItemDto> pageList = new PageList<>();
		BaseHelper.copyPage(pageList, total, query, new ArrayList<>(configItemDtoMap.values()).subList(start, end));

		ConfigInfoDto configInfoDto = new ConfigInfoDto();
		configInfoDto.setProfiles(profileSet.toArray(new String[0]));
		configInfoDto.setConfigItemList(pageList);
		return configInfoDto;
	}
}
