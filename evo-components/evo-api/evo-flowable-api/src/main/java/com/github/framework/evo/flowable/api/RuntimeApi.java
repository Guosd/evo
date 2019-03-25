package com.github.framework.evo.flowable.api;

import com.github.framework.evo.flowable.model.CommentDto;
import com.github.framework.evo.flowable.model.ProcessInstanceDto;
import com.github.framework.evo.flowable.model.StartReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * User: Kyll
 * Date: 2019-03-24 12:26
 */
@FeignClient(name = "evo-flowable", path = "/runtime")
public interface RuntimeApi {
	@PostMapping("/process-instance")
	ProcessInstanceDto startProcessInstanceByKey(@RequestBody StartReq startReq);

	@PostMapping("/process-instance/next")
	ProcessInstanceDto startProcessInstanceByKeyAndNext(@RequestBody StartReq startReq);

	@GetMapping("/process-instance/{processInstanceId}/comment")
	List<CommentDto> findProcessInstanceComments(@PathVariable("processInstanceId") String processInstanceId);

	@DeleteMapping("/clear")
	void clear();
}
