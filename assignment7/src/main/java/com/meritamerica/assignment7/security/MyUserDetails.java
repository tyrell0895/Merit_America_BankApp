package com.meritamerica.assignment7.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.meritamerica.assignment7.models.Users;

public class MyUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;

	public  MyUserDetails(Users users) {
		this.username = users.getUsername();
		this.password = users.getPassword();
		this.active = users.isActive();
		this.authorities = Arrays.stream(users.getRoles().split(","))
				.map(SimpleGrantedAuthority:: new)
				.collect(Collectors.toList());
		

	}
	
	public MyUserDetails() {}
	
	public  MyUserDetails(String username) {
		this.username = username;
	}

	
//All values are hard coded except User Name. 
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
		return active;
	}

}
