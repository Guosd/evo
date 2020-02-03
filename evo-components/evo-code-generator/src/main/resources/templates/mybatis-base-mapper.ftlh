<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- ============================================================== -->
<!-- ============================================================== -->
<!-- ===========通过evo-code-generate-mybatis工具自动生成，请勿手工修改！========= -->
<!-- ===========本配置文件中定义的节点可在自定义配置文件中直接使用！============== -->
<!-- ============================================================== -->
<!-- ============================================================== -->
<mapper namespace="${daoInfo.packageName}.${daoInfo.name}">
	<#include "inc/mybatis-base-mapper-inc.ftlh"> 
 
	<!-- 按主键查询一条记录 -->
	<select id="get" resultMap="BaseResultMap" parameterType="map">
		select
			<include refid="Base_Column_List" />
		from ${table.name}
		where ${primaryKey.columnName} = <#noparse>#</#noparse>{param1}
	</select>

	<!-- 按对象查询一页记录（多条记录） -->
	<select id="getOne" resultMap="BaseResultMap" parameterType="${poInfo.packageName}.${poInfo.name}">
		<include refid="Base_Select_By_Entity" />
	</select>

	<select id="find" resultMap="BaseResultMap" parameterType="${poInfo.packageName}.${poInfo.name}">
		<include refid="Base_Select_By_Entity" />
	</select>

	<select id="findLike" resultMap="BaseResultMap" parameterType="${poInfo.packageName}.${poInfo.name}">
		<include refid="Base_Select_By_Like_Entity" />
	</select>

	<!-- 查询行数 -->
	<select id="count" resultType="int" parameterType="${poInfo.packageName}.${poInfo.name}">
		select
		count(1)
		from ${table.name}
		<where>
			<include refid="Base_Select_By_Entity_Where" />
		</where>
	</select>

	<!-- 模糊查询行数 -->
	<select id="countLike" resultType="int" parameterType="${poInfo.packageName}.${poInfo.name}">
		select
		count(1)
		from ${table.name}
		<where>
			<include refid="Base_Select_By_Like_Entity_Where" />
		</where>
	</select>

	<!-- 完整插入一条记录-->
	<insert id="insert" <#if isAutoIncrementPK>useGeneratedKeys="true" keyProperty="${toVariableString(primaryKey.columnName)}"</#if> parameterType="${poInfo.packageName}.${poInfo.name}">
		insert into ${table.name} (
<#list table.columns as column>
<#if genConfig.insertTimeForHisName?lower_case != toVariableString(column.name)?lower_case >
			${column.name}<#if column_index < (columnSize -1)>,</#if>
</#if>			
</#list>
		) values (
<#list table.columns as column>
<#if genConfig.insertTimeForHisName?lower_case == toVariableString(column.name)?lower_case >
<#elseif genConfig.operateTimeForHisName?lower_case == toVariableString(column.name)?lower_case >
			<include refid="mybatis.common.Base_Current" /><#if column_index < (columnSize -1)>,</#if>
<#elseif genConfig.versionName?lower_case == toVariableString(column.name)?lower_case >
			1<#if column_index < (columnSize -1)>,</#if>
<#elseif "deletedFlag"?lower_case == toVariableString(column.name)?lower_case >
			'0'<#if column_index < (columnSize -1)>,</#if>
<#else>			
			<#noparse>#</#noparse>{${toVariableString(column.name)}}<#if column_index < (columnSize -1)>,</#if>
</#if>
</#list>
		)
	</insert>

	<!-- 插入一条记录(为空的字段不操作) -->
	<insert id="insertSelective" <#if isAutoIncrementPK>useGeneratedKeys="true" keyProperty="${toVariableString(primaryKey.columnName)}"</#if> parameterType="${poInfo.packageName}.${poInfo.name}">
		insert into ${table.name}
		<trim prefix="(" suffix=")" suffixOverrides="," >
<#list table.columns as column>
<#if genConfig.insertTimeForHisName?lower_case == toVariableString(column.name)?lower_case >
<#elseif genConfig.operateTimeForHisName?lower_case == toVariableString(column.name)?lower_case >
			${column.name}<#if column_index < (columnSize -1)>,</#if>
<#elseif genConfig.versionName?lower_case == toVariableString(column.name)?lower_case >
			${column.name}<#if column_index < (columnSize -1)>,</#if>
<#else>
			<if test="${toVariableString(column.name)} != null" >
				${column.name}<#if column_index < (columnSize -1)>,</#if>
			</if>
