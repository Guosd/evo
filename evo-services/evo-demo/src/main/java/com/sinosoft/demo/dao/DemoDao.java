package com.sinosoft.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.framework.evo.base.PageParam;
import com.sinosoft.demo.po.DemoPo;

import java.util.List;

public interface DemoDao extends BaseMapper<DemoPo> {
    List<DemoPo> findPageTest(PageParam pageParam);
    DemoPo get(String id);
}
