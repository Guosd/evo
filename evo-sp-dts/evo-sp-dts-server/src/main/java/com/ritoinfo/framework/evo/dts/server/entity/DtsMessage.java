package com.ritoinfo.framework.evo.dts.server.entity;

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
@Table(name = "dts_message", catalog = "evo_framework")
public class DtsMessage extends BaseMapperEntity<Long> {
	@Id
	@KeySql(useGeneratedKeys = true)
	private Long id;
	private String logMessageKey;// 日志消息Key
	private String messageKey;// 消息Key
	private String businessKey;// 业务Key(事务Key)
	private String content;// 业务参数 JSON
	private String producer;// 事务生产者 spring.application.name
	private String consumer;// 事务消费者 spring.application.name
	private String role;// 角色 RO00: 事务生产者 RO01: 事务消费者
	private String step;// 阶段 ST00: 事务处理中 ST01: 事务已完成
}
