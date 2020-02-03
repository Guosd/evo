<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- ============================================================== -->
<!-- ============================================================== -->
<!-- ===========通过ins-framework-mybatis工具自动生成，请勿手工修改！========= -->
<!-- ===========本配置文件中定义的节点可在自定义配置文件中直接使用！============== -->
<!-- ============================================================== -->
<!-- ============================================================== -->
<mapper namespace="mybatis.common">
<#if genConfig.defaultCache>
	<!-- 默认开启二级缓存,使用两级缓存来处理 -->
	<cache type="ins.framework.mybatis.cache.decorators.DefaultCache"/>
</#if>

	<!-- 获取时间戳 -->
	<sql id="Base_Current">
		<choose>
		    <when test="_databaseId == 'oracle'">sysdate</when>
		    <when test="_databaseId == 'sqlserver'">GetDate()</when>
		    <when test="_databaseId == 'mysql'">now()</when>
		    <when test="_databaseId == 'postgresql'">now()</when>
		    <when test="_databaseId == 'informix'">current</when>
		    <when test="_databaseId == 'db2'">current</when>
		    <otherwise>current</otherwise>
		</choose>	
	</sql>
</mapper>
