package org.kyll.demo.infa.rest;

import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;

import lombok.extern.slf4j.Slf4j;
import org.kyll.demo.infa.bizz.P2PBizz;
import org.kyll.demo.infa.model.LoginReqDto;
import org.kyll.demo.infa.model.LoginResDto;
import org.kyll.demo.infa.model.GetUserInfoReqDto;
import org.kyll.demo.infa.model.GetUserInfoResDto;
import org.kyll.demo.infa.model.QueryUserCertReqDto;
import org.kyll.demo.infa.model.QueryUserCertResDto;
import org.kyll.demo.infa.model.BankVerifyReqDto;
import org.kyll.demo.infa.model.BankVerifyResDto;
import org.kyll.demo.infa.model.BankBandReqDto;
import org.kyll.demo.infa.model.BankBandResDto;
import org.kyll.demo.infa.model.GetProvincesReqDto;
import org.kyll.demo.infa.model.GetProvincesResDto;
import org.kyll.demo.infa.model.GetCityiesReqDto;
import org.kyll.demo.infa.model.GetCityiesResDto;
import org.kyll.demo.infa.model.GetBanksReqDto;
import org.kyll.demo.infa.model.GetBanksResDto;
import org.kyll.demo.infa.model.GetBranchBanksReqDto;
import org.kyll.demo.infa.model.GetBranchBanksResDto;
import org.kyll.demo.infa.model.SetPayPasswordReqDto;
import org.kyll.demo.infa.model.SetPayPasswordResDto;
import org.kyll.demo.infa.model.GetAssetInfoReqDto;
import org.kyll.demo.infa.model.GetAssetInfoResDto;
import org.kyll.demo.infa.model.GetPlansReqDto;
import org.kyll.demo.infa.model.GetPlansResDto;
import org.kyll.demo.infa.model.GetPlanDetailReqDto;
import org.kyll.demo.infa.model.GetPlanDetailResDto;
import org.kyll.demo.infa.model.InvestPlanReqDto;
import org.kyll.demo.infa.model.InvestPlanResDto;
import org.kyll.demo.infa.model.GetBandBanksReqDto;
import org.kyll.demo.infa.model.GetBandBanksResDto;
import org.kyll.demo.infa.model.UnBandBankReqDto;
import org.kyll.demo.infa.model.UnBandBankResDto;
import org.kyll.demo.infa.model.GetTradeDetailsReqDto;
import org.kyll.demo.infa.model.GetTradeDetailsResDto;
import org.kyll.demo.infa.model.GetMyPlansReqDto;
import org.kyll.demo.infa.model.GetMyPlansResDto;
import org.kyll.demo.infa.model.GetRechargeCodeReqDto;
import org.kyll.demo.infa.model.GetRechargeCodeResDto;
import org.kyll.demo.infa.model.RechargeVerifyReqDto;
import org.kyll.demo.infa.model.RechargeVerifyResDto;
import org.kyll.demo.infa.model.WithdrawReqDto;
import org.kyll.demo.infa.model.WithdrawResDto;
import org.kyll.demo.infa.model.GetQuestionsReqDto;
import org.kyll.demo.infa.model.GetQuestionsResDto;
import org.kyll.demo.infa.model.SubmitAnswersReqDto;
import org.kyll.demo.infa.model.SubmitAnswersResDto;
import org.kyll.demo.infa.model.RegistReqDto;
import org.kyll.demo.infa.model.RegistResDto;
import org.kyll.demo.infa.model.ModifyPayPwdReqDto;
import org.kyll.demo.infa.model.ModifyPayPwdResDto;
import org.kyll.demo.infa.model.ResetPayPwdReqDto;
import org.kyll.demo.infa.model.ResetPayPwdResDto;
import org.kyll.demo.infa.model.ModifyMobileReqDto;
import org.kyll.demo.infa.model.ModifyMobileResDto;
import org.kyll.demo.infa.model.SetMobileReqDto;
import org.kyll.demo.infa.model.SetMobileResDto;
import org.kyll.demo.infa.model.ModifyLoginPwdReqDto;
import org.kyll.demo.infa.model.ModifyLoginPwdResDto;
import org.kyll.demo.infa.model.ResetLoginPwdReqDto;
import org.kyll.demo.infa.model.ResetLoginPwdResDto;
import org.kyll.demo.infa.model.base.P2PResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/p2p")
@RestController
public class P2PRest {
	@Autowired
	private P2PBizz p2pBizz;

