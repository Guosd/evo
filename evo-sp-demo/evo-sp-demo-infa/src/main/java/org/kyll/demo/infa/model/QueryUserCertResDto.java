package org.kyll.demo.infa.model;

import lombok.Data;

/**
 * 查询实名认证接口响应对象
 */
@Data
public class QueryUserCertResDto {
	private String realityName;// 姓名 
	private String idNumber;// 身份证号 
}
