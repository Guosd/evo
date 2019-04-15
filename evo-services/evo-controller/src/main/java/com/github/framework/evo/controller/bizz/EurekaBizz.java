package com.github.framework.evo.controller.bizz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2019-04-15 16:08
 */
@Service
public class EurekaBizz {
	@Autowired
	private DiscoveryClient discoveryClient;

	public void list() {
		System.out.println(discoveryClient.getServices());
	}
}
