package com.cts.mc.bankmanagement.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.mc.bankmanagement.dto.UserDto;
import com.cts.mc.bankmanagement.entity.UserEntity;
import com.cts.mc.bankmanagement.repositories.UserRepository;
import com.cts.mc.bankmanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@WebMvcTest
//@AutoConfigureMockMvc(addFilters=false )
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc(addFilters=false)
public class UserControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	AuthenticationManager m;
	
	@MockBean
	private UserService userService;
	
	private UserEntity userEntity = UserEntity.builder()
			.username("testuser")
			.name("Test User")
			.password("strongpassword")
			.email("some@some.com")
			.accountType("Savings")
			.build();
	
	private UserDto userDto = UserDto.builder()
			.username("testuser")
			.name("Test User")
			.password("strongpassword")
			.email("some@some.com")
			.accountType("Savings")
			.build();
	
	private UserDto userDtoInvalid = UserDto.builder()
			.name("Test User")
			.password("strongpassword")
			.email("some@some.com")
			.accountType("Savings")
			.build();
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private UserRepository userRepo;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testSignup1() throws Exception {
		when(userService.save(ArgumentMatchers.any()))
		.thenReturn(userDto);
		
		when(userRepo.findByUsername(ArgumentMatchers.any()))
		.thenReturn(userEntity);
		
		when(passwordEncoder.matches(ArgumentMatchers.any(), ArgumentMatchers.any()))
		.thenReturn(true);
		
		
		
		mvc.perform(post("/users/signup")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(userDto)))
		.andExpect(status().isCreated());
	}
	
	

}
