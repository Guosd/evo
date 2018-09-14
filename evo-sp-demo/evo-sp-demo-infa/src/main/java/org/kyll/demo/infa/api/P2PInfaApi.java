package org.kyll.demo.infa.api;

import org.kyll.demo.infa.model.BankBandReqDto;
import org.kyll.demo.infa.model.BankBandResDto;
import org.kyll.demo.infa.model.BankVerifyReqDto;
import org.kyll.demo.infa.model.BankVerifyResDto;
import org.kyll.demo.infa.model.GetAssetInfoReqDto;
import org.kyll.demo.infa.model.GetAssetInfoResDto;
import org.kyll.demo.infa.model.GetBandBanksReqDto;
import org.kyll.demo.infa.model.GetBandBanksResDto;
import org.kyll.demo.infa.model.GetBanksReqDto;
import org.kyll.demo.infa.model.GetBanksResDto;
import org.kyll.demo.infa.model.GetBranchBanksReqDto;
import org.kyll.demo.infa.model.GetBranchBanksResDto;
import org.kyll.demo.infa.model.GetCityiesReqDto;
import org.kyll.demo.infa.model.GetCityiesResDto;
import org.kyll.demo.infa.model.GetMyPlansReqDto;
import org.kyll.demo.infa.model.GetMyPlansResDto;
import org.kyll.demo.infa.model.GetPlanDetailReqDto;
import org.kyll.demo.infa.model.GetPlanDetailResDto;
import org.kyll.demo.infa.model.GetPlansReqDto;
import org.kyll.demo.infa.model.GetPlansResDto;
import org.kyll.demo.infa.model.GetProvincesReqDto;
import org.kyll.demo.infa.model.GetProvincesResDto;
import org.kyll.demo.infa.model.GetQuestionsReqDto;
import org.kyll.demo.infa.model.GetQuestionsResDto;
import org.kyll.demo.infa.model.GetRechargeCodeReqDto;
import org.kyll.demo.infa.model.GetRechargeCodeResDto;
import org.kyll.demo.infa.model.GetTradeDetailsReqDto;
import org.kyll.demo.infa.model.GetTradeDetailsResDto;
import org.kyll.demo.infa.model.GetUserInfoReqDto;
import org.kyll.demo.infa.model.GetUserInfoResDto;
import org.kyll.demo.infa.model.InvestPlanReqDto;
import org.kyll.demo.infa.model.InvestPlanResDto;
import org.kyll.demo.infa.model.LoginReqDto;
import org.kyll.demo.infa.model.LoginResDto;
import org.kyll.demo.infa.model.ModifyLoginPwdReqDto;
import org.kyll.demo.infa.model.ModifyLoginPwdResDto;
import org.kyll.demo.infa.model.ModifyMobileReqDto;
import org.kyll.demo.infa.model.ModifyMobileResDto;
import org.kyll.demo.infa.model.ModifyPayPwdReqDto;
import org.kyll.demo.infa.model.ModifyPayPwdResDto;
import org.kyll.demo.infa.model.QueryUserCertReqDto;
import org.kyll.demo.infa.model.QueryUserCertResDto;
import org.kyll.demo.infa.model.RechargeVerifyReqDto;
import org.kyll.demo.infa.model.RechargeVerifyResDto;
import org.kyll.demo.infa.model.RegistReqDto;
import org.kyll.demo.infa.model.RegistResDto;
import org.kyll.demo.infa.model.ResetLoginPwdReqDto;
import org.kyll.demo.infa.model.ResetLoginPwdResDto;
import org.kyll.demo.infa.model.ResetPayPwdReqDto;
import org.kyll.demo.infa.model.ResetPayPwdResDto;
import org.kyll.demo.infa.model.SetMobileReqDto;
import org.kyll.demo.infa.model.SetMobileResDto;
import org.kyll.demo.infa.model.SetPayPasswordReqDto;
import org.kyll.demo.infa.model.SetPayPasswordResDto;
import org.kyll.demo.infa.model.SubmitAnswersReqDto;
import org.kyll.demo.infa.model.SubmitAnswersResDto;
import org.kyll.demo.infa.model.UnBandBankReqDto;
import org.kyll.demo.infa.model.UnBandBankResDto;
import org.kyll.demo.infa.model.WithdrawReqDto;
import org.kyll.demo.infa.model.WithdrawResDto;
import org.kyll.demo.infa.model.base.P2PResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "", name = "p2p")
public interface P2PInfaApi {
	@PostMapping
	P2PResDto<LoginResDto> login(LoginReqDto loginReqDto);

