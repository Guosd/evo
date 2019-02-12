package com.ritoinfo.framework.evo.base.dao;

import com.ritoinfo.framework.evo.base.entity.BaseXmlEntity;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-02-09 16:54
 */
public interface BaseXmlDao<E extends BaseXmlEntity, PK extends Serializable> extends BaseDao<E, PK> {
}
