package com.github.framework.evo.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sino on 2019/9/19.
 */
@Data
@ApiModel("分页结果对象")
public class PageResult<T> extends PageBaseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("总数")
    private long total;

    @ApiModelProperty("分页数据")
    private List<T> data;
}
