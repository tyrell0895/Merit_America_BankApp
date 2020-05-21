package com.meritamerica.assignment7.security;
import com.meritamerica.assignment7.models.Users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.repos.UsersRepository;
@Service
public class MyUserDetailsServices implements UserDetailsService{

	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO return an instance of the repository 
		Optional<Users> users =  usersRepository.findByUserName( userName);
		
		users.orElseThrow(() -> new UsernameNotFoundException("Not found"+ userName));
		return users.map(MyUserDetails:: new).get();	
	}




}
