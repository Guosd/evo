<#if genConfig.defaultCache>
	<!-- 默认开启二级缓存,使用两级缓存来处理 -->
	<cache type="ins.framework.mybatis.cache.decorators.DefaultCache"/>
</#if>
	<!-- 通用查询结果对象-->
	<resultMap id="BaseResultMap" type="${poInfo.packageName}.${poInfo.name}">
<#list table.keyColumns as column>
		<id column="${column.name}" property="${toVariableString(column.name)}" />
</#list>
<#list table.nonKeyColumns as column>
		<result column="${column.name}" property="${toVariableString(column.name)}" />
</#list>
	</resultMap>
	
	<!-- 通用查询结果列-->
	<sql id="Base_Column_List"> 
<#assign columnSize = table.columns?size>
<#list table.columns as column>
		${column.name}<#if column_index < (columnSize -1)>,</#if>
</#list>
	</sql>

	<!-- 按对象查询记录的WHERE部分 -->
	<sql id="Base_Select_By_Entity_Where">
<#list table.columns as column>
	<#if "deletedFlag"?lower_case == toTypeString(column.name)?lower_case>
		and ${column.name} = '0' 
	<#else>
		<if test="${toVariableString(column.name)} != null and ${toVariableString(column.name)} != ''" >
			and ${column.name} = <#noparse>#</#noparse>{${toVariableString(column.name)}}
		</if>
	</#if>
</#list>	
	</sql>

	<!-- 按对象查询记录的SQL部分 -->
	<sql id="Base_Select_By_Entity">
		select
			<include refid="Base_Column_List" />
		from ${table.name}
		<where>
			<include refid="Base_Select_By_Entity_Where" />
		</where>
	</sql>

	<!-- 按对象查询记录的模糊匹配WHERE部分 -->
	<sql id="Base_Select_By_Like_Entity_Where">
		<#list table.columns as column>
			<#if "deletedFlag"?lower_case == toTypeString(column.name)?lower_case>
				and ${column.name} = '0'
			<#else>
				<if test="${toVariableString(column.name)} != null and ${toVariableString(column.name)} != ''" >
					and ${column.name} like concat(concat('%',<#noparse>#</#noparse>{${toVariableString(column.name)}}),'%')
				</if>
			</#if>
		</#list>
	</sql>

	<!-- 按对象查询记录的模糊匹配SQL部分 -->
	<sql id="Base_Select_By_Like_Entity">
		select
		<include refid="Base_Column_List" />
		from ${table.name}
		<where>
			<include refid="Base_Select_By_Like_Entity_Where" />
		</where>
	</sql>