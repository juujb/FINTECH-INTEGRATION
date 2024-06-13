package br.com.fintech.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class CryptoUtils {
	
	public static String encrypt(String password) throws Exception {
		MessageDigest md= MessageDigest.getInstance("MD5"); 
		md.update(password.getBytes("ISO-8859-1"));
		BigInteger hash= new BigInteger(1, md.digest());
		return hash.toString(16); 
	}

}