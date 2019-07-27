package com.github.framework.evo.autodeploy.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
@Data
@ApiModel("发布系统参数VO")
public class AutoDeployRequestVo {
    @ApiModelProperty("发布到哪一个环境上，int,uat,sim,rel")
    private String envirement;
    @ApiModelProperty("是否需要进行编译")
    private boolean compile;

    @ApiModelProperty("说明发布的是哪个项目,car,evo,payment,undwrt,platform")
    private String parentName;
    @ApiModelProperty("说明发布的哪个子项目")
    private String[] projectName;
}
