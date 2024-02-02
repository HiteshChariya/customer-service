package com.bookstore.customer.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookServiceResponse {

	private Boolean validation;
	private String error;
	private String message;
	private List<BookResponse> response;
	
}
