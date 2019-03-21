package com.github.framework.evo.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2018-04-15 16:39
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PermissionParam {
	@NotBlank
	private String uri;
	@NotBlank
	private String method;
	@NotBlank
	private String token;
}
