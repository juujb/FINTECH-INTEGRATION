package br.com.fintech.bean;

import java.time.OffsetDateTime;

public abstract class Transference extends AuditableDocument {
	private int walletCode;
	private int userCode;
	private double value;
	private OffsetDateTime efetivationDate;
	private boolean fixed = false;
	private String description;
	
	public Transference(double value, String description, boolean fixed) {
		super();
		setValue(value);
		setDescription(description);
		setFixed(fixed);
	}
	
	public Transference(int code, int userCode, int walletCode, double value, String description, boolean fixed, OffsetDateTime efetivationDate, OffsetDateTime createdDate) {
		super(code, createdDate);
		setUserCode(userCode);
		setWalletCode(walletCode);
		setValue(value);
		this.efetivationDate = efetivationDate;
		setFixed(fixed);
		setDescription(description);
	}
	
	public Transference(int userCode, int walletCode, double value, String description, boolean fixed, OffsetDateTime efetivationDate) {
		setUserCode(userCode);
		setWalletCode(walletCode);
		setValue(value);
		this.efetivationDate = efetivationDate;
		setFixed(fixed);
		setDescription(description);
	}
	
	public OffsetDateTime getEfetivationDate() {
		return efetivationDate;
	}
	
	public void validateOperation() {
		if (efetivationDate == null)
			efetivationDate = OffsetDateTime.now(zoneOffset);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWalletCode() {
		return walletCode;
	}

	public void setWalletCode(int walletCode) {
		this.walletCode = walletCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	
}
