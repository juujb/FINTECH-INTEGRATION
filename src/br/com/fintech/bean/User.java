package br.com.fintech.bean;

import br.com.fintech.utils.CryptoUtils;

public class User {
	private int userCode;
    private String name; 
    private String email;
    private String password;

    public User() {
    	super();
    }
    
    public User (int userCode, String name, String email, String password) {
    	super();
        this.name = name;
        this.email = email;
        setUserCode(userCode);
        setPassword(password);
    }
    
    public User (String name, String email, String password) {
    	super();
        this.name = name;
        this.email = email;
        setPassword(password);
    }
    
    public User(String email, String password) {
    	this.email = email;
    	setPassword(password);
    }
    
    void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		try {
			this.password = CryptoUtils.encrypt(password);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

}
