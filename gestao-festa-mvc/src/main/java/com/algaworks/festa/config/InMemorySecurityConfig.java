package com.algaworks.festa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public class InMemorySecurityConfig {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder)
			throws Exception {
		builder
		.inMemoryAuthentication()
		.withUser("paulo").password("{noop}MGRhut@#!13").roles("USER")
		.and()
		.withUser("manoel").password("{noop}123").roles("USER")
		.and()
		.withUser("gabriel").password("{noop}123").roles("USER");
	}
}
