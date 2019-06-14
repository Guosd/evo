package com.github.framework.evo.controller.model.dockerswarm;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2019-06-14 10:53
 */
@Data
public class PortDto {
	private String protocol;
	private Integer targetPort;
	private Integer publishedPort;
	private String publishMode;
}
