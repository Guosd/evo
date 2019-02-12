package com.ritoinfo.framework.evo.activiti.api;

import com.ritoinfo.framework.evo.activiti.dto.CommentDto;
import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-11 21:32
 */
@FeignClient(name = "evo-activiti", path = "/comment")
public interface CommentApi {
	@GetMapping("{processInstanceId}")
	ServiceResponse<List<CommentDto>> find(@PathVariable("processInstanceId") String processInstanceId);
}
