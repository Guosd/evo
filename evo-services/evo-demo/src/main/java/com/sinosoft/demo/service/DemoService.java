package com.sinosoft.demo.service;

import com.sinosoft.demo.dao.DemoDao;
import com.sinosoft.demo.vo.DemoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DemoService {
    @Autowired
    private DemoDao demoDao;

    public List<String> test(DemoVo demoVo){
        return demoDao.findPage();
    }
}
