package com.fort.jidisec.service.oauth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fort.jidisec.module.role.Role;
import com.fort.jidisec.module.user.FortUserDetails;
import com.fort.jidisec.mongodb.MongoSuportTemplate;
import com.fort.jidisec.service.role.RoleService;

/**
 * 用户认证接口
 * @author zhigongzhang
 *
 */
@Service("mongoUserDetailsService")
public class MongoUserDetailsService extends MongoSuportTemplate implements UserDetailsService {

	private final String USER_CONLLECTION = "user";
	
	@Autowired
	private RoleService roleService;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		FortUserDetails user = mongoTemplate.findOne(new Query(Criteria.where("username").is(username)), FortUserDetails.class, USER_CONLLECTION);
		if(user == null) {
			throw new RuntimeException("查询用户信息时出现异常");
		}
		String roleType = user.getRoletype();
		if(roleType == null || "".equals(roleType)) {
			throw new RuntimeException("用户未分配权限");
		}
		Role role = roleService.findOneByRoleName(roleType);
		if(role == null) {
			throw new RuntimeException("用户未分配权限");
		}
		List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
		GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
		roleList.add(ga);
		user.setAuthorities(roleList);
		user.setPassword("");
		return user;
	}

}
