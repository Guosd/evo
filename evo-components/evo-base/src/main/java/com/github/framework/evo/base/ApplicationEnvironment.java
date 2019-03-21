package com.github.framework.evo.base;

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
public class ApplicationEnvironment {
	private String applicationName;
	private boolean exceptionEnabled;
}
