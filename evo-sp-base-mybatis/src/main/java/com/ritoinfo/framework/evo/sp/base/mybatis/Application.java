package com.ritoinfo.framework.evo.sp.base.mybatis;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * User: Kyll
 * Date: 2017-11-27 11:29
 */
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		new SpringApplicationBuilder(com.ritoinfo.framework.evo.sp.base.Application.class).web(true).run(args);
	}
}