	@PostMapping
	P2PResDto<GetUserInfoResDto> getUserInfo(GetUserInfoReqDto getUserInfoReqDto);

	@PostMapping
	P2PResDto<QueryUserCertResDto> queryUserCert(QueryUserCertReqDto queryUserCertReqDto);

	@PostMapping
	P2PResDto<BankVerifyResDto> bankVerify(BankVerifyReqDto bankVerifyReqDto);

	@PostMapping
	P2PResDto<BankBandResDto> bankBand(BankBandReqDto bankBandReqDto);

	@PostMapping
	P2PResDto<GetProvincesResDto> getProvinces(GetProvincesReqDto getProvincesReqDto);

	@PostMapping
	P2PResDto<GetCityiesResDto> getCityies(GetCityiesReqDto getCityiesReqDto);

	@PostMapping
	P2PResDto<GetBanksResDto> getBanks(GetBanksReqDto getBanksReqDto);

	@PostMapping
	P2PResDto<GetBranchBanksResDto> getBranchBanks(GetBranchBanksReqDto getBranchBanksReqDto);

	@PostMapping
	P2PResDto<SetPayPasswordResDto> setPayPassword(SetPayPasswordReqDto setPayPasswordReqDto);

	@PostMapping
	P2PResDto<GetAssetInfoResDto> getAssetInfo(GetAssetInfoReqDto getAssetInfoReqDto);

	@PostMapping
	P2PResDto<GetPlansResDto> getPlans(GetPlansReqDto getPlansReqDto);

	@PostMapping
	P2PResDto<GetPlanDetailResDto> getPlanDetail(GetPlanDetailReqDto getPlanDetailReqDto);

	@PostMapping
	P2PResDto<InvestPlanResDto> investPlan(InvestPlanReqDto investPlanReqDto);

	@PostMapping
	P2PResDto<GetBandBanksResDto> getBandBanks(GetBandBanksReqDto getBandBanksReqDto);

	@PostMapping
	P2PResDto<UnBandBankResDto> unBandBank(UnBandBankReqDto unBandBankReqDto);

	@PostMapping
	P2PResDto<GetTradeDetailsResDto> getTradeDetails(GetTradeDetailsReqDto getTradeDetailsReqDto);

	@PostMapping
	P2PResDto<GetMyPlansResDto> getMyPlans(GetMyPlansReqDto getMyPlansReqDto);

	@PostMapping
	P2PResDto<GetRechargeCodeResDto> getRechargeCode(GetRechargeCodeReqDto getRechargeCodeReqDto);

	@PostMapping
	P2PResDto<RechargeVerifyResDto> rechargeVerify(RechargeVerifyReqDto rechargeVerifyReqDto);

	@PostMapping
	P2PResDto<WithdrawResDto> withdraw(WithdrawReqDto withdrawReqDto);

	@PostMapping
	P2PResDto<GetQuestionsResDto> getQuestions(GetQuestionsReqDto getQuestionsReqDto);

	@PostMapping
	P2PResDto<SubmitAnswersResDto> submitAnswers(SubmitAnswersReqDto submitAnswersReqDto);

	@PostMapping
	P2PResDto<RegistResDto> regist(RegistReqDto registReqDto);

	@PostMapping
	P2PResDto<ModifyPayPwdResDto> modifyPayPwd(ModifyPayPwdReqDto modifyPayPwdReqDto);

	@PostMapping
	P2PResDto<ResetPayPwdResDto> resetPayPwd(ResetPayPwdReqDto resetPayPwdReqDto);

	@PostMapping
	P2PResDto<ModifyMobileResDto> modifyMobile(ModifyMobileReqDto modifyMobileReqDto);

	@PostMapping
	P2PResDto<SetMobileResDto> setMobile(SetMobileReqDto setMobileReqDto);

	@PostMapping
	P2PResDto<ModifyLoginPwdResDto> modifyLoginPwd(ModifyLoginPwdReqDto modifyLoginPwdReqDto);

	@PostMapping
	P2PResDto<ResetLoginPwdResDto> resetLoginPwd(ResetLoginPwdReqDto resetLoginPwdReqDto);

}
