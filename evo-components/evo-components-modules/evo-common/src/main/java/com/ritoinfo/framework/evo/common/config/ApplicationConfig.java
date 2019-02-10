package com.ritoinfo.framework.evo.common.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2019-02-09 20:41
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ApplicationConfig {
	private String applicationName;
}
