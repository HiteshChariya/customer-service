package com.bookstore.customer.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResponse {

	private Boolean tokenValidated;
	private TokenData principle;
	private String failureMessage;
	

	
}
