package com.github.framework.evo.activiti.proxy.entity;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2018-06-08 15:53
 */
@Data
public class UserProxy {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Boolean pictureSet;
}
