package com.github.framework.evo.autodeploy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.framework.evo.autodeploy.entity.AutoReplaceParameter;

import java.util.List;

/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
public interface AutoReplaceParameterDao extends BaseMapper<AutoReplaceParameter> {
    List<String> findFileName();
}
