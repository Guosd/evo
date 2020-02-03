package ${poInfo.packageName};

import java.io.Serializable;
<#if containsDate>
import java.util.Date;
</#if>
<#if containsBigDecimal>
import java.math.BigDecimal;
</#if>
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * 通过evo-code-generate-mybatis工具自动生成，请勿手工修改。表${table.name}的复合主键PO对象<br/>
 * 对应表名：${table.name}<#if table.comment != "">,备注：${table.comment}</#if>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ${poInfo.name} extends ${poInfo.name}Key implements Serializable {
	private static final long serialVersionUID = 1L;

<#list table.nonKeyColumns as column>
	/** 对应字段：${column.name}<#if column.comment != "">,备注：${column.comment}</#if> */
	private ${toJavaType(column.type)} ${toVariableString(column.name)};
</#list>	
}
