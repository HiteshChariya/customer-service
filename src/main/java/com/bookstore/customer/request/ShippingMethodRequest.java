package com.bookstore.customer.request;

import lombok.Data;

@Data
public class ShippingMethodRequest {

	private Long id;
	
	private String methodName;
	private Long cost;
}
