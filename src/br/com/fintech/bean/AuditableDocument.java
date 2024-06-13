package br.com.fintech.bean;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class AuditableDocument {
	
	private int code;
	private OffsetDateTime createdDate;
	protected ZoneOffset zoneOffset = ZoneOffset.of("-03:00");

	public AuditableDocument(int code, OffsetDateTime createdDate) {
		setCode(code);
		setCreatedDate(createdDate);
	}
	
	public AuditableDocument() {}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public OffsetDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(OffsetDateTime createdDate) {
		this.createdDate = createdDate;
	}

}
