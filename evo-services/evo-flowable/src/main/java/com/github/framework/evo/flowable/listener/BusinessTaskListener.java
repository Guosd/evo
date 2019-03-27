package com.github.framework.evo.flowable.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2019-03-27 09:59
 */
@Component
public class BusinessTaskListener implements TaskListener {
	@Override
	public void notify(DelegateTask delegateTask) {

	}
}


