package com.fort.jidisec.module.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class FortUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7419924775414930063L;

	private String username;
	
	private String password;
	
	private String roletype;
	
	private String mail;
	
	private String phone;
	
	private String description;
	
	private Integer lockNumber;
	
	private Long lockTimeLength;
	
	private Long lockStartTime;
	
	private Long passwordErrorNumber;
	
	private String lockStatus;
	
	private String userType;
	
	private String theme;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLockNumber() {
		return lockNumber;
	}

	public void setLockNumber(Integer lockNumber) {
		this.lockNumber = lockNumber;
	}

	public Long getLockTimeLength() {
		return lockTimeLength;
	}

	public void setLockTimeLength(Long lockTimeLength) {
		this.lockTimeLength = lockTimeLength;
	}

	public Long getLockStartTime() {
		return lockStartTime;
	}

	public void setLockStartTime(Long lockStartTime) {
		this.lockStartTime = lockStartTime;
	}

	public Long getPasswordErrorNumber() {
		return passwordErrorNumber;
	}

	public void setPasswordErrorNumber(Long passwordErrorNumber) {
		this.passwordErrorNumber = passwordErrorNumber;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}
