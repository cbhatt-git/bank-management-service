package com.cts.mc.bankmanagement.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.cts.mc.bankmanagement.dto.LoanDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * @author chiranjitbhattacharya
 *
 */


@Tag(name = "Loan API", description = "APIs to enable user apply for loan, view exsting loans etc.")

public interface LoanApi {



	@Operation(summary = "Apply for a new loan")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "201", description = "Created")})
	ResponseEntity<LoanDto> apply(LoanDto loanDetails, Authentication auth);	

	
	
	@Operation(summary = "Find Loan details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "200", description = "Ok")})

	ResponseEntity<List<LoanDto>> find(Authentication auth);


}
