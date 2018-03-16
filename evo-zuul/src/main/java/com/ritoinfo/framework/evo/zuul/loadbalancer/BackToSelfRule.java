package com.ritoinfo.framework.evo.zuul.loadbalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Kyll
 * Date: 2018-01-10 18:50
 */
@Slf4j
public class BackToSelfRule extends AbstractLoadBalancerRule {
	@Override
	public void initWithNiwsConfig(IClientConfig iClientConfig) {
	}

	@Override
	public Server choose(Object key) {
		return this.choose(this.getLoadBalancer(), key);
	}

	private Server choose(ILoadBalancer loadBalancer, Object key) {
		if (loadBalancer == null) {
			log.warn("no load balancer");
			return null;
		}

		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String remoteHost = request.getRemoteHost();
		String headerHostPort = request.getHeader("host");

		log.info("Client Request Info: " + request.getRequestURL() + ", addr: " + request.getRemoteAddr() + ", host: " + remoteHost + ", port: " + request.getRemotePort() + ", headerHost: " + headerHostPort);

		Server finalServer = null;
		for (Server server : loadBalancer.getReachableServers()) {
			if (server.getHost().equals(remoteHost)) {
				finalServer = server;
				break;
			}
		}

		if (finalServer == null) {
			for (Server server : loadBalancer.getReachableServers()) {
				if (server.getHost().equals(headerHostPort.split(":")[0])) {
					finalServer = server;
					break;
				}
			}

			log.warn("server on " + remoteHost + " is not exist, use default server");
		} else {
			log.info("find server on " + remoteHost);
		}

		return finalServer;
	}
}
