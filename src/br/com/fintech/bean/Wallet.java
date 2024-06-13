package br.com.fintech.bean;

public class Wallet {
    private String name;
    private String description;
    private int userCode;
    private int code;
    
    public Wallet(String name, String description, int userCode) {
    	super();
    	this.setName(name);
    	this.setDescription(description);
    	this.setUserCode(userCode);
    }
    
    public Wallet(int code, int userCode, String name, String description) {
    	super();
    	this.setCode(code);
    	this.setName(name);
    	this.setDescription(description);
    	this.setUserCode(userCode);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}