package com.ritoinfo.framework.evo.activiti.dto;

import lombok.Data;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2016-07-14 16:31
 */
@Data
public class CommentDto {
	private String id;
	private String author;
	private String message;
	private Date time;
	private String taskId;
	private String taskName;
	private String taskUrl;
	private String processInstanceId;
	private String processInstanceUrl;
}
