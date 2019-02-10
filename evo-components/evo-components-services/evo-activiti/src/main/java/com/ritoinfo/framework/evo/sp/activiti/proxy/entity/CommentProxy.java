package com.ritoinfo.framework.evo.sp.activiti.proxy.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Kyll
 * Date: 2016-07-14 16:31
 */
@Data
public class CommentProxy implements Serializable {
	private String id;
	private String author;
	private String message;
	private Date time;
	private String taskId;
	private String taskUrl;
	private String processInstanceId;
	private String processInstanceUrl;
}