	@PostMapping("/login")
	public ServiceResponse<LoginResDto> login(@RequestBody LoginReqDto loginReqDto) {
		P2PResDto<LoginResDto> p2pResDto = p2pBizz.login(loginReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getUserInfo")
	public ServiceResponse<GetUserInfoResDto> getUserInfo(@RequestBody GetUserInfoReqDto getUserInfoReqDto) {
		P2PResDto<GetUserInfoResDto> p2pResDto = p2pBizz.getUserInfo(getUserInfoReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/queryUserCert")
	public ServiceResponse<QueryUserCertResDto> queryUserCert(@RequestBody QueryUserCertReqDto queryUserCertReqDto) {
		P2PResDto<QueryUserCertResDto> p2pResDto = p2pBizz.queryUserCert(queryUserCertReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/bankVerify")
	public ServiceResponse<BankVerifyResDto> bankVerify(@RequestBody BankVerifyReqDto bankVerifyReqDto) {
		P2PResDto<BankVerifyResDto> p2pResDto = p2pBizz.bankVerify(bankVerifyReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/bankBand")
	public ServiceResponse<BankBandResDto> bankBand(@RequestBody BankBandReqDto bankBandReqDto) {
		P2PResDto<BankBandResDto> p2pResDto = p2pBizz.bankBand(bankBandReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getProvinces")
	public ServiceResponse<GetProvincesResDto> getProvinces(@RequestBody GetProvincesReqDto getProvincesReqDto) {
		P2PResDto<GetProvincesResDto> p2pResDto = p2pBizz.getProvinces(getProvincesReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getCityies")
	public ServiceResponse<GetCityiesResDto> getCityies(@RequestBody GetCityiesReqDto getCityiesReqDto) {
		P2PResDto<GetCityiesResDto> p2pResDto = p2pBizz.getCityies(getCityiesReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getBanks")
	public ServiceResponse<GetBanksResDto> getBanks(@RequestBody GetBanksReqDto getBanksReqDto) {
		P2PResDto<GetBanksResDto> p2pResDto = p2pBizz.getBanks(getBanksReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getBranchBanks")
	public ServiceResponse<GetBranchBanksResDto> getBranchBanks(@RequestBody GetBranchBanksReqDto getBranchBanksReqDto) {
		P2PResDto<GetBranchBanksResDto> p2pResDto = p2pBizz.getBranchBanks(getBranchBanksReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/setPayPassword")
	public ServiceResponse<SetPayPasswordResDto> setPayPassword(@RequestBody SetPayPasswordReqDto setPayPasswordReqDto) {
		P2PResDto<SetPayPasswordResDto> p2pResDto = p2pBizz.setPayPassword(setPayPasswordReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getAssetInfo")
	public ServiceResponse<GetAssetInfoResDto> getAssetInfo(@RequestBody GetAssetInfoReqDto getAssetInfoReqDto) {
		P2PResDto<GetAssetInfoResDto> p2pResDto = p2pBizz.getAssetInfo(getAssetInfoReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getPlans")
	public ServiceResponse<GetPlansResDto> getPlans(@RequestBody GetPlansReqDto getPlansReqDto) {
		P2PResDto<GetPlansResDto> p2pResDto = p2pBizz.getPlans(getPlansReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getPlanDetail")
	public ServiceResponse<GetPlanDetailResDto> getPlanDetail(@RequestBody GetPlanDetailReqDto getPlanDetailReqDto) {
		P2PResDto<GetPlanDetailResDto> p2pResDto = p2pBizz.getPlanDetail(getPlanDetailReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/investPlan")
	public ServiceResponse<InvestPlanResDto> investPlan(@RequestBody InvestPlanReqDto investPlanReqDto) {
		P2PResDto<InvestPlanResDto> p2pResDto = p2pBizz.investPlan(investPlanReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getBandBanks")
	public ServiceResponse<GetBandBanksResDto> getBandBanks(@RequestBody GetBandBanksReqDto getBandBanksReqDto) {
		P2PResDto<GetBandBanksResDto> p2pResDto = p2pBizz.getBandBanks(getBandBanksReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/unBandBank")
	public ServiceResponse<UnBandBankResDto> unBandBank(@RequestBody UnBandBankReqDto unBandBankReqDto) {
		P2PResDto<UnBandBankResDto> p2pResDto = p2pBizz.unBandBank(unBandBankReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getTradeDetails")
	public ServiceResponse<GetTradeDetailsResDto> getTradeDetails(@RequestBody GetTradeDetailsReqDto getTradeDetailsReqDto) {
		P2PResDto<GetTradeDetailsResDto> p2pResDto = p2pBizz.getTradeDetails(getTradeDetailsReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getMyPlans")
	public ServiceResponse<GetMyPlansResDto> getMyPlans(@RequestBody GetMyPlansReqDto getMyPlansReqDto) {
		P2PResDto<GetMyPlansResDto> p2pResDto = p2pBizz.getMyPlans(getMyPlansReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getRechargeCode")
	public ServiceResponse<GetRechargeCodeResDto> getRechargeCode(@RequestBody GetRechargeCodeReqDto getRechargeCodeReqDto) {
		P2PResDto<GetRechargeCodeResDto> p2pResDto = p2pBizz.getRechargeCode(getRechargeCodeReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/rechargeVerify")
	public ServiceResponse<RechargeVerifyResDto> rechargeVerify(@RequestBody RechargeVerifyReqDto rechargeVerifyReqDto) {
		P2PResDto<RechargeVerifyResDto> p2pResDto = p2pBizz.rechargeVerify(rechargeVerifyReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/withdraw")
	public ServiceResponse<WithdrawResDto> withdraw(@RequestBody WithdrawReqDto withdrawReqDto) {
		P2PResDto<WithdrawResDto> p2pResDto = p2pBizz.withdraw(withdrawReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/getQuestions")
	public ServiceResponse<GetQuestionsResDto> getQuestions(@RequestBody GetQuestionsReqDto getQuestionsReqDto) {
		P2PResDto<GetQuestionsResDto> p2pResDto = p2pBizz.getQuestions(getQuestionsReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/submitAnswers")
	public ServiceResponse<SubmitAnswersResDto> submitAnswers(@RequestBody SubmitAnswersReqDto submitAnswersReqDto) {
		P2PResDto<SubmitAnswersResDto> p2pResDto = p2pBizz.submitAnswers(submitAnswersReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/regist")
	public ServiceResponse<RegistResDto> regist(@RequestBody RegistReqDto registReqDto) {
		P2PResDto<RegistResDto> p2pResDto = p2pBizz.regist(registReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/modifyPayPwd")
	public ServiceResponse<ModifyPayPwdResDto> modifyPayPwd(@RequestBody ModifyPayPwdReqDto modifyPayPwdReqDto) {
		P2PResDto<ModifyPayPwdResDto> p2pResDto = p2pBizz.modifyPayPwd(modifyPayPwdReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/resetPayPwd")
	public ServiceResponse<ResetPayPwdResDto> resetPayPwd(@RequestBody ResetPayPwdReqDto resetPayPwdReqDto) {
		P2PResDto<ResetPayPwdResDto> p2pResDto = p2pBizz.resetPayPwd(resetPayPwdReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/modifyMobile")
	public ServiceResponse<ModifyMobileResDto> modifyMobile(@RequestBody ModifyMobileReqDto modifyMobileReqDto) {
		P2PResDto<ModifyMobileResDto> p2pResDto = p2pBizz.modifyMobile(modifyMobileReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/setMobile")
	public ServiceResponse<SetMobileResDto> setMobile(@RequestBody SetMobileReqDto setMobileReqDto) {
		P2PResDto<SetMobileResDto> p2pResDto = p2pBizz.setMobile(setMobileReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/modifyLoginPwd")
	public ServiceResponse<ModifyLoginPwdResDto> modifyLoginPwd(@RequestBody ModifyLoginPwdReqDto modifyLoginPwdReqDto) {
		P2PResDto<ModifyLoginPwdResDto> p2pResDto = p2pBizz.modifyLoginPwd(modifyLoginPwdReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

	@PostMapping("/resetLoginPwd")
	public ServiceResponse<ResetLoginPwdResDto> resetLoginPwd(@RequestBody ResetLoginPwdReqDto resetLoginPwdReqDto) {
		P2PResDto<ResetLoginPwdResDto> p2pResDto = p2pBizz.resetLoginPwd(resetLoginPwdReqDto);
		return ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());
	}

}
