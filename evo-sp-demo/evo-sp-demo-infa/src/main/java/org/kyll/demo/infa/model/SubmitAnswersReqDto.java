package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 答题接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SubmitAnswersReqDto extends P2PReqDto {
	private Long userId;// 用户id 
	private String optionIds;// 答案ids ，号拼接
}