</#if>
</#list>
		</trim>
		values <trim prefix="(" suffix=")" suffixOverrides="," >
<#list table.columns as column>
<#if genConfig.insertTimeForHisName?lower_case == toVariableString(column.name)?lower_case >
<#elseif genConfig.operateTimeForHisName?lower_case == toVariableString(column.name)?lower_case >
			<include refid="mybatis.common.Base_Current" /><#if column_index < (columnSize -1)>,</#if>
<#elseif genConfig.versionName?lower_case == toVariableString(column.name)?lower_case >
			1<#if column_index < (columnSize -1)>,</#if>
<#elseif "deletedFlag"?lower_case == toVariableString(column.name)?lower_case >
			'0'<#if column_index < (columnSize -1)>,</#if>			
<#else>
			<if test="${toVariableString(column.name)} != null" >
				<#noparse>#</#noparse>{${toVariableString(column.name)}}<#if column_index < (columnSize -1)>,</#if>
			</if>
</#if>
</#list>
		</trim>
	</insert>

	<!-- 更新一条记录(为空的字段不操作) -->
	<update id="updateSelective" <#if isAutoIncrementPK>useGeneratedKeys="true" keyProperty="${toVariableString(primaryKey.columnName)}"</#if> parameterType="${poInfo.packageName}.${poInfo.name}">
		update ${table.name}
		<set>
<#list updateColumns as column>
<#if genConfig.insertTimeForHisName?lower_case == toVariableString(column.name)?lower_case >
<#elseif primaryKey.columnName == column.name >
<#elseif genConfig.operateTimeForHisName?lower_case == toVariableString(column.name)?lower_case >
			${column.name} = <include refid="mybatis.common.Base_Current" /><#if column_index < (columnSize -1)>,</#if>
<#elseif genConfig.versionName?lower_case == toVariableString(column.name)?lower_case >
			${column.name} = ${column.name} + 1<#if column_index < (columnSize -1)>,</#if>
<#elseif "deletedFlag"?lower_case == toVariableString(column.name)?lower_case >
			${column.name} = '0'<#if column_index < (columnSize -1)>,</#if>
<#else>
			<if test="${toVariableString(column.name)} != null" >
				${column.name}=<#noparse>#</#noparse>{${toVariableString(column.name)}}<#if column_index < (columnSize -1)>,</#if>
			</if>
</#if>
</#list>
		</set>
		where ${primaryKey.columnName} = <#noparse>#</#noparse>{${toVariableString(primaryKey.columnName)} }<#if hasVersionField> and ${versionFieldName} = <#noparse>#</#noparse>{${versionFieldName}}
</#if>			
	</update>

	<!-- 完整更新一条记录 -->
	<update id="update" <#if isAutoIncrementPK>useGeneratedKeys="true" keyProperty="${toVariableString(primaryKey.columnName)}"</#if> parameterType="${poInfo.packageName}.${poInfo.name}">
		update ${table.name} set
		<trim suffixOverrides="," >
<#list updateColumns as column>
<#if genConfig.insertTimeForHisName?lower_case == toVariableString(column.name)?lower_case >
<#elseif primaryKey.columnName == column.name >
<#elseif genConfig.operateTimeForHisName?lower_case == toVariableString(column.name)?lower_case >
			${column.name} = <include refid="mybatis.common.Base_Current" /><#if column_index < (columnSize -1)>,</#if>
<#elseif genConfig.versionName?lower_case == toVariableString(column.name)?lower_case >
			${column.name} = ${column.name} + 1<#if column_index < (columnSize -1)>,</#if>
<#elseif "deletedFlag"?lower_case == toVariableString(column.name)?lower_case >
			${column.name} = '0'<#if column_index < (columnSize -1)>,</#if>
<#else>
			${column.name}=<#noparse>#</#noparse>{${toVariableString(column.name)}}<#if column_index < (columnSize -1)>,</#if>
</#if>
</#list>
		</trim>
		where ${primaryKey.columnName} = <#noparse>#</#noparse>{${toVariableString(primaryKey.columnName)}}<#if hasVersionField> and ${versionFieldName} = <#noparse>#</#noparse>{${versionFieldName}}
</#if>
	</update>

	<!-- 按主键删除一条记录 -->
	<delete id="delete" parameterType="map">
		delete from ${table.name}
		where ${primaryKey.columnName} = <#noparse>#</#noparse>{param1}
	</delete>
</mapper>
