package com.deploy.rest;

import com.github.framework.evo.deploy.bizz.AutoDeployBizz;
import com.github.framework.evo.deploy.entity.DeployRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AutoDeployRest {
    @Autowired
    private AutoDeployBizz autoDeployBizz;

    @PostMapping("/deploy")
    public String deploy(@RequestBody DeployRequestVo deployRequestVo){
        String result = autoDeployBizz.deploy(deployRequestVo);
        return result;
    }
}
