package com.cts.mc.bankmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cts.mc.bankmanagement.dto.LoanDto;
import com.cts.mc.bankmanagement.entity.LoanEntity;
import com.cts.mc.bankmanagement.repository.LoanRepository;

@SpringBootTest
public class LoanServiceTest {
	
	@MockBean
	private LoanRepository loanRepository;
	
	@MockBean
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private LoanService loanService;

	
	private LoanEntity loanEntity = LoanEntity.builder()
			.username("testuser")
			.build();
	
	private LoanDto loanDto = LoanDto.builder()
			.username("testuser")
			.build();
	
	
	@Test
	public void testApplyLoan() {
		when(loanRepository.save(ArgumentMatchers.any()))
		.thenReturn(loanEntity);
		
		LoanDto user = loanService.applyLoan(loanDto, "dummyuser");
		assertEquals(loanEntity.getUsername(), user.getUsername());
	}
	
	
	
	@Test
	public void testEdit() {
		when(loanRepository.findByUsername(ArgumentMatchers.any()))
		.thenReturn(Collections.singletonList(loanEntity));
		
		
		List<LoanDto> loans = loanService.findLoanByUsername("someuser");
		assertEquals(loanEntity.getUsername(), loans.get(0).getUsername());
	}
	
	

}
