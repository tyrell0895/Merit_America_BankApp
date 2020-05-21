package com.meritamerica.assignment7.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		//		POST /authenticate/createUser
		//		Creates a new User
		//		Only administrator can call this
		//		POST /authenticate
		//		Takes username and password and returns a JWT Token if authentication is successful or an HTTP 401 if authentication fails
		//		Anyone call this

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").hasRole("Admin")
								.antMatchers("/").hasRole("AccountHolder")
								.antMatchers("/authenticate","/CDOffering").permitAll()
								.and().formLogin();
		;// Need the method calls we are going to use. 
		//This is where we will define the paths the Admin and Accountholder can access.
	}


	@Bean
	public PasswordEncoder getPasswordEncoder() {

		//Allows for clear text password, basically strings 
		return NoOpPasswordEncoder.getInstance();	}



}
