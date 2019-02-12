package com.github.framework.evo.auth.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2019-01-04 14:26
 */
@Data
public class VerifyCodeParam implements Serializable {
	@NotBlank
	private String mobileNumber;
	private String imei;
}
