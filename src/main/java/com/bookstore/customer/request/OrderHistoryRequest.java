package com.bookstore.customer.request;

import java.time.LocalDateTime;

import com.bookstore.customer.enums.OrderStatusCode;

import com.bookstore.customer.enums.OrderTrakerCode;
import lombok.Data;

@Data
public class OrderHistoryRequest {

	private Long id;
	private Long orderId;
	private OrderTrakerCode status;
	private LocalDateTime statusDate;
}
