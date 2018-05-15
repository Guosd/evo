package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.common.Const;
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

	public String getCode(MobileCodeDto mobileCodeDto) {
		String mobileNumber = mobileCodeDto.getMobileNumber();

		String verifyCode = AlgorithmUtil.randomNumber(6);
		//	smsApi.send() TODO
		redisService.set(RedisKeyAssist.generate("VERIFY_CODE_" + Const.VERIFY_CODE_SIGN_IN, mobileNumber), verifyCode, 60 * 1000L);
		redisService.set(RedisKeyAssist.generate("VERIFY_CODE_" + Const.VERIFY_CODE_SIGN_UP, mobileNumber), verifyCode, 60 * 1000L);

		return verifyCode;// TODO 生产环境去掉返回值
	}

	public String getCodeForSignIn(MobileCodeDto mobileCodeDto) {
		UserDto userDto = userApi.getByMobileNumber(mobileCodeDto.getMobileNumber()).getData();
		if (userDto == null) {
			throw new MobileNumberNotFoundException(mobileCodeDto.getMobileNumber());
		}

		return generateCode(Const.VERIFY_CODE_SIGN_IN, userDto.getMobileNumber());
	}

	public String getCodeForSignUp(MobileCodeDto mobileCodeDto) {
		return generateCode(Const.VERIFY_CODE_SIGN_UP, mobileCodeDto.getMobileNumber());
	}

	public String authorize(MobileLoginDto loginDto, HttpServletRequest request) {
		String mobileNumber = loginDto.getMobileNumber();
		String verifyCode = loginDto.getVerifyCode();

		String verifyCodeSignIn = redisService.getString(RedisKeyAssist.generate("VERIFY_CODE_" + Const.VERIFY_CODE_SIGN_IN, mobileNumber));
		if (verifyCode.equals(verifyCodeSignIn)) {
			redisService.delete(RedisKeyAssist.generate("VERIFY_CODE_" + Const.VERIFY_CODE_SIGN_IN, mobileNumber));
			redisService.delete(RedisKeyAssist.generate("VERIFY_CODE_" + Const.VERIFY_CODE_SIGN_UP, mobileNumber));

			return generateToken(mobileNumber, request);
		} else {
			String verifyCodeSignUp = redisService.getString(RedisKeyAssist.generate("VERIFY_CODE_" + Const.VERIFY_CODE_SIGN_UP, mobileNumber));
			if (verifyCode.equals(verifyCodeSignUp)) {
				redisService.delete(RedisKeyAssist.generate("VERIFY_CODE_" + Const.VERIFY_CODE_SIGN_UP, mobileNumber));
				redisService.delete(RedisKeyAssist.generate("VERIFY_CODE_" + Const.VERIFY_CODE_SIGN_IN, mobileNumber));

				return generateToken(mobileNumber, request);
			} else {
				throw new VerifyCodeInvalidException(verifyCode);
			}
		}
	}

	private String generateCode(String type, String mobileNumber) {
		String verifyCode = AlgorithmUtil.randomNumber(6);
		//	smsApi.send() TODO
		redisService.set(RedisKeyAssist.generate("VERIFY_CODE_" + type, mobileNumber), verifyCode, 60 * 1000L);

		return verifyCode;// TODO 生产环境去掉返回值
	}

	private String generateToken(String mobileNumber, HttpServletRequest request) {
		UserDto userDto = userApi.getByMobileNumber(mobileNumber).getData();
		if (userDto == null) {
			throw new MobileNumberNotFoundException(mobileNumber);
		}

		String token = assistBizz.createAndSaveToken(userDto);
		assistBizz.updateLoginInfo(userDto, token, request);
		return token;
	}
}
