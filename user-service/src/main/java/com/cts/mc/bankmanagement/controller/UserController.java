package com.cts.mc.bankmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mc.bankmanagement.api.UserApi;
import com.cts.mc.bankmanagement.dto.UserDto;
import com.cts.mc.bankmanagement.service.UserService;
import com.cts.mc.bankmanagement.validator.groups.Create;
import com.cts.mc.bankmanagement.validator.groups.Update;


/**
 * 
 * user Api controller
 * @author chiranjitbhattacharya
 *
 */
@RestController
public class UserController implements UserApi {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	private UserService userService;
	
	@Override
	@PostMapping("/users/signup")
	public ResponseEntity<UserDto> signup(@Validated( { Create.class } ) @RequestBody( required = true ) UserDto user) {
		log.info("Received request to create a new user with user name : {} ", user.getUsername());
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}


	@Override
	@PutMapping("/users")
	public ResponseEntity<UserDto> editAccount(@Validated( { Update.class } ) @RequestBody( required = true ) UserDto user, Authentication auth) {
		log.info("Received request to update an user account with user name : {} ", auth.getPrincipal());
		return new ResponseEntity<>(userService.edit(user, auth.getPrincipal().toString()), HttpStatus.OK);
	}


	@Override
	@GetMapping("/users")
	public ResponseEntity<UserDto> findUser(Authentication auth) {
		log.info("Received request to fetch user account details for user name : {} ", auth.getPrincipal());
		return new ResponseEntity<>(userService.findByUsername(auth.getPrincipal().toString()), HttpStatus.OK);
	}


	@Override
	public ResponseEntity<UserDto> changePassword(Authentication auth) {
		return null;
	}

}
