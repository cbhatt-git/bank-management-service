package com.cts.mc.bankmanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mc.bankmanagement.dto.LoanDto;
import com.cts.mc.bankmanagement.entity.LoanEntity;
import com.cts.mc.bankmanagement.repository.LoanRepository;
import com.cts.mc.bankmanagement.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author chiranjitbhattacharya
 *
 */
@Service
public class LoanServiceImpl implements LoanService {

	private static final Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	@Override
	public LoanDto applyLoan(LoanDto loanDetails, String username) {
		log.info("Connecting to repository to create a new loan for the user : {}", username);
		// Setting the username
		loanDetails.setUsername(username);
		
		return mapper.convertValue(loanRepository.save(mapper.convertValue(loanDetails, LoanEntity.class)), LoanDto.class);
	}

	@Override
	public List<LoanDto> findLoanByUsername(String username) {
		log.info("Connecting to repository to fetch all loan details for the user : {}", username);
		return loanRepository.findByUsername(username)
				.stream()
				.parallel()
				.map( loanData -> mapper.convertValue(loanData, LoanDto.class))
				.collect(Collectors.toList());
	}

}
