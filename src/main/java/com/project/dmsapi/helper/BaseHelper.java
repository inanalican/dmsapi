package com.project.dmsapi.helper;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.stereotype.Service;

@Service
public class BaseHelper {

	public String generateHashedPassword(String password) {
		try {
			String plaintext = password;
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(plaintext.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			return hashtext;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return "";
		}
	}
	public String getExtensionFromFileName(String fileName) {
		return fileName.split("\\.")[1];
	}
}
