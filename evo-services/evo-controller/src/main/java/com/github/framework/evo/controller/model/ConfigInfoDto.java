package com.github.framework.evo.controller.model;

import com.github.framework.evo.common.model.PageList;
import lombok.Data;

/**
 * User: Kyll
 * Date: 2019-05-24 14:48
 */
@Data
public class ConfigInfoDto {
	private String[] profiles;
	private PageList<ConfigItemDto> configItemList;
}
