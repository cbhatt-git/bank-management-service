package com.cts.mc.bankmanagement.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.mc.bankmanagement.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@WebMvcTest
//@AutoConfigureMockMvc(addFilters=false )
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc(addFilters=true)
public class LoanControllerTest{
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	private LoanService loanService;
	
	
	
	@Autowired
	private ObjectMapper mapper;

	
	@Test
	public void testApplyLoan() throws Exception {
		
		
		
		mvc.perform(post("/loans")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void testgetLoan() throws Exception {
		
		
		
		mvc.perform(put("/loans")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void testgetLoanE() throws Exception {
		
		
		
		mvc.perform(put("/loans")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());
	}

	

	
	

}
