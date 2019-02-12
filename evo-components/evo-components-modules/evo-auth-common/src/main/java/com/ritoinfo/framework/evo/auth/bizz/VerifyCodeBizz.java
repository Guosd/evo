package com.ritoinfo.framework.evo.auth.bizz;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.AlgorithmUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.auth.api.IamApi;
import com.ritoinfo.framework.evo.auth.api.model.UserDetailsDto;
import com.ritoinfo.framework.evo.auth.assist.RedisAssist;
import com.ritoinfo.framework.evo.auth.config.VerifyCodeConfig;
import com.ritoinfo.framework.evo.auth.exception.MobileNumberNotFoundException;
import com.ritoinfo.framework.evo.auth.model.VerifyCodeDto;
import com.ritoinfo.framework.evo.auth.model.VerifyCodeParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2019-01-17 09:16
 */
@Slf4j
@Service
public class VerifyCodeBizz {
	@Autowired
	private IamApi iamApi;
	@Autowired
	private RedisAssist redisAssist;
	@Autowired
	private VerifyCodeConfig verifyCodeConfig;

	public VerifyCodeDto get(VerifyCodeParam verifyCodeParam) {
		String mobileNumber = verifyCodeParam.getMobileNumber();
		String verifyCode = calcVerifyCode();

		redisAssist.saveVerifyCode(Const.VERIFY_CODE_SIGN_IN, mobileNumber, verifyCode);
		redisAssist.saveVerifyCode(Const.VERIFY_CODE_SIGN_UP, mobileNumber, verifyCode);

		return VerifyCodeDto.builder().verifyCode(verifyCode).existUser(getUser(mobileNumber) != null).build();
	}

	public String getForSignIn(VerifyCodeParam verifyCodeParam) {
		String mobileNumber = verifyCodeParam.getMobileNumber();
		UserDetailsDto userDetailsDto = getUser(mobileNumber);
		if (userDetailsDto == null) {
			throw new MobileNumberNotFoundException(mobileNumber);
		}

		String verifyCode = calcVerifyCode();
		redisAssist.saveVerifyCode(Const.VERIFY_CODE_SIGN_IN, mobileNumber, verifyCode);

		return verifyCode;
	}

	public String getForSignUp(VerifyCodeParam verifyCodeParam) {
		String mobileNumber = verifyCodeParam.getMobileNumber();

		String verifyCode = calcVerifyCode();
		redisAssist.saveVerifyCode(Const.VERIFY_CODE_SIGN_UP, mobileNumber, verifyCode);

		return verifyCode;
	}

	public boolean check(String mobileNumber, String verifyCode) {
		return check(null, mobileNumber, verifyCode);
	}

	public boolean check(String bizzFlag, String mobileNumber, String verifyCode) {
		boolean result = StringUtil.isBlank(bizzFlag)
				? checkVerifyCode(Const.VERIFY_CODE_SIGN_IN, mobileNumber, verifyCode) || checkVerifyCode(Const.VERIFY_CODE_SIGN_UP, mobileNumber, verifyCode)
				: checkVerifyCode(bizzFlag, mobileNumber, verifyCode);

		if (result) {
			redisAssist.deleteVerifyCode(mobileNumber);
		}

		return result;
	}

	private String calcVerifyCode() {
		return Const.VERIFY_CODE_TYPE_RANDOM.equals(verifyCodeConfig.getType()) ? AlgorithmUtil.randomNumber(verifyCodeConfig.getLength()) : verifyCodeConfig.getValue();
	}

	private UserDetailsDto getUser(String mobileNumber) {
		return iamApi.getByMobileNumber(mobileNumber).getData();
	}

	private boolean checkVerifyCode(String bizzFlag, String mobileNumber, String verifyCode) {
		return verifyCode.equals(redisAssist.getVerifyCode(bizzFlag, mobileNumber));
	}
}
