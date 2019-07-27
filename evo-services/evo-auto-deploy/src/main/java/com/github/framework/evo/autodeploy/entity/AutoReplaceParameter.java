package com.github.framework.evo.autodeploy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.framework.evo.base.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
@Data
@TableName("evo_auto_replace_parameter")
public class AutoReplaceParameter extends BaseEntity {
    @TableField("id")
    private String id;
    @TableField("key")
    private String key;
    @TableField("project_name")
    private String projectName;
    @TableField("new_value")
    private String newvalue;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_by")
    private String updateBy;
    @TableField("update_time")
    private Date updateTime;
}
