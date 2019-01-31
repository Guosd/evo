package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.sp.auth.api.RbacApi;
import com.ritoinfo.framework.evo.sp.auth.api.model.RbacDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-12-28 18:18
 */
@Service
public class RbacBizz {
	@Autowired
	private RbacApi rbacApi;

	public boolean check(RbacDto rbacDto) {
		return rbacApi.check(rbacDto).getData();
	}
}
