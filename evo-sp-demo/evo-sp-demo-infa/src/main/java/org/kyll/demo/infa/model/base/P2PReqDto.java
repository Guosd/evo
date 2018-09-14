package org.kyll.demo.infa.model.base;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2018-09-02 14:11
 */
@Data
public class P2PReqDto {
	private String funcode;// 调用接口标识，不单独配置请求URl，通过该参数区别接口类型
}
