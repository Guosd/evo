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
@Table(name = "dts_log", catalog = "evo_framework")
public class DtsLog extends BaseMapperEntity<Long> {
	@Id
	@KeySql(useGeneratedKeys = true)
	private Long id;
	private String messageKey;// 消息Key
	private String businessKey;// 业务Key(事务Key)
	private String source;// 事务生产者
	private String target;// 事务消费者
	private String arg;// 业务参数 JSON
	private String sourceStatus;// SS00 服务生产者处理中， SS01 服务生产者已完成
	private String targetStatus;// ST00 服务消费者处理中， ST01 服务消费者已完成
}
