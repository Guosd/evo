package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.common.uitl.AlgorithmUtil;
import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import com.ritoinfo.framework.evo.sms.api.SmsApi;
import com.ritoinfo.framework.evo.sp.auth.assist.RedisKeyAssist;
import com.ritoinfo.framework.evo.sp.auth.dto.MobileCodeDto;
import com.ritoinfo.framework.evo.sp.auth.dto.MobileLoginDto;
import com.ritoinfo.framework.evo.sp.auth.exception.MobileNumberNotFoundException;
import com.ritoinfo.framework.evo.sp.auth.exception.VerifyCodeInvalidException;
import com.ritoinfo.framework.evo.sp.sys.api.UserApi;
import com.ritoinfo.framework.evo.sp.sys.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Kyll
 * Date: 2018-04-20 11:48
 */
@Slf4j
@Service
public class AuthBizz {
	@Autowired
	private UserApi userApi;
	@Autowired
	private SmsApi smsApi;
	@Autowired
	private RedisService redisService;
	@Autowired
	private AssistBizz assistBizz;

	public String generateCode(MobileCodeDto mobileCodeDto) {
		UserDto userDto = userApi.mobileNumber(mobileCodeDto.getMobileNumber()).getData();
		if (userDto == null) {
			throw new MobileNumberNotFoundException(mobileCodeDto.getMobileNumber());
		}

		String verifyCode = AlgorithmUtil.randomNumber(6);
	//	smsApi.send() TODO
		redisService.set(RedisKeyAssist.generate("YERIFY_CODE", userDto.getMobileNumber()), verifyCode, 60 * 1000L);

		return verifyCode;
	}

	public String authorize(MobileLoginDto loginDto, HttpServletRequest request) {
		String mobileNumber = loginDto.getMobileNumber();
		String verifyCode = loginDto.getVerifyCode();
		if (verifyCode.equals(redisService.getString(RedisKeyAssist.generate("YERIFY_CODE", mobileNumber)))) {
			UserDto userDto = userApi.mobileNumber(mobileNumber).getData();
			if (userDto == null) {
				throw new MobileNumberNotFoundException(mobileNumber);
			}

			redisService.delete(RedisKeyAssist.generate("YERIFY_CODE", mobileNumber));

			String token = assistBizz.createAndSaveToken(userDto);
			assistBizz.updateLoginInfo(userDto, token, request);
			return token;
		} else {
			throw new VerifyCodeInvalidException(verifyCode);
		}
	}
}
