package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.common.Config;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Kyll
 * Date: 2018-09-17 16:26
 */
@Service
public class TransactionProducerMessageBizz {
	public void send() {
		TransactionListener transactionListener = new TransactionListener() {
			private AtomicInteger transactionIndex = new AtomicInteger(0);
			private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

			@Override
			public LocalTransactionState executeLocalTransaction(Message message, Object o) {
				int value = transactionIndex.getAndIncrement();
				int status = value % 3;
				localTrans.put(message.getTransactionId(), status);
				return LocalTransactionState.UNKNOW;
			}

			@Override
			public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
				Integer status = localTrans.get(messageExt.getTransactionId());
				if (null != status) {
					switch (status) {
						case 0:
							return LocalTransactionState.UNKNOW;
						case 1:
							return LocalTransactionState.COMMIT_MESSAGE;
						case 2:
							return LocalTransactionState.ROLLBACK_MESSAGE;
					}
				}
				return LocalTransactionState.COMMIT_MESSAGE;
			}
		};

		TransactionMQProducer producer = new TransactionMQProducer("test_transaction_group");
		ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setName("client-transaction-msg-check-thread");
				return thread;
			}
		});

		producer.setNamesrvAddr(Config.NAMESRV_ADDR);
		producer.setExecutorService(executorService);
		producer.setTransactionListener(transactionListener);
		try {
			producer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}

		String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
		for (int i = 0; i < 10; i++) {
			try {
				Message msg =
						new Message("TopicTest1234", tags[i % tags.length], "KEY" + i,
								("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
				SendResult sendResult = producer.sendMessageInTransaction(msg, null);
				System.out.printf("%s%n", sendResult);

				Thread.sleep(10);
			} catch (MQClientException | UnsupportedEncodingException | InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 100000; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		producer.shutdown();
	}
}
