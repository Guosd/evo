<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- ============================================================== -->
<!-- ==================可直接使用Base配置文件中定义的节点！=================== -->
<!-- ============================================================== -->
<mapper namespace="mybatis.common">
<#if genConfig.defaultCache>
	<!-- 开启二级缓存,在SQL中使用flushCache、useCache来刷新和使用缓存 -->
	<cache-ref namespace="mybatis.common"/>
</#if>
	<!-- 请在下方添加自定义全局MyBatis配置--> 
  
</mapper>
