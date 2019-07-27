package com.github.framework.evo.autodeploy.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
@Data
@ApiModel("发布结果VO")
public class AutoDeployResponseVo {
    @ApiModelProperty("是否发布成功")
    private String result;
    @ApiModelProperty("发布不成功的愿景")
    private String message;
}
