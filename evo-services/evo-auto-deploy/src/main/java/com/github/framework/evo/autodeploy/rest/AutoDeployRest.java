package com.github.framework.evo.autodeploy.rest;

import com.github.framework.evo.autodeploy.bizz.AutoDeployBizz;
import com.github.framework.evo.autodeploy.entity.AutoDeployRequestVo;
import com.github.framework.evo.autodeploy.entity.AutoDeployResponseVo;
import com.github.framework.evo.common.exception.ExceptionUtil;
import com.github.framework.evo.common.web.ApiResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
@RestController
@RequestMapping("/autodeploy/")
@Slf4j
public class AutoDeployRest {
    @Autowired
    private AutoDeployBizz autoDeployBizz;

    /**
     * 自动发布入口地址
     * @param autoDeployRequestVo
     * @return
     */
    @ApiOperation(value = "自动发布接口")
    @PostMapping("autoDeploy")
    public ApiResponse<AutoDeployResponseVo> deploy(AutoDeployRequestVo autoDeployRequestVo){
        ApiResponse<AutoDeployResponseVo> result = ApiResponse.ok();
        try {
            AutoDeployResponseVo autoDeployResponseVo = autoDeployBizz.autoDeploy(autoDeployRequestVo);
            result.setData(autoDeployResponseVo);
        }catch (Exception e){
            log.error(e.getMessage(),e.getCause());
            result = ExceptionUtil.getExceptionResult(e);
        }
        return result;
    }


}
