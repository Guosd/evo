package org.kyll.demo.infa.bizz;

import lombok.extern.slf4j.Slf4j;
import org.kyll.demo.infa.api.P2PInfaApi;
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
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class P2PBizz {
	@Autowired
	private P2PInfaApi p2pInfaApi;

	public P2PResDto<LoginResDto> login(LoginReqDto loginReqDto) {
		log.info("调用P2P接口: " + loginReqDto);
		return p2pInfaApi.login(loginReqDto);
	}

	public P2PResDto<GetUserInfoResDto> getUserInfo(GetUserInfoReqDto getUserInfoReqDto) {
		log.info("调用P2P接口: " + getUserInfoReqDto);
		return p2pInfaApi.getUserInfo(getUserInfoReqDto);
	}

	public P2PResDto<QueryUserCertResDto> queryUserCert(QueryUserCertReqDto queryUserCertReqDto) {
		log.info("调用P2P接口: " + queryUserCertReqDto);
		return p2pInfaApi.queryUserCert(queryUserCertReqDto);
	}

	public P2PResDto<BankVerifyResDto> bankVerify(BankVerifyReqDto bankVerifyReqDto) {
		log.info("调用P2P接口: " + bankVerifyReqDto);
		return p2pInfaApi.bankVerify(bankVerifyReqDto);
	}

	public P2PResDto<BankBandResDto> bankBand(BankBandReqDto bankBandReqDto) {
		log.info("调用P2P接口: " + bankBandReqDto);
		return p2pInfaApi.bankBand(bankBandReqDto);
	}

	public P2PResDto<GetProvincesResDto> getProvinces(GetProvincesReqDto getProvincesReqDto) {
		log.info("调用P2P接口: " + getProvincesReqDto);
		return p2pInfaApi.getProvinces(getProvincesReqDto);
	}

	public P2PResDto<GetCityiesResDto> getCityies(GetCityiesReqDto getCityiesReqDto) {
		log.info("调用P2P接口: " + getCityiesReqDto);
		return p2pInfaApi.getCityies(getCityiesReqDto);
	}

	public P2PResDto<GetBanksResDto> getBanks(GetBanksReqDto getBanksReqDto) {
		log.info("调用P2P接口: " + getBanksReqDto);
		return p2pInfaApi.getBanks(getBanksReqDto);
	}

	public P2PResDto<GetBranchBanksResDto> getBranchBanks(GetBranchBanksReqDto getBranchBanksReqDto) {
		log.info("调用P2P接口: " + getBranchBanksReqDto);
		return p2pInfaApi.getBranchBanks(getBranchBanksReqDto);
	}

	public P2PResDto<SetPayPasswordResDto> setPayPassword(SetPayPasswordReqDto setPayPasswordReqDto) {
		log.info("调用P2P接口: " + setPayPasswordReqDto);
		return p2pInfaApi.setPayPassword(setPayPasswordReqDto);
	}

	public P2PResDto<GetAssetInfoResDto> getAssetInfo(GetAssetInfoReqDto getAssetInfoReqDto) {
		log.info("调用P2P接口: " + getAssetInfoReqDto);
		return p2pInfaApi.getAssetInfo(getAssetInfoReqDto);
	}

	public P2PResDto<GetPlansResDto> getPlans(GetPlansReqDto getPlansReqDto) {
		log.info("调用P2P接口: " + getPlansReqDto);
		return p2pInfaApi.getPlans(getPlansReqDto);
	}

	public P2PResDto<GetPlanDetailResDto> getPlanDetail(GetPlanDetailReqDto getPlanDetailReqDto) {
		log.info("调用P2P接口: " + getPlanDetailReqDto);
		return p2pInfaApi.getPlanDetail(getPlanDetailReqDto);
	}

	public P2PResDto<InvestPlanResDto> investPlan(InvestPlanReqDto investPlanReqDto) {
		log.info("调用P2P接口: " + investPlanReqDto);
		return p2pInfaApi.investPlan(investPlanReqDto);
	}

	public P2PResDto<GetBandBanksResDto> getBandBanks(GetBandBanksReqDto getBandBanksReqDto) {
		log.info("调用P2P接口: " + getBandBanksReqDto);
		return p2pInfaApi.getBandBanks(getBandBanksReqDto);
	}

	public P2PResDto<UnBandBankResDto> unBandBank(UnBandBankReqDto unBandBankReqDto) {
		log.info("调用P2P接口: " + unBandBankReqDto);
		return p2pInfaApi.unBandBank(unBandBankReqDto);
	}

	public P2PResDto<GetTradeDetailsResDto> getTradeDetails(GetTradeDetailsReqDto getTradeDetailsReqDto) {
		log.info("调用P2P接口: " + getTradeDetailsReqDto);
		return p2pInfaApi.getTradeDetails(getTradeDetailsReqDto);
	}

	public P2PResDto<GetMyPlansResDto> getMyPlans(GetMyPlansReqDto getMyPlansReqDto) {
		log.info("调用P2P接口: " + getMyPlansReqDto);
		return p2pInfaApi.getMyPlans(getMyPlansReqDto);
	}

	public P2PResDto<GetRechargeCodeResDto> getRechargeCode(GetRechargeCodeReqDto getRechargeCodeReqDto) {
		log.info("调用P2P接口: " + getRechargeCodeReqDto);
		return p2pInfaApi.getRechargeCode(getRechargeCodeReqDto);
	}

	public P2PResDto<RechargeVerifyResDto> rechargeVerify(RechargeVerifyReqDto rechargeVerifyReqDto) {
		log.info("调用P2P接口: " + rechargeVerifyReqDto);
		return p2pInfaApi.rechargeVerify(rechargeVerifyReqDto);
	}

	public P2PResDto<WithdrawResDto> withdraw(WithdrawReqDto withdrawReqDto) {
		log.info("调用P2P接口: " + withdrawReqDto);
		return p2pInfaApi.withdraw(withdrawReqDto);
	}

	public P2PResDto<GetQuestionsResDto> getQuestions(GetQuestionsReqDto getQuestionsReqDto) {
		log.info("调用P2P接口: " + getQuestionsReqDto);
		return p2pInfaApi.getQuestions(getQuestionsReqDto);
	}

	public P2PResDto<SubmitAnswersResDto> submitAnswers(SubmitAnswersReqDto submitAnswersReqDto) {
		log.info("调用P2P接口: " + submitAnswersReqDto);
		return p2pInfaApi.submitAnswers(submitAnswersReqDto);
	}

	public P2PResDto<RegistResDto> regist(RegistReqDto registReqDto) {
		log.info("调用P2P接口: " + registReqDto);
		return p2pInfaApi.regist(registReqDto);
	}

	public P2PResDto<ModifyPayPwdResDto> modifyPayPwd(ModifyPayPwdReqDto modifyPayPwdReqDto) {
		log.info("调用P2P接口: " + modifyPayPwdReqDto);
		return p2pInfaApi.modifyPayPwd(modifyPayPwdReqDto);
	}

	public P2PResDto<ResetPayPwdResDto> resetPayPwd(ResetPayPwdReqDto resetPayPwdReqDto) {
		log.info("调用P2P接口: " + resetPayPwdReqDto);
		return p2pInfaApi.resetPayPwd(resetPayPwdReqDto);
	}

	public P2PResDto<ModifyMobileResDto> modifyMobile(ModifyMobileReqDto modifyMobileReqDto) {
		log.info("调用P2P接口: " + modifyMobileReqDto);
		return p2pInfaApi.modifyMobile(modifyMobileReqDto);
	}

	public P2PResDto<SetMobileResDto> setMobile(SetMobileReqDto setMobileReqDto) {
		log.info("调用P2P接口: " + setMobileReqDto);
		return p2pInfaApi.setMobile(setMobileReqDto);
	}

	public P2PResDto<ModifyLoginPwdResDto> modifyLoginPwd(ModifyLoginPwdReqDto modifyLoginPwdReqDto) {
		log.info("调用P2P接口: " + modifyLoginPwdReqDto);
		return p2pInfaApi.modifyLoginPwd(modifyLoginPwdReqDto);
	}

	public P2PResDto<ResetLoginPwdResDto> resetLoginPwd(ResetLoginPwdReqDto resetLoginPwdReqDto) {
		log.info("调用P2P接口: " + resetLoginPwdReqDto);
		return p2pInfaApi.resetLoginPwd(resetLoginPwdReqDto);
	}

}
