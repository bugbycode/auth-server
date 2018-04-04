package com.fort.jidisec.module.role;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2158609704809786118L;
	
	//角色名称
	private String roleName;
	
	//角色描述
	private String roleMemo;
	
	//角色权限信息
	private Set<String> roleList;
	
	//角色类型 系统默认角色或者是自定义角色
	private String roleType;
	
	public Role() {
		
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleMemo() {
		return roleMemo;
	}

	public void setRoleMemo(String roleMemo) {
		this.roleMemo = roleMemo;
	}

	public Set<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<String> roleList) {
		this.roleList = roleList;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	
}
