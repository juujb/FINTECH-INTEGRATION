package br.com.fintech.bean;

import java.time.OffsetDateTime;

public class Expense extends Transference {
	private int installments = 1;
	private boolean paidStatus = false;
	private OffsetDateTime dueDate;
	
	public Expense(double value, boolean fixed, String description, int installments, OffsetDateTime dueDate) {
		super(value, description, fixed);
		setInstallments(installments);
		setDueDate(dueDate);
	}
	
	public Expense(int code, int userCode, int walletCode, double value, String description, boolean fixed, boolean paidStatus, OffsetDateTime efetivationDate, OffsetDateTime createdDate, int installments, OffsetDateTime dueDate) {
		super(code, userCode, walletCode, value, description, fixed, efetivationDate, createdDate);
		setInstallments(installments);
		setDueDate(dueDate);
		setPaidStatus(paidStatus);
	}
	
	public Expense(int userCode, int walletCode, double value, String description, boolean fixed, boolean paidStatus, OffsetDateTime efetivationDate, int installments, OffsetDateTime dueDate) {
		super(userCode, walletCode, value, description, fixed, efetivationDate);
		setInstallments(installments);
		setDueDate(dueDate);
		setPaidStatus(paidStatus);
	}
	
	public boolean getPaidStatus() {
		return paidStatus;
	}
	
	public void setPaidStatus(boolean paidStatus) {
		this.paidStatus = paidStatus;
	}

	public int getInstallments() {
		return installments;
	}

	public void setInstallments(int installments) {
		this.installments = installments;
	}

	public OffsetDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(OffsetDateTime dueDate) {
		this.dueDate = dueDate;
	}
}
