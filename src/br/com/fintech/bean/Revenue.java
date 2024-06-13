package br.com.fintech.bean;

import java.time.OffsetDateTime;

public class Revenue extends Transference {
	private String typeOfEntry;

	public Revenue(double value, String description, boolean fixed, String typeOfEntry) {
        super(value, description, fixed);
        this.setTypeOfEntry(typeOfEntry);
    }
	
	public Revenue(int code, int userCode, int walletCode, double value, String description, boolean fixed, OffsetDateTime efetivationDate, OffsetDateTime createdDate, String typeOfEntry) {
		super(code, userCode, walletCode, value, description, fixed, efetivationDate, createdDate);
		this.setTypeOfEntry(typeOfEntry);
	}
 
	public String getTypeOfEntry() {
		return typeOfEntry;
	}
	
	public void setTypeOfEntry(String typeOfEntry) {
		this.typeOfEntry = typeOfEntry;
	}
}