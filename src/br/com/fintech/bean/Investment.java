package br.com.fintech.bean;

import java.time.OffsetDateTime;

public class Investment extends Transference {
	private String investmentType;
	
	public Investment(double value, String description, boolean fixed, String investmentType) {
		super(value, description, fixed);
		this.setInvestmentType(investmentType);
	}

	public Investment(int code, int userCode, int walletCode, double value, String description, boolean fixed, OffsetDateTime efetivationDate, OffsetDateTime createdDate, String investmentType) {
		super(code, userCode, walletCode, value, description, fixed, efetivationDate, createdDate);
		this.setInvestmentType(investmentType);
	}

	public String getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}
}
