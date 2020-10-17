package com.cts.mc.bankmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cts.mc.bankmanagement.dto.UserDto;
import com.cts.mc.bankmanagement.entity.UserEntity;
import com.cts.mc.bankmanagement.repositories.UserRepository;

@SpringBootTest
public class UserServiceTest {
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;

	
	private UserEntity userEntity = UserEntity.builder()
			.username("testuser")
			.name("Test User")
			.build();
	
	private UserDto userDto = UserDto.builder()
			.username("testuser")
			.name("Test User")
			.build();
	
	
	@Test
	public void testSignup1() {
		when(userRepository.save(ArgumentMatchers.any()))
		.thenReturn(userEntity);
		UserDto user = userService.save(userDto);
		assertEquals(userEntity.getUsername(), user.getUsername());
	}
	
	@Test
	public void testEdit() {
		when(userRepository.save(ArgumentMatchers.any()))
		.thenReturn(userEntity);
		
		when(userRepository.findByUsername(ArgumentMatchers.any()))
		.thenReturn(userEntity);
		
		UserDto user = userService.edit(userDto, "someuser");
		assertEquals(userEntity.getUsername(), user.getUsername());
	}
	
	

}
