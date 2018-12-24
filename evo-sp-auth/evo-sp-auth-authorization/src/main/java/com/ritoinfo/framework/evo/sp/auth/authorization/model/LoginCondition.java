package com.ritoinfo.framework.evo.sp.auth.authorization.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-12-20 11:41
 */
@Builder
@Data
public class LoginCondition implements Serializable {
	private String username;
	private String mobileNumber;
}
