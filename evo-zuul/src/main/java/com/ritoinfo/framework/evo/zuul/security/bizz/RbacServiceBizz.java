package com.ritoinfo.framework.evo.zuul.security.bizz;

import com.ritoinfo.framework.evo.zuul.security.api.RbacApi;
import com.ritoinfo.framework.evo.zuul.security.api.model.RbacDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-12-28 18:18
 */
@Service
public class RbacServiceBizz {
	@Autowired
	private RbacApi rbacApi;

	public boolean validate() {
		Boolean data = rbacApi.validate(new RbacDto()).getData();
		return data == null ? Boolean.FALSE : data;
	}
}
