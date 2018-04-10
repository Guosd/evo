package com.ritoinfo.framework.evo.activiti.proxy.model;

import com.ritoinfo.framework.evo.activiti.ActivitiConst;
import com.ritoinfo.framework.evo.activiti.proxy.entity.VariableProxy;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

public class ActivitiVariable {
	private List<VariableProxy> variableProxyList;
	private String initiator;
	private String processDefinitionName;
	private String businessKey;
	private String prevOutgoing;
	private String withdrawMark;
	private String feedbackException;

	public ActivitiVariable(List<VariableProxy> variableProxyList) {
		this.variableProxyList = variableProxyList;
	}

	public String getInitiator() {
		if (StringUtil.isBlank(initiator)) {
			initiator = getVariableStringValue(ActivitiConst.DEFPV_INITIATOR);
		}
		return initiator;
	}

	public String getProcessDefinitionName() {
		if (StringUtil.isBlank(processDefinitionName)) {
			processDefinitionName = getVariableStringValue(ActivitiConst.DEFPV_PROCESSDEFINITION_NAME);
		}
		return processDefinitionName;
	}

	public String getBusinessKey() {
		if (StringUtil.isBlank(businessKey)) {
			businessKey = getVariableStringValue(ActivitiConst.DEFPV_PROCESSINSTANCE_BUSINESSKEY);
		}
		return businessKey;
	}

	public String getPrevOutgoing() {
		if (StringUtil.isBlank(prevOutgoing)) {
			prevOutgoing = getVariableStringValue(ActivitiConst.DEFPV_PREV_OUTGOING);
		}
		return prevOutgoing;
	}

	public String getWithdrawMark() {
		if (StringUtil.isBlank(withdrawMark)) {
			withdrawMark = getVariableStringValue(ActivitiConst.DEFPV_WITHDRAW_MARK);
		}
		return withdrawMark;
	}

	public String getFeedbackException() {
		if (StringUtil.isBlank(feedbackException)) {
			feedbackException = getVariableStringValue(ActivitiConst.DEFPV_FEEDBACKEXCEPTION);
		}
		return feedbackException;
	}

	public String getVariableStringValue(String name) {
		Object value = this.getVariableValue(name);
		return value == null ? null : value.toString();
	}

	public Double getVariableDoubleValue(String name) {
		String value = this.getVariableStringValue(name);
		return StringUtils.isBlank(value) ? null : NumberUtils.toDouble(value);
	}

	public Integer getVariableIntegerValue(String name) {
		String value = this.getVariableStringValue(name);
		return StringUtils.isBlank(value) ? null : NumberUtils.toInt(value);
	}

	public Object getVariableValue(String name) {
		Object value = null;
		for (VariableProxy variableProxy : variableProxyList) {
			if (name.equals(variableProxy.getName())) {
				value = variableProxy.getValue();
				break;
			}
		}
		return value;
	}
}
