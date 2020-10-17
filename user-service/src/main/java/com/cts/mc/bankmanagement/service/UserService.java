package com.cts.mc.bankmanagement.service;

import com.cts.mc.bankmanagement.dto.UserDto;

public interface UserService {
	
	UserDto save(UserDto user);
	
	UserDto edit(UserDto user, String authenticatedUsername);

	UserDto findByUsername(String username);
	
}
