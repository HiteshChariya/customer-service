package com.bookstore.customer.response;

import com.bookstore.customer.entity.OrderStatus;

import lombok.Data;

@Data
public class OrderStatusResponse {

	private Long id;
	private String status;
	
	public OrderStatusResponse(OrderStatus orderStatus) {
		this.id = orderStatus.getId();
		this.status = orderStatus.getStatus();
	}
}
