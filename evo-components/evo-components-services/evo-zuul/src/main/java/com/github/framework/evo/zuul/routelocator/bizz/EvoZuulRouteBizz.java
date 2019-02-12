package com.github.framework.evo.zuul.routelocator.bizz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-08-21 11:45
 * 由于ZUUL已经提供默认的路由加载轮询（每隔5秒扫描一次），所以本类中的刷新方法暂不使用
 */
@Slf4j
@Service
public class EvoZuulRouteBizz {
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	@Autowired
	private RouteLocator routeLocator;

	public void refresh() {
		log.info("刷新路由表");

		applicationEventPublisher.publishEvent(new RoutesRefreshedEvent(routeLocator));
	}
}
