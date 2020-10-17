package com.cts.mc.bankmanagement.service;

import java.util.List;

import com.cts.mc.bankmanagement.dto.LoanDto;

/**
 * 
 * Loan Service
 * 
 * @author chiranjitbhattacharya
 *
 */
public interface LoanService {
	
	/**
	 * Service to create a new loan details.
	 * 
	 * @param loanDetails LoanDto - Loan related details
	 * @param username String - User name
	 * @return LoanDto - newly created loan details
	 */
	public LoanDto applyLoan(LoanDto loanDetails, String username);
	
	
	
	
	/**
	 * Service to fetch all load details for an user.
	 * 
	 * @param username String - User name of the loan details.
	 * @return List<LoanDto> List of loan details for the user, empty list if no loan found.
	 */
	public List<LoanDto> findLoanByUsername(String username);
	

}
