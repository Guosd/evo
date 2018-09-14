package org.kyll.demo.infa.model.base;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2018-09-02 14:11
 */
@Data
public class P2PResDto<T> {
	private String status;
	private String msg;
	private T data;// 所有返回的数据放到data里面
}
