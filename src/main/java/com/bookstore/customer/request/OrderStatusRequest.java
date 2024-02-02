package com.bookstore.customer.request;

import lombok.Data;

@Data
public class OrderStatusRequest {

	private Long id;
	private String status;
}
