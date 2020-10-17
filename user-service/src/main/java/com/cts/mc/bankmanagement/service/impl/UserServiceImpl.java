package com.cts.mc.bankmanagement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.mc.bankmanagement.dto.UserDto;
import com.cts.mc.bankmanagement.entity.UserEntity;
import com.cts.mc.bankmanagement.repositories.UserRepository;
import com.cts.mc.bankmanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author chiranjitbhattacharya
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ObjectMapper mapper;

	@Override
	public UserDto save(UserDto user) {
		// Encoding the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		log.info("Connecting to repository to create a new user with username : {} ", user.getUsername());
		return mapper.convertValue(userRepository.save(mapper.convertValue(user, UserEntity.class)), UserDto.class );
	}

	@Override
	public UserDto edit(UserDto user, String authenticatedUsername) {
		
		// Fetch user details
		UserEntity authenticatedUser = userRepository.findByUsername(authenticatedUsername);
		
		// As the username and password is non editable, updating those with current values
		user.setUsername(authenticatedUser.getUsername());
		user.setPassword(authenticatedUser.getPassword());
		user.setId(authenticatedUser.getId());
		user.setCreatedOn(authenticatedUser.getCreatedOn());
		
		log.info("Connecting to repository to update an existing user with username : {} ", user.getUsername());
		return mapper.convertValue(userRepository.save(mapper.convertValue(user, UserEntity.class)), UserDto.class );
	}

	@Override
	public UserDto findByUsername(String username) {
		return mapper.convertValue(userRepository.findByUsername(username), UserDto.class);
	}

}
