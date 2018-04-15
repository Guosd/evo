package com.ritoinfo.framework.evo.sp.sys.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * User: Kyll
 * Date: 2018-03-04 18:06
 */
@FeignClient(value = "evo-sp-sys", path = "/role")
public interface RoleApi {
}
