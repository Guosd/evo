package com.github.framework.evo.autoconfigure.controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Kyll
 * Date: 2019-05-23 10:21
 */
@Data
@ConfigurationProperties("evo.controller")
public class ControllerProperties {
	private DockerSwarm dockerSwarm = new DockerSwarm();
	private Config config = new Config();

	@Data
	public static class DockerSwarm {
		private Network network = new Network();

		/**
		 * Docker Swarm Http REST API host
		 */
		private String host = "localhost";
		/**
		 * Docker Swarm Http REST API port
		 */
		private int port = 2375;

		@Data
		public static class Network {
			/**
			 * Overlay网络名称
			 */
			private String name = "evo-overlay";
		}
	}

	@Data
	public static class Config {
		/**
		 * 配置中心的 Spring Application Name
		 */
		private String serviceId = "evo-config";
	}
}
