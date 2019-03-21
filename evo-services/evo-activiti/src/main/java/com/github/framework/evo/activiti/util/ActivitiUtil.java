package com.github.framework.evo.activiti.util;

import com.github.framework.evo.common.uitl.BeanUtil;
import com.github.framework.evo.common.uitl.StringUtil;
import com.github.framework.evo.activiti.condition.ActivitiPageCondition;
import com.github.framework.evo.activiti.dto.VariableDto;
import com.github.framework.evo.activiti.proxy.entity.TaskProxy;
import com.github.framework.evo.activiti.proxy.entity.VariableProxy;
import com.github.framework.evo.activiti.proxy.model.ActivitiPage;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.VariableScope;
import org.activiti.engine.impl.el.Expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-04-02 09:47
 */
@Slf4j
public class ActivitiUtil {
	public static Map<String, Object> listToMap(List<VariableProxy> variableList) {
		Map<String, Object> map = new HashMap<>();
		for (VariableProxy variableProxy : variableList) {
			map.put(variableProxy.getName(), variableProxy.getValue());
		}
		return map;
	}

	public static List<VariableProxy> mapToList(Map<String, Object> variableMap) {
		List<VariableProxy> variableProxyList = new ArrayList<>();

		for (Map.Entry<String, Object> entry : variableMap.entrySet()) {
			VariableProxy variableProxy = new VariableProxy();
			variableProxy.setName(entry.getKey());
			variableProxy.setValue(StringUtil.toEmpty(entry.getValue()));
			variableProxyList.add(variableProxy);
		}

		return variableProxyList;
	}

	public static List<VariableDto> toVariableDto(List<VariableProxy> variableProxyList) {
		List<VariableDto> variableDtoList = new ArrayList<>();
		for (VariableProxy variableProxy : variableProxyList) {
			VariableDto variableDto = new VariableDto();
			BeanUtil.copy(variableDto, variableProxy);

			variableDtoList.add(variableDto);
		}
		return variableDtoList;
	}

	public static List<VariableProxy> toVariableProxy(List<VariableDto> variableDtoList) {
		List<VariableProxy> variableProxyList = new ArrayList<>();
		for (VariableDto variableDto : variableDtoList) {
			VariableProxy variableProxy = new VariableProxy();
			BeanUtil.copy(variableProxy, variableDto);

			variableProxyList.add(variableProxy);
		}
		return variableProxyList;
	}

	public static ActivitiPage toActivitiPage(ActivitiPageCondition activitiPageCondition) {
		ActivitiPage activitiPage = new ActivitiPage();

		int pageSize= activitiPageCondition.getPageSize();
		activitiPage.setStart((activitiPageCondition.getPageNo() - 1) * pageSize);
		activitiPage.setSize(pageSize);

		return activitiPage;
	}

	public static int getTaskSort(TaskProxy taskProxy) {
		String[] tss = taskProxy.getTaskDefinitionKey().split("_");
		return Integer.parseInt(tss[tss.length - 1].trim());
	}

	public static boolean isWithdraw(String taskId, String defpvWithdrawMark) {
		return StringUtil.isNotBlank(defpvWithdrawMark) && ("," + defpvWithdrawMark + ",").contains("," + taskId + ",");
	}

	public static String getExpressionText(Expression expression) {
		return expression == null ? null : expression.getExpressionText();
	}

	public static Object getExpressionValue(Expression expression, VariableScope variableScope) {
		Object value = null;
		if (expression != null) {
			try {
				value = expression.getValue(variableScope);
			} catch (ActivitiException e) {
				log.warn("流程变量不存在: " + e.getMessage());
			}
		}
		return value;
	}
}
