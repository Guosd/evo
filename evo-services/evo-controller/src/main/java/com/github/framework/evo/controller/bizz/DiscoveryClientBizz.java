package com.github.framework.evo.controller.bizz;

import com.github.framework.evo.common.uitl.StringUtil;
import com.github.framework.evo.controller.exception.DiscoveryServerException;
import com.github.framework.evo.controller.model.ServiceInstanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2019-04-15 16:51
 */
@Service
public class DiscoveryClientBizz {
	@Autowired
	private DiscoveryClient discoveryClient;
	private static String discoveryServerType;

	public List<ServiceInstanceDto> findServiceInstance() {
		List<ServiceInstanceDto> serviceInstanceDtoList = new ArrayList<>();

		for (String serviceId : discoveryClient.getServices()) {
			for (ServiceInstance serviceInstance : discoveryClient.getInstances(serviceId)) {
				ServiceInstanceDto serviceInstanceDto = new ServiceInstanceDto();
				serviceInstanceDto.setDiscoveryServerType(discoveryServerType);
				serviceInstanceDto.setServiceId(serviceInstance.getServiceId());
				serviceInstanceDto.setInstanceId(serviceInstance.getInstanceId());
				serviceInstanceDto.setHost(serviceInstance.getHost());
				serviceInstanceDto.setPort(serviceInstance.getPort());
				serviceInstanceDto.setUri(serviceInstance.getUri().toString());
				serviceInstanceDto.setSecure(serviceInstance.isSecure());
				serviceInstanceDto.setScheme(serviceInstance.getScheme());
				serviceInstanceDto.setMetadata(serviceInstance.getMetadata());
				serviceInstanceDtoList.add(serviceInstanceDto);
			}
		}

		return serviceInstanceDtoList;
	}

	@PostConstruct
	private void initDiscoveryServerType() {
		if (StringUtil.isBlank(discoveryServerType)) {
			if (discoveryClient instanceof CompositeDiscoveryClient) {
				CompositeDiscoveryClient compositeDiscoveryClient = (CompositeDiscoveryClient) discoveryClient;
				for (DiscoveryClient dc : compositeDiscoveryClient.getDiscoveryClients()) {
					if (dc instanceof EurekaDiscoveryClient) {
						discoveryServerType = "eureka";
						return;
					}
				}
			}
			throw new DiscoveryServerException("无法获取 DiscoveryServer 类型");
		}
	}
}
