package com.sinosoft.demo.controller;

import com.sinosoft.demo.po.DemoPo;
import com.sinosoft.demo.service.DemoService;
import com.sinosoft.demo.vo.DemoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @PostMapping("/test1")
    public DemoPo test1(DemoVo demoVo){
        return demoService.test(demoVo);
    }

    @PostMapping("/test2")
    public List<DemoPo> test2(DemoVo demoVo){
        return demoService.test2(demoVo);
    }
}
