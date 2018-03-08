package com.ritoinfo.framework.evo.sp.base.assist;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.sp.base.dto.BaseDto;
import com.ritoinfo.framework.evo.sp.base.entity.BaseEntity;
import com.ritoinfo.framework.evo.sp.base.exception.RestException;

/**
 * User: Kyll
 * Date: 2018-03-08 13:07
 */
public class BaseHelper {
	public static <T extends BaseEntity> T toEntity(BaseDto dto) {
		T entity = toEmptyEntity(dto);
		BeanUtil.copy(dto, entity);
		return entity;
	}

	public static <T extends BaseEntity> T toEmptyEntity(BaseDto dto) {
		String className = BeanUtil.getClassName(dto);
		if (className.endsWith("Dto")) {
			return BeanUtil.newInstance(className.substring(0, className.length() - 3));
		}

		throw new RestException(Const.RC_BASE_SPECIFICATION, "DTO对象类名称没有以Dto结尾");
	}
}
