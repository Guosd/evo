package com.github.framework.evo.controller.model.dockerswarm;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2019-06-14 08:56
 */
@Data
public class ManagerStatusDto {
	private Boolean leader;
	private String reachability;
	private String addr;
}
