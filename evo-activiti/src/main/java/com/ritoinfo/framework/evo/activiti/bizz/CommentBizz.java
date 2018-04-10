package com.ritoinfo.framework.evo.activiti.bizz;

import com.ritoinfo.framework.evo.activiti.dto.CommentDto;
import com.ritoinfo.framework.evo.activiti.proxy.service.HistoryServiceProxy;
import com.ritoinfo.framework.evo.activiti.proxy.service.TaskServiceProxy;
import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-10 14:21
 */
@Slf4j
@Service
public class CommentBizz {
	@Autowired
	private TaskServiceProxy taskServiceProxy;
	@Autowired
	private HistoryServiceProxy historyServiceProxy;

	public List<CommentDto> getList(String processInstanceId) {
		List<CommentDto> commentDtoList = BaseHelper.toAnyDto(taskServiceProxy.getProcessInstanceCommentList(processInstanceId), CommentDto.class);
		for (CommentDto commentDto : commentDtoList) {
			// TODO author
			commentDto.setTaskName(historyServiceProxy.getById(commentDto.getTaskId()).getName());
		}
		return commentDtoList;
	}
}
