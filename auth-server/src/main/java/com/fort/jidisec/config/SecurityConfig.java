package com.fort.jidisec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fort.jidisec.service.user.UserService;
import com.fort.jidisec.webapp.authentication.FortAuthenticationFailureHandler;
import com.fort.jidisec.webapp.authentication.FortAuthenticationSuccessHandler;
import com.fort.jidisec.webapp.authentication.FortUsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final String defaultFailureUrl = "/login?error";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")
        .and()
        .authorizeRequests().antMatchers("/imgCode").permitAll()
        .antMatchers("/oauth/**").authenticated()
        .and()
        .formLogin().loginPage("/login").permitAll()
        .and().addFilterBefore(getUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	public UsernamePasswordAuthenticationFilter getUsernamePasswordAuthenticationFilter() throws Exception {
		UsernamePasswordAuthenticationFilter authFilter = new FortUsernamePasswordAuthenticationFilter(userService);
		authFilter.setAuthenticationManager(this.authenticationManager());
		authFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		return authFilter;
	}
	
	@Bean("authenticationFailureHandler")
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return new FortAuthenticationFailureHandler(defaultFailureUrl);
	}
	
	@Bean("authenticationSuccessHandler")
	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return new FortAuthenticationSuccessHandler();
	}
}
