package com.fort.jidisec.module.code;

import java.io.Serializable;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * 授权码实体信息类
 * 
 * @author zhigongzhang
 *
 */
public class AuthorizationCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8329253038868886092L;

	private String code;
	
	private byte[] authentication;

	public AuthorizationCode() {
		
	}
	
	public AuthorizationCode(String code, byte[] authentication) {
		this.code = code;
		this.authentication = authentication;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte[] getAuthentication() {
		return authentication;
	}

	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}
	
}
