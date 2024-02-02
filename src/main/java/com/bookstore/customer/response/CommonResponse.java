package com.bookstore.customer.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

    public enum OperationStatus {
        SUCCESS, FAILURE,BUSINESS_FAILURE,UNAUTHORIZED,USER_NOT_REGISTERED;
    }

    private String message;

    private OperationStatus status;

    private T response;
    
    private boolean validation;
    
    private List<CustomError> errors;

    public static <T> CommonResponse<T> failure(String message) {
        return CommonResponse.failure(message, null);
    }

    public static <T> CommonResponse<T> failure(String message, T response) {
        return new CommonResponse<>(message, OperationStatus.FAILURE, response,false,null);
    }

    public static  <T> CommonResponse<T> success(String message) {
        return CommonResponse.success(message, null);
    }

    public static <T> CommonResponse<T> success(String message, T response) {
        return new CommonResponse<>(message, OperationStatus.SUCCESS, response,true,null);
    }
    
    public static <T> CommonResponse<T> failureWithErrors(String message, List<CustomError> errors) {
        return new CommonResponse<>(message, OperationStatus.SUCCESS, null,false,errors);
    }
    
    public static <T> CommonResponse<T> failureWithErrors(String message, T errors) {
        return new CommonResponse<>(message, OperationStatus.FAILURE, errors,false, null);
    }
    
    public static <T> CommonResponse<T> failure(String message, T response, T errors) {
    	 return new CommonResponse<>(message, OperationStatus.FAILURE, errors,false, null);
	}

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CommonResponse(String message, OperationStatus status) {
        this(message, status, null,true,null);
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CommonResponse(String message, OperationStatus status, T response, final boolean valid,final List<CustomError> errors) {
        this.message = message;
        this.status = status;
        this.response = response;
        this.validation = valid;
        this.errors = errors;
    }

	public Boolean getValidation() {
		return validation;
	}
	public void setValidation(Boolean validation) {
		this.validation = validation;
	}
	public List<CustomError> getError() {
		return errors;
	}
	public void setError(List<CustomError> error) {
		this.errors = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}

	public void addCustomErrors(CustomError err)
	{
		if(errors==null)
			errors= new ArrayList<CustomError>();
		errors.add(err);
	}

}