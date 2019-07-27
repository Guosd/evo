package com.github.framework.evo.autodeploy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.framework.evo.base.entity.BaseEntity;
import lombok.Data;

import java.util.Date;
/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
@Data
@TableName("evo_auto_deploy_parameter")
public class AutoDeployParameter extends BaseEntity {
    @TableId("id")
    private String id;
    @TableField("key")
    private String key;
    @TableField("value")
    private String value;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_by")
    private String updateBy;
    @TableField("update_time")
    private Date updateTime;
}
