package com.cts.mc.bankmanagement.api;



import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.cts.mc.bankmanagement.dto.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * 
 * API specification for User apis.
 * 
 * @author chiranjitbhattacharya
 *
 */


@Tag(name = "User API", description = "APIs to enable users to create a new account and manage existing acount.")

public interface UserApi {



	/**
	 * API to create or register a new user
	 * 
	 * @param user UserDto - User details for creating an account
	 * @return The created user details
	 */
	@Operation(summary = "Register yourself")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "201", description = "Created")})
	ResponseEntity<UserDto> signup(UserDto user);	

	
	/**
	 * API to fetch user user
	 * 
	 * @param user UserDto - User details for creating an account
	 * @return The created user details
	 */
	@Operation(summary = "Fetch account details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "201", description = "Created")})
	ResponseEntity<UserDto> findUser(Authentication auth);	
	
	/**
	 * 
	 * API to update an existing user account details for the authenticated user
	 * 
	 * This is an secured API, username should be available in the authentication context to perform the operation.
	 * 
	 * @param user UserDto - User details to be updated
	 * @param auth Authentication - Authentication object
	 * @return The updated user details
	 */
	@Operation(summary = "Edit account details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "200", description = "Ok")})

	ResponseEntity<UserDto> editAccount(UserDto user, Authentication auth);	
	
	
	
	/**
	 * API to change password
	 * 
	 * @return The created user details
	 */
	@Operation(summary = "Change password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "201", description = "Created")})
	ResponseEntity<UserDto> changePassword(Authentication auth);


}
