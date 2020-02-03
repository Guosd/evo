package ${daoInfo.packageName};

import org.apache.ibatis.annotations.Mapper;

import ${poInfo.packageName}.${poInfo.name};
<#if isUnionKey>
import ${poInfo.packageName}.${idType};
</#if>
import com.github.framework.evo.base.dao.BaseXmlDao;

/**
 *
 * 表${table.name}对应的基于MyBatis实现的Dao接口<br/>
 * 在其中添加自定义方法
 *
 */
@Mapper
public interface ${daoInfo.name} extends BaseXmlDao<${poInfo.name}, ${idType}> {

}
