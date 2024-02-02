package com.bookstore.customer.exception;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.CustomError;
import com.fasterxml.jackson.core.JsonParseException;

public class ErrorUtils {
	public enum ErrorType {
		SYSTEM_EXCEPTION, BUSINESS_EXCEPTION, INPUT_VALIDTION, INVALID_USER_NAME_PASSWORD;
	}
	public enum ValidationType
	{
		MISSING_INPUT, INVALID_INPUT;
	}
	

	public static CustomError translateError(Exception ex) {
		CustomError error = new CustomError();
		error.setErrorMessage(ex.getMessage());
		error.setType(ErrorType.SYSTEM_EXCEPTION.toString());
		int errorCode = 501;
		if (ex instanceof SQLException) {
			errorCode = 502;
		}
		else if (ex instanceof IOException) {
			errorCode = 503;
		}
		else if (ex instanceof RemoteException) {
			errorCode = 504;
		}
		else if (ex instanceof JsonParseException)
			errorCode = 505;
		else
			errorCode = 555;
		error.setErrorCode(errorCode);
		return error;
	}
	
	public static <T> CommonResponse<T> buildErrorResponse(CommonResponse<T> response, Exception ex, Logger logger)
	{
		if(response==null)
			response = new CommonResponse<T>(null, null);
		response.setValidation(false);
		CustomError error = translateError(ex);
			response.addCustomErrors(error);
		logger.error(ex.toString(), ex);
		response.setMessage(ex.getMessage());
		return response;
	}
	public static CustomError translateInputValidation(String type, String message) {
		CustomError error = new CustomError();
		error.setErrorMessage(message);
		error.setType(type);
		error.setErrorCode(401);
		return error;
	}
}