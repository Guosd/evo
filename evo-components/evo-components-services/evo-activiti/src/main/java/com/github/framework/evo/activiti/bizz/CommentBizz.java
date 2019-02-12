package com.github.framework.evo.activiti.bizz;

import com.github.framework.evo.activiti.dto.CommentDto;
import com.github.framework.evo.activiti.proxy.service.HistoryServiceProxy;
import com.github.framework.evo.activiti.proxy.service.TaskServiceProxy;
import com.github.framework.evo.base.assist.BaseHelper;
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
		List<CommentDto> commentDtoList = BaseHelper.convertObject(taskServiceProxy.getProcessInstanceCommentList(processInstanceId), CommentDto.class);
		for (CommentDto commentDto : commentDtoList) {
			// TODO author
			commentDto.setTaskName(historyServiceProxy.getById(commentDto.getTaskId()).getName());
		}
		return commentDtoList;
	}
}
