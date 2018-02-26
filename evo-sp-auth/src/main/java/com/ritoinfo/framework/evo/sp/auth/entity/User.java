package com.ritoinfo.framework.evo.sp.auth.entity;

import com.ritoinfo.framework.evo.sp.base.entity.BaseEntity;
import lombok.Data;

/**
 * User: Kyll
 * Date: 2018-02-09 16:29
 */
@Data
public class User extends BaseEntity<Long> {
	private String username;
	private String password;
}
