package ${poInfo.packageName};

import com.github.framework.evo.base.entity.BaseXmlEntity;

import java.io.Serializable;
<#if containsDate>
import java.util.Date;
</#if>
<#if containsBigDecimal>
import java.math.BigDecimal;
</#if>
import lombok.Data;

/**
 *
 * 通过evo-code-generator工具自动生成，请勿手工修改。表${table.name}的复合主键PO主键对象<br/>
 * 对应表名：${table.name}<#if table.comment != "">,备注：${table.comment}</#if>
 *
 */
@Data
public class ${poInfo.name}Key extends BaseXmlEntity implements Serializable {
	private static final long serialVersionUID = 1L;

<#list table.keyColumns as column>
	/** 对应字段：${column.name}<#if column.comment != "">,备注：${column.comment}</#if> */
	private ${toJavaType(column.type)} ${toVariableString(column.name)};
</#list>	
}
