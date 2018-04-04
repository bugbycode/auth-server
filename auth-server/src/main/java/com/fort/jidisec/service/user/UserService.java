package com.fort.jidisec.service.user;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fort.jidisec.module.user.FortUserDetails;
import com.fort.jidisec.mongodb.MongoSuportTemplate;

@Service("userService")
public class UserService extends MongoSuportTemplate{
	
	private final String ROLE_CONLLECTION = "user";
	
	public FortUserDetails findOneByUsernamePassword(String username,String password) {
		Criteria criteria = Criteria.where("username").is(username)
				.andOperator(Criteria.where("password").is(password));
		
		Query query = new Query(criteria);
		
		return mongoTemplate.findOne(query, FortUserDetails.class,ROLE_CONLLECTION);
	}
}
