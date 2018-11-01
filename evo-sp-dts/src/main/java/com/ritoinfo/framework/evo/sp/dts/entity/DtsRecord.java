package com.ritoinfo.framework.evo.sp.dts.entity;

import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseMapperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: Kyll
 * Date: 2018-10-18 16:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "dts_record", catalog = "evo_framework")
public class DtsRecord extends BaseMapperEntity<Long> {
	@Id
	@KeySql(useGeneratedKeys = true)
	private Long id;
	private String businessKey;// 业务Key(事务Key)
	private String producerStep;// ST00 事务处理中， ST01 事务已完成
	private String consumerStep;// ST00 事务处理中， ST01 事务已完成
}
