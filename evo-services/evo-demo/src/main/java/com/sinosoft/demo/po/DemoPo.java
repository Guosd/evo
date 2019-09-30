package com.sinosoft.demo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("demo")
public class DemoPo {
    @TableId
    private String id;
    @TableField("name")
    private String name;
}
