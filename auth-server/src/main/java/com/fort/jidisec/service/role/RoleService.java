package com.fort.jidisec.service.role;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fort.jidisec.module.role.Role;
import com.fort.jidisec.mongodb.MongoSuportTemplate;

@Service("roleService")
public class RoleService extends MongoSuportTemplate{
	
	private final String ROLE_CONLLECTION = "role";
	
	public Role findOneByRoleName(String roleName) {
		return mongoTemplate.findOne(new Query(Criteria.where("roleName").is(roleName)), Role.class, ROLE_CONLLECTION);
	}
}
