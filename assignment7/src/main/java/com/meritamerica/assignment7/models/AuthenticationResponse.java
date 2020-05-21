package com.meritamerica.assignment7.models;

//Output for Authentication method
public class AuthenticationResponse {

	
	private final String jwt ;
	
	public AuthenticationResponse(String jwt) {
		this.jwt=jwt;
	}

	public String getJwt() {
		return jwt;
	}

	
}
