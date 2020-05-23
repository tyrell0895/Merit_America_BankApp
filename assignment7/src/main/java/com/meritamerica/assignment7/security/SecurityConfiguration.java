package com.meritamerica.assignment7.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.meritamerica.assignment7.filters.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsServices myUserDetailsServices;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsServices);

	}
//Authenticate is not working for permit all. stating forbidden and access denied when using postman
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/AccountHolders/**").hasAuthority("ADMIN")
		.antMatchers("/Me/**").hasAuthority("ACCOUNTHOLDER")
		.antMatchers(HttpMethod.POST,"/CDOffering").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET,"/CDOffering").hasAnyAuthority("ADMIN","ACCOUNTHOLDER")
		.antMatchers("/authenticate/CreateUser").hasAuthority("ADMIN")
		.antMatchers("/authenticate").permitAll()
		.anyRequest().authenticated().and().
				exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);//Spring Security wont create a session
http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); //we add in the filter that we created before the User/Pass authentication
	}
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
		
	}
	
	
	    
	        
	

	@Bean
	public PasswordEncoder getPasswordEncoder() {

		//Allows for clear text password, basically strings 
		return NoOpPasswordEncoder.getInstance();	}



}
