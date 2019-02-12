package com.github.framework.evo.sys.dao;

import com.github.framework.evo.base.dao.BaseXmlDao;
import com.github.framework.evo.sys.entity.Micro;
import org.apache.ibatis.annotations.Mapper;

/**
 * User: Kyll
 * Date: 2018-04-23 20:02
 */
@Mapper
public interface MicroDao extends BaseXmlDao<Micro, Long> {
}
