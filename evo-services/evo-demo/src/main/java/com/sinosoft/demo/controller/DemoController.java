package com.sinosoft.demo.controller;

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

    @PostMapping("/test")
    public List<String> test(DemoVo demoVo){
        return demoService.test(demoVo);
    }
}
