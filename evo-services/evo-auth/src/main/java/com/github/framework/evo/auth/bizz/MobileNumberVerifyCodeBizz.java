package com.github.framework.evo.auth.bizz;

import com.github.framework.evo.auth.api.IamApi;
import com.github.framework.evo.auth.exception.UserNotFoundException;
import com.github.framework.evo.auth.exception.VerifyCodeInvalidException;
import com.github.framework.evo.auth.exception.VerifyCodeSendException;
import com.github.framework.evo.auth.model.MobileNumberParam;
import com.github.framework.evo.auth.model.UserDetailsDto;
import com.github.framework.evo.auth.model.VerifyCodeDto;
import com.github.framework.evo.auth.model.VerifyCodeParam;
import com.github.framework.evo.sms.api.SmsApi;
import com.github.framework.evo.sms.dto.SmsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-03-08 14:33
 */
@Slf4j
@Service
public class MobileNumberVerifyCodeBizz {
	@Autowired
	private IamApi iamApi;
	@Autowired
	private SmsApi smsApi;
	@Autowired
	private VerifyCodeBizz verifyCodeBizz;
	@Autowired
	private TokenBizz tokenBizz;

	public VerifyCodeDto get(VerifyCodeParam verifyCodeParam) {
		VerifyCodeDto verifyCodeDto = verifyCodeBizz.get(verifyCodeParam);
		sendCode(verifyCodeParam.getMobileNumber(), verifyCodeDto.getVerifyCode());
		return verifyCodeDto;
	}

	public String getForSignIn(VerifyCodeParam verifyCodeParam) {
		String verifyCode = verifyCodeBizz.getForSignIn(verifyCodeParam);
		sendCode(verifyCodeParam.getMobileNumber(), verifyCode);
		return verifyCode;
	}

	public String getForSignUp(VerifyCodeParam verifyCodeParam) {
		String verifyCode = verifyCodeBizz.getForSignUp(verifyCodeParam);
		sendCode(verifyCodeParam.getMobileNumber(), verifyCode);
		return verifyCode;
	}

	public String login(MobileNumberParam loginDto, String remoteAddr) {
		String mobileNumber = loginDto.getMobileNumber();
		String verifyCode = loginDto.getVerifyCode();

		if (verifyCodeBizz.check(mobileNumber, verifyCode)) {
			UserDetailsDto userDetailsDto = iamApi.getByMobileNumber(mobileNumber);
			if (userDetailsDto == null) {
				throw new UserNotFoundException(mobileNumber);
			}

			iamApi.updateLoginInfo(userDetailsDto.getId(), remoteAddr);
			return tokenBizz.createToken(userDetailsDto);
		}

		throw new VerifyCodeInvalidException(verifyCode);
	}

	private void sendCode(String mobileNumber, String verifyCode) {
		log.info("发送验证码: {}, {}", mobileNumber, verifyCode);

		try {
			smsApi.send(SmsDto.builder().phoneNo(mobileNumber).content(verifyCode).build());
		} catch (Exception e) {
			throw new VerifyCodeSendException("验证码发送失败", e);
		}
	}
}
