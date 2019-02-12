package com.ritoinfo.framework.evo.auth.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2019-02-11 12:46
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class IamConfig {
	private String serviceId;
}
