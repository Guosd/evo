package com.github.framework.evo.flowable.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2019-03-26 17:09
 */
@Slf4j
@Component
public class TaskStrategyListener implements TaskListener {
	@Override
	public void notify(DelegateTask delegateTask) {

	}
}
