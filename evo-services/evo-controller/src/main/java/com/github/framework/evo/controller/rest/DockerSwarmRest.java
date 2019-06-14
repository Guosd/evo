package com.github.framework.evo.controller.rest;

import com.github.framework.evo.controller.bizz.DockerSwarmBizz;
import com.github.framework.evo.controller.model.dockerswarm.NodeDto;
import com.github.framework.evo.controller.model.dockerswarm.ServiceDto;
import com.github.framework.evo.controller.model.dockerswarm.SwarmDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2019-06-14 09:46
 */
@Slf4j
@RequestMapping("/docker-swarm")
@RestController
public class DockerSwarmRest {
	@Autowired
	private DockerSwarmBizz dockerSwarmBizz;

	@GetMapping("/inspect")
	public SwarmDto inspectSwarm() {
		return dockerSwarmBizz.inspectSwarm();
	}

	@GetMapping("/nodes")
	public List<NodeDto> listNodes() {
		return dockerSwarmBizz.listNodes();
	}

	@GetMapping("/nodes/{id}")
	public NodeDto inspectNode(@PathVariable String id) {
		return dockerSwarmBizz.inspectNode(id);
	}

	@GetMapping("/services")
	public List<ServiceDto> listServices() {
		return dockerSwarmBizz.listServices();
	}

	@GetMapping("/services/{id}")
	public ServiceDto inspectService(@PathVariable String id) {
		return dockerSwarmBizz.inspectService(id);
	}

	@PostMapping("/services/{id}/update")
	public void updateService(@PathVariable String id, @RequestParam int replicas) {
		dockerSwarmBizz.updateService(id, replicas);
	}
}
