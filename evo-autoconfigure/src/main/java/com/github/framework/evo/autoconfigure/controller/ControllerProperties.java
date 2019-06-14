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
	private Eureka eureka = new Eureka();
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
	public static class Eureka {
		private Rest rest = new Rest();

		@Data
		public static class Rest {
			/**
			 * 注册服务
			 */
			private String register = "/apps/{serviceId}";
			/**
			 * 取消注册服务
			 */
			private String deRegister = "/apps/{serviceId}/{instanceId}";
			/**
			 * 发送心跳
			 */
			private String heartbeat = "/apps/{serviceId}/{instanceId}";
			/**
			 * 查询所有实例
			 */
			private String instances = "/apps";
			/**
			 * 查询所有指定serviceId的实例
			 */
			private String instancesOfService = "/apps/{serviceId}";
			/**
			 * 查询指定实例
			 */
			private String serviceInstance = "/apps/{serviceId}/{instanceId}";
			/**
			 * 查询指定实例
			 */
			private String instance = "/instances/{instanceId}";
			/**
			 * 实例下线
			 */
			private String outOfService = "/apps/{serviceId}/{instanceId}/status?value=OUT_OF_SERVICE";
			/**
			 * 实例上线
			 */
			private String backIntoService = "/apps/{serviceId}/{instanceId}/status?value=UP";
			/**
			 * 更新元数据
			 */
			private String metadata = "/apps/{serviceId}/{instanceId}/metadata?{key}={value}";
			/**
			 * 查询特定vip地址下的所有实例
			 */
			private String vips = "/vips/{vipAddress}";
			/**
			 * 查询特定安全vip地址下的所有实例
			 */
			private String svips = "/vips/{svipAddress}";
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
