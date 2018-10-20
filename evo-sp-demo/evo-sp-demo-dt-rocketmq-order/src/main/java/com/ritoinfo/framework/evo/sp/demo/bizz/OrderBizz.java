package com.ritoinfo.framework.evo.sp.demo.bizz;

import com.ritoinfo.framework.evo.common.uitl.AlgorithmUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.dts.common.DtsTransaction;
import com.ritoinfo.framework.evo.dts.common.annotation.RocketMQTransactionProducer;
import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseMapperBizz;
import com.ritoinfo.framework.evo.sp.demo.dao.OrderDao;
import com.ritoinfo.framework.evo.sp.demo.dto.OrderDto;
import com.ritoinfo.framework.evo.sp.demo.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-07-12 13:13
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class OrderBizz extends BaseMapperBizz<OrderDao, Order, Long, OrderDto> implements DtsTransaction {
	private static int count = 1;

	/**
	 * 发送事务消息时的调用顺序 1，生成业务 Key 字符串。不需要直接调用
	 * @param namesrvAddr RocketMQ Namesrv 地址
	 * @param group 组
	 * @param topic 主题
	 * @param tags 标签
	 * @param target 下游服务 spring application name
	 * @param applicationName 本服务名称
	 * @return 业务 key 字符串
	 */
	@Override
	public String getBusinessKey(String namesrvAddr, String group, String topic, String tags, String target, String applicationName) {
		return applicationName + "_" + DateUtil.formatDatetimeCompact(DateUtil.now()) + "_" + AlgorithmUtil.uuid();
	}

	/**
	 * 发送事务消息时的调用顺序 2，封装额外的参数属性。不需要直接调用
	 * @param namesrvAddr RocketMQ Namesrv 地址
	 * @param group 组
	 * @param topic 主题
	 * @param tags 标签
	 * @param target 下游服务 spring application name
	 * @param applicationName 本服务名称
	 * @return 参数
	 */
	@Override
	public Object getArg(String namesrvAddr, String group, String topic, String tags, String target, String applicationName) {
		Map<String, Object> map = new HashMap<>();
		map.put("prop", "1");
		return map;
	}



	/**
	 * 发送事务消息时的调用顺序 3，事务发起入口，由于 RocketMQ 的事务消息机制，会在发送预处理消息阶段回调此业务逻辑方法，所以调用时请忽略返回值
	 * 参数 Message message 与 Object arg，可省略，如果有，则一定要保持此顺序放在参数列表最后
	 * @return LocalTransactionState.COMMIT_MESSAGE 提交事务,这意味着允许消费者使用该消息
	 *         LocalTransactionState.ROLLBACK_MESSAGE 回滚事务,这意味着消息将被删除和不允许消费
	 *         LocalTransactionState.UNKNOW 中间状态,这意味着MQ需要核对,以确定状态
	 */
	@RocketMQTransactionProducer(target = "evo-sp-demo-dt-rocketmq-account")
	@Transactional
	public LocalTransactionState saveLocal(String name, Message message, Object arg) {
		OrderDto orderDto = new OrderDto();
		orderDto.setName(name + " 订单 " + count++);
		this.create(orderDto);

		return LocalTransactionState.COMMIT_MESSAGE;
	}

	/**
	 * 发送事务消息时的调用顺序 4，此方法用于检查本地事务状态并响应 RocketMQ 检查请求。不需要直接调用
	 * @return LocalTransactionState 含义与 saveLocal 方法描述一致
	 */
	@Override
	public LocalTransactionState checkLocal(MessageExt messageExt) {
		return LocalTransactionState.COMMIT_MESSAGE;
	}
}
