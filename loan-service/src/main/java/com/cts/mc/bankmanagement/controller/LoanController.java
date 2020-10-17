package com.cts.mc.bankmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mc.bankmanagement.api.LoanApi;
import com.cts.mc.bankmanagement.dto.LoanDto;
import com.cts.mc.bankmanagement.service.LoanService;

/**
 * @author chiranjitbhattacharya
 *
 */
@RestController
public class LoanController implements LoanApi {

	private static final Logger log = LoggerFactory.getLogger(LoanController.class);
	
	@Autowired
	private LoanService loanService;




	@PostMapping("/loans")
	@Override
	public ResponseEntity<LoanDto> apply(@Valid @RequestBody(required = true)LoanDto loanDetails, Authentication auth) {
		log.info("Received request to create a new loan for user : {} ", auth.getPrincipal().toString());
		return new ResponseEntity<>(loanService.applyLoan(loanDetails, auth.getPrincipal().toString()), HttpStatus.OK);
	}
	
	


	@GetMapping("/loans")
	@Override
	public ResponseEntity<List<LoanDto>> find(Authentication auth) {
		log.info("Received request to fetch all loan details for user : {} ", auth.getPrincipal().toString());
		return new ResponseEntity<>(loanService.findLoanByUsername(auth.getPrincipal().toString()), HttpStatus.OK);
	}

}
