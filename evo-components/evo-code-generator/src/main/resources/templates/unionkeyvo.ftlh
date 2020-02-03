package ${voInfo.packageName};

import java.io.Serializable;
<#if containsDate>
import java.util.Date;
</#if>
<#if containsBigDecimal>
import java.math.BigDecimal;
</#if> 
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * ${voInfo.keyName}KeyVo对象.<#if table.comment != "">对应实体描述：${table.comment}</#if>
 *
 */
@Data
@ApiModel(" ${voInfo.keyName}KeyVo对象")
public class ${voInfo.keyName}KeyVo implements Serializable {
	private static final long serialVersionUID = 1L;

<#list table.keyColumns as column>
	/** 对应字段：${column.name}<#if column.comment != "">,备注：${column.comment}</#if> */
	@ApiModelProperty(<#if column.comment != "">"${column.comment}"</#if>)
	private ${toJavaType(column.type)} ${toVariableString(column.name)};
</#list>	
}
