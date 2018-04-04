package com.fort.jidisec.service.oauth;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.stereotype.Service;

import com.fort.jidisec.module.code.AuthorizationCode;
import com.fort.jidisec.mongodb.MongoSuportTemplate;

@Service("mongoAuthorizationCodeServices")
public class MongoAuthorizationCodeServices extends MongoSuportTemplate implements AuthorizationCodeServices{

	private final String CONLLECTION_NAME = "oauth_code";
	
	private RandomValueStringGenerator generator = new RandomValueStringGenerator();
	
	@Override
	public String createAuthorizationCode(OAuth2Authentication authentication) {
		String code = generator.generate();
		AuthorizationCode authorizationCode = new AuthorizationCode(code, serializeAuthentication(authentication));
		mongoTemplate.insert(authorizationCode, CONLLECTION_NAME);
		return code;
	}

	@Override
	public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {
		AuthorizationCode authorizationCode = mongoTemplate.findOne(new Query(Criteria.where("code").is(code)), AuthorizationCode.class,CONLLECTION_NAME);
		OAuth2Authentication authentication = null;
		if(authorizationCode != null) {
			authentication = deserializeAuthentication(authorizationCode.getAuthentication());
			mongoTemplate.remove(new Query(Criteria.where("code").is(code)), CONLLECTION_NAME);
		}
		return authentication;
	}
	
	protected byte[] serializeAccessToken(OAuth2AccessToken token) {
		return SerializationUtils.serialize(token);
	}

	protected byte[] serializeRefreshToken(OAuth2RefreshToken token) {
		return SerializationUtils.serialize(token);
	}

	protected byte[] serializeAuthentication(OAuth2Authentication authentication) {
		return SerializationUtils.serialize(authentication);
	}

	protected OAuth2AccessToken deserializeAccessToken(byte[] token) {
		return SerializationUtils.deserialize(token);
	}

	protected OAuth2RefreshToken deserializeRefreshToken(byte[] token) {
		return SerializationUtils.deserialize(token);
	}

	protected OAuth2Authentication deserializeAuthentication(byte[] authentication) {
		return SerializationUtils.deserialize(authentication);
	}

	protected String extractTokenKey(String value) {
		if (value == null) {
			return null;
		}
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
		}

		try {
			byte[] bytes = digest.digest(value.getBytes("UTF-8"));
			return String.format("%032x", new BigInteger(1, bytes));
		}
		catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
		}
	}

}
