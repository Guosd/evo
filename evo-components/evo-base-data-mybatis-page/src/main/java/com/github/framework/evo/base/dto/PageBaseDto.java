package com.github.framework.evo.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by sino on 2019/9/23.
 */
@Data
@ApiModel("分页基础对象")
public class PageBaseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("页码,从1开始")
    private int pageNum = 1;

    @ApiModelProperty("页面大小")
    private int pageSize = 10;
}
