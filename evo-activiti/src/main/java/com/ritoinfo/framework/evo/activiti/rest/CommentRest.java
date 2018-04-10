package com.ritoinfo.framework.evo.activiti.rest;

import com.ritoinfo.framework.evo.activiti.bizz.CommentBizz;
import com.ritoinfo.framework.evo.activiti.dto.CommentDto;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-10 14:17
 */
@Slf4j
@RequestMapping("comment")
@RestController
public class CommentRest {
	@Autowired
	private CommentBizz commentBizz;

	@GetMapping
	public ServiceResponse<List<CommentDto>> find(@RequestBody String processInstanceId) {
		return ServiceResponse.ok(commentBizz.getList(processInstanceId));
	}
}
