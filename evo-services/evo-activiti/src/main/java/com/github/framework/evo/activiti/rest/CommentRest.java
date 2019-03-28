package com.github.framework.evo.activiti.rest;

import com.github.framework.evo.activiti.api.CommentApi;
import com.github.framework.evo.activiti.bizz.CommentBizz;
import com.github.framework.evo.activiti.dto.CommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-10 14:17
 */
@Slf4j
@RequestMapping("/comment")
@RestController
public class CommentRest implements CommentApi {
	@Autowired
	private CommentBizz commentBizz;

	@Override
	public List<CommentDto> find(@PathVariable String processInstanceId) {
		return commentBizz.getList(processInstanceId);
	}
}
