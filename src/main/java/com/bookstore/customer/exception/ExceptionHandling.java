package com.bookstore.customer.exception;


import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Arrays;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.CustomError;
import com.fasterxml.jackson.core.JsonParseException;

import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class ExceptionHandling extends ResponseEntityExceptionHandler {
	
	private static final String FAILURE = "Failure";
	
	@ExceptionHandler({SQLException.class,IOException.class,RemoteException.class,JsonParseException.class})
	@ResponseStatus(HttpStatus.OK)
	public <T> ResponseEntity<CommonResponse<T>> handleInvalidArgumentException(SQLException exception){
		return ResponseEntity.ok(ErrorUtils.buildErrorResponse(null, exception, log)); 
	}
	
//	@ExceptionHandler(ResourceNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
//		CustomError customError = ErrorUtils.translateInputValidation(ErrorUtils.ValidationType.INVALID_INPUT.name(),
//				resourceNotFoundException.getMessage());
//		return ResponseEntity.ok(CommonResponse.failure(FAILURE, null, Arrays.asList(customError)));
//	}

	@ExceptionHandler(InvalidArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleInvalidArgumentException(InvalidArgumentException invalidArgumentException) {

		CustomError customError = ErrorUtils.translateInputValidation(ErrorUtils.ErrorType.INPUT_VALIDTION.name(),
				invalidArgumentException.getMessage());
		return ResponseEntity.ok(CommonResponse.failure(FAILURE, null, Arrays.asList(customError)));
	}
	
//	@ExceptionHandler(AuthenticationException.class)
//	@ResponseStatus(HttpStatus.UNAUTHORIZED)
//	public ResponseEntity<Object> handleAuthenticationException(AuthenticationException authenticationException) {
//
//		CustomError customError = ErrorUtils.translateInputValidation(ErrorUtils.ErrorType.INVALID_USER_NAME_PASSWORD.name(),
//				authenticationException.getMessage());
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.failure(FAILURE, null, Arrays.asList(customError)));
//	}

}