package com.ritoinfo.framework.evo.zuul.routelocator;

import com.ritoinfo.framework.evo.common.uitl.JsonUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.zuul.routelocator.dao.EvoZuulRouteDao;
import com.ritoinfo.framework.evo.zuul.routelocator.entity.EvoZuulRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-08-21 09:11
 */
@Slf4j
public class DynamicRouteLocator extends DiscoveryClientRouteLocator {
	private EvoZuulRouteDao evoZuulRouteDao;

	public DynamicRouteLocator(String servletPath, DiscoveryClient discovery, ZuulProperties properties, ServiceRouteMapper serviceRouteMapper, ServiceInstance localServiceInstance) {
		super(servletPath, discovery, properties, serviceRouteMapper, localServiceInstance);
	}

	public DynamicRouteLocator(String servletPath, DiscoveryClient discovery, ZuulProperties properties, ServiceRouteMapper serviceRouteMapper, ServiceInstance localServiceInstance, EvoZuulRouteDao evoZuulRouteDao) {
		super(servletPath, discovery, properties, serviceRouteMapper, localServiceInstance);
		this.evoZuulRouteDao = evoZuulRouteDao;
	}

	@Override
	protected LinkedHashMap<String, ZuulProperties.ZuulRoute> locateRoutes() {
		LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap();

		Example example = new Example(EvoZuulRoute.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("enabled", "1");

		List<EvoZuulRoute> evoZuulRouteList = evoZuulRouteDao.selectByExample(example);
		log.info("加载路由表: " + JsonUtil.objectToJson(evoZuulRouteList));

		for (EvoZuulRoute evoZuulRoute : evoZuulRouteList) {
			String path = evoZuulRoute.getPath();
			String retryableValue = evoZuulRoute.getRetryable();
			String sensitiveHeadersValue = evoZuulRoute.getSensitiveHeaders();

			ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute(
					evoZuulRoute.getRouteId(), path, evoZuulRoute.getServiceId(), evoZuulRoute.getUrl(),
					"1".equals(evoZuulRoute.getStripPrefix()),
					StringUtil.isBlank(retryableValue) ? null : "1".equals(retryableValue),
					StringUtil.isBlank(sensitiveHeadersValue) ? null : new HashSet<>(Arrays.asList(sensitiveHeadersValue.split(","))));
			routesMap.put(path, zuulRoute);
		}

		return routesMap;
	}
}
