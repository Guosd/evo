package com.ritoinfo.framework.evo.common.uitl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: Kyll
 * Date: 2018-09-28 15:47
 */
public class ConcurrentUtil {
	public static ExecutorService createExecutorService(int corePoolSize, int maximumPoolSize, long keepAliveTime, int capacity, String threadName) {
		return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<>(capacity), r -> {
			Thread thread = new Thread(r);
			thread.setName(threadName);
			return thread;
		});
	}
}
