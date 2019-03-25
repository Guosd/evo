package com.github.framework.evo.flowable.model;

import lombok.Data;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2019-03-25 22:56
 */
@Data
public class CommentDto {
	private String id;
	private String userId;
	private Date time;
	private String taskId;
	private String processInstanceId;
	private String type;
	private String fullMessage;
}
