package com.dianzishangwu.yihunli.signlogin.domain;

public class MerchantSignUpRequest extends SignUpRequest{

	private String tradeMark;
	private String companyName;
	private String companyIntroduce;
	private String businessScope;
	private String serviceTel;
	private String accountSubject;
	
	public String getAccountSubject() {
		return accountSubject;
	}
	public void setAccountSubject(String accountSubject) {
		this.accountSubject = accountSubject;
	}
	public String getTradeMark() {
		return tradeMark;
	}
	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyIntroduce() {
		return companyIntroduce;
	}
	public void setCompanyIntroduce(String companyIntroduce) {
		this.companyIntroduce = companyIntroduce;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getServiceTel() {
		return serviceTel;
	}
	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}

}
