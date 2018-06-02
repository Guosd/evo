package com.ritoinfo.framework.evo;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2017-11-27 11:57
 */
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EvoActivitiApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoActivitiApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner init(final RepositoryService repositoryService,
	                              final RuntimeService runtimeService,
	                              final TaskService taskService) {

		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				Map<String, Object> map = new HashMap<>();
				map.put("task_create_url", "http://10.20.133.30:20115/code");
				map.put("task_complete_url", "http://10.20.133.30:20115/code");

				runtimeService.startProcessInstanceByKey("oneTaskProcess", map);

				for (Task task : taskService.createTaskQuery().list()) {
					taskService.complete(task.getId());
				}
			}
		};

	}*/

}
