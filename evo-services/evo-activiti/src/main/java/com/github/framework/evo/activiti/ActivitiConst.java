package com.github.framework.evo.activiti;

/**
 * User: Kyll
 * Date: 2016-07-15 14:47
 */
public class ActivitiConst {
	public static final String VARIABLE_SCOPE_GLOBAL = "global";
	public static final String VARIABLE_SCOPE_LOCAL = "local";

	public static final String TASK_ACTION_COMPLETE = "complete";
	public static final String TASK_ACTION_CLAIM = "claim";
	public static final String TASK_ACTION_DELEGATE = "delegate";
	public static final String TASK_ACTION_RESOLVE = "resolve";

	public static final String PROCESS_INSTANCE_ACTION_SUSPEND = "suspend";
	public static final String PROCESS_INSTANCE_ACTION_ACTIVATE = "activate";

	public static final String VARIABLE_TYPE_INTEGER = "integer";
	public static final String VARIABLE_TYPE_LONG = "long";
	public static final String VARIABLE_TYPE_DOUBLE = "double";
	public static final String VARIABLE_TYPE_STRING = "string";
	public static final String VARIABLE_TYPE_DATE = "date";

	public static final String VARIABLE_OPERATION_EQUALS = "equals";
	public static final String VARIABLE_OPERATION_NOT_EQUALS = "notEquals";
	public static final String VARIABLE_OPERATION_EQUALS_IGNORE_CASE = "equalsIgnoreCase";
	public static final String VARIABLE_OPERATION_NOT_EQUALS_IGNORE_CASE = "notEqualsIgnoreCase";
	public static final String VARIABLE_OPERATION_LIKE = "like";
	public static final String VARIABLE_OPERATION_LIKE_IGNORE_CASE = "likeIgnoreCase";
	public static final String VARIABLE_OPERATION_GREATER_THAN = "greaterThan";
	public static final String VARIABLE_OPERATION_GREATER_THAN_OR_EQUALS = "greaterThanOrEquals";
	public static final String VARIABLE_OPERATION_LESS_THAN = "lessThan";
	public static final String VARIABLE_OPERATION_LESS_THAN_OR_EQUALS = "lessThanOrEquals";

	public static final int RESPONSE_CODE_OK = 200;
	public static final int RESPONSE_CODE_CREATED = 201;
	public static final int RESPONSE_CODE_NO_CONTENT = 204;
	public static final int RESPONSE_CODE_UNAUTHORIZED = 401;
	public static final int RESPONSE_CODE_FORBIDDEN = 403;
	public static final int RESPONSE_CODE_NOT_FOUND = 404;
	public static final int RESPONSE_CODE_METHOD_NOT_ALLOWED = 405;
	public static final int RESPONSE_CODE_CONFLICT = 409;
	public static final int RESPONSE_CODE_UNSUPPORTED_MEDIA_TYPE = 415;
	public static final int RESPONSE_CODE_INTERNAL_SERVER_ERROR = 500;
	public static final int RESPONSE_CODE_FAULT = -1;// 自定义 失败代码

	public static final String DEFPV_INITIATOR = "defpv_initiator";//流程发起人
	public static final String DEFPV_PROCESSDEFINITION_NAME = "defpv_processdefinition_name";//流程定义中文名
	public static final String DEFPV_PROCESSINSTANCE_BUSINESSKEY = "defpv_processinstance_businesskey";//业务编号
	public static final String DEFPV_PREV_OUTGOING = "defpv_prev_outgoing";
	public static final String DEFPV_PREV_OUTGOING_PASS = "pass";
	public static final String DEFPV_PREV_OUTGOING_REJECT = "reject";
	public static final String DEFPV_PREV_OUTGOING_WITHDRAW = "withdraw";
	public static final String DEFPV_PREV_OUTGOING_EXCEPTION = "exception";
	public static final String DEFPV_PREV_OUTGOING_ABANDON = "abandon";
	public static final String DEFPV_WITHDRAW_MARK = "defpv_withdraw_mark";
	public static final String DEFPV_FEEDBACKEXCEPTION = "feedbackException";
	public static final String DEFPV_INFA_URL_PROCESS_START = "process_start_url";
	public static final String DEFPV_INFA_URL_PROCESS_END = "process_end_url";
	public static final String DEFPV_INFA_URL_TASK_CREATE = "task_create_url";
	public static final String DEFPV_INFA_URL_TASK_ASSIGNMENT = "task_assignment_url";
	public static final String DEFPV_INFA_URL_TASK_COMPLETE = "task_complete_url";

	public static final String START_TASK_COMMENT = "流程启动";// 流程启动后第一个任务节点的默认审批意见
	public static final String START_TASK_COMMENT_PASS = "通过";// 正常流程通过时， 默认审批意见
	public static final String START_TASK_COMMENT_REJECT = "驳回后再次提交";// 起始任务被拒绝后再启动
	public static final String START_TASK_COMMENT_WITHDRAW = "撤销后再次提交";// 起始任务撤销后再启动
	public static final String WF_USER = "wf";// 工作流隐藏用户
}
