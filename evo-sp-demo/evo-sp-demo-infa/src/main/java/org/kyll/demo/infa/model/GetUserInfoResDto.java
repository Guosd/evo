package org.kyll.demo.infa.model;

import lombok.Data;

/**
 * 用户信息接口响应对象
 */
@Data
public class GetUserInfoResDto {
	private String mobile;// 绑定手机号 
	private Integer isPayPassword;// 是否设置交易密码 0否 1是
	private Integer isCreateBank;// 是否开通存管账户 0否  1是
	private Integer isAddBaseInfo;// 实名认证状态 未认证0、审核中、已认证1（？出借人认证没有审核中状态）
	private Integer isMobile;// 是否绑定手机号 0否 1是
	private Integer questionScore;// 问卷调查结果 没有调查过的为0 其他 正常分数返回提供规则
	private String realityName;// 姓名 
	private String idNumber;// 身份证号 
}
