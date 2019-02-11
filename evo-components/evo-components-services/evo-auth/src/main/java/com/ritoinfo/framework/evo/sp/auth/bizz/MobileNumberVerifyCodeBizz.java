package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.sp.auth.api.IamApi;
import com.ritoinfo.framework.evo.sp.auth.api.model.UserDetailsDto;
import com.ritoinfo.framework.evo.sp.auth.exception.UserNotFoundException;
import com.ritoinfo.framework.evo.sp.auth.exception.VerifyCodeInvalidException;
import com.ritoinfo.framework.evo.sp.auth.exception.VerifyCodeSendException;
import com.ritoinfo.framework.evo.sp.auth.model.MobileNumberParam;
import com.ritoinfo.framework.evo.sp.auth.model.VerifyCodeDto;
import com.ritoinfo.framework.evo.sp.auth.model.VerifyCodeParam;
import com.ritoinfo.framework.evo.sp.sms.api.SmsApi;
import com.ritoinfo.framework.evo.sp.sms.dto.SmsDto;
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
			UserDetailsDto userDetailsDto = iamApi.getByMobileNumber(mobileNumber).getData();
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
