package com.bookstore.customer.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchTokenData {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	TokenValidation tokenValidation;
	
	
	public TokenData getTokenData() {
		String authHeader = request.getHeader("Authorization");
		TokenData tokenData = tokenValidation.handleRequest(authHeader).getPrinciple();
		return tokenData;
	}

}
