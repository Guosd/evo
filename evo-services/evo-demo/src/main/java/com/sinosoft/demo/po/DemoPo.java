package com.sinosoft.demo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName
public class DemoPo {
    @TableField
    private String id;
    @TableField
    private String name;
}
