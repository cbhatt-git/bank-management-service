package com.cts.mc.bankmanagement.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Error {


	private int errorCode;
	private String error;
	private String errorMessage;
	private List<String> fieldErrors = new ArrayList<>();
	
	
	public Error(HttpStatus status, String message, List<String> fieldErrors ) {
		this.errorCode = status.value();
		this.error = status.name();
		this.errorMessage = message;
		this.fieldErrors = fieldErrors;
	}



}
