package com.sinosoft.demo.service;

import com.github.framework.evo.base.MybatisApiUtils;
import com.github.framework.evo.base.PageParam;
import com.sinosoft.demo.dao.DemoDao;
import com.sinosoft.demo.po.DemoPo;
import com.sinosoft.demo.vo.DemoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Slf4j
public class DemoService {
    @Autowired
    private DemoDao demoDao;

    public DemoPo test(@RequestParam DemoVo demoVo){
        // PageParam pageParam = new PageParam(1,4);
        return demoDao.selectById("1");
    }

    public List<DemoPo> test2(@RequestParam DemoVo demoVo){
        PageParam pageParam = new PageParam(1,2);
        List<DemoPo> result = demoDao.findPageTest(pageParam);
        return result;
    }
}
