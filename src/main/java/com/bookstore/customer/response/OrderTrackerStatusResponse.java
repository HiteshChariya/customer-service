package com.bookstore.customer.response;

import com.bookstore.customer.entity.OrderStatus;
import com.bookstore.customer.entity.OrderTrakerStatus;
import lombok.Data;

@Data
public class OrderTrackerStatusResponse {

	private Long id;
	private String status;

	public OrderTrackerStatusResponse(OrderTrakerStatus orderStatus) {
		this.id = orderStatus.getId();
		this.status = orderStatus.getOrderTrakerStatue();
	}
}
