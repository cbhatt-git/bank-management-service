package com.cts.mc.bankmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.mc.bankmanagement.dto.AuthUser;
import com.cts.mc.bankmanagement.entity.User;
import com.cts.mc.bankmanagement.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid Username");
		}
		return new AuthUser(user);
	}

}
