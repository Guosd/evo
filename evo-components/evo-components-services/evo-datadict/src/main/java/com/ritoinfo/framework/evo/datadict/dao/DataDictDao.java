package com.ritoinfo.framework.evo.datadict.dao;

import com.ritoinfo.framework.evo.base.dao.BaseXmlDao;
import com.ritoinfo.framework.evo.datadict.entity.DataDict;
import org.apache.ibatis.annotations.Mapper;

/**
 * User: Kyll
 * Date: 2018-04-13 09:51
 */
@Mapper
public interface DataDictDao extends BaseXmlDao<DataDict, Long> {
}
