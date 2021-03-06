package com.github.framework.evo.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-12-28 18:36
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RbacDto implements Serializable {
	private String userId;
	private String method;
	private String uri;
}
