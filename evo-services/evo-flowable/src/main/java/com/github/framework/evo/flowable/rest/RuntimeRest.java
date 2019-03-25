package com.github.framework.evo.flowable.rest;

import com.github.framework.evo.flowable.api.RuntimeApi;
import com.github.framework.evo.flowable.bizz.RuntimeBizz;
import com.github.framework.evo.flowable.model.CommentDto;
import com.github.framework.evo.flowable.model.ProcessInstanceDto;
import com.github.framework.evo.flowable.model.StartReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2019-03-24 12:26
 */
@RequestMapping("/runtime")
@RestController
public class RuntimeRest implements RuntimeApi {
	@Autowired
	private RuntimeBizz runtimeBizz;

	@Override
	public ProcessInstanceDto startProcessInstanceByKey(StartReq startReq) {
		return runtimeBizz.startProcessInstanceByKey(startReq);
	}

	@Override
	public ProcessInstanceDto startProcessInstanceByKeyAndNext(StartReq startReq) {
		return runtimeBizz.startProcessInstanceByKeyAndNext(startReq);
	}

	@Override
	public List<CommentDto> findProcessInstanceComments(String processInstanceId) {
		return runtimeBizz.findProcessInstanceComments(processInstanceId);
	}

	@Override
	public void clear() {
		runtimeBizz.clear();
	}
}
