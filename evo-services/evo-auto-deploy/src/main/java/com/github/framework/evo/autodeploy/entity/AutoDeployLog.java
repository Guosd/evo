package com.github.framework.evo.autodeploy.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.github.framework.evo.base.entity.BaseEntity;
import lombok.Data;

import java.util.Date;
/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
@Data
@TableName("evo_auto_deploy_log")
public class AutoDeployLog extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private String id;
    @TableField("command")
    private String command;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_by")
    private String updateBy;
    @TableField("update_time")
    private Date updateTime;
}
