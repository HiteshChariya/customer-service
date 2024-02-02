package com.bookstore.customer.response;

import java.time.LocalDateTime;

import com.bookstore.customer.constant.MessageConstant;
import com.bookstore.customer.entity.OrderHistory;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class OrderHistoryResponse {

	private Long id;
	private Long orderId;
	private Long status;
	@JsonFormat(pattern = MessageConstant.DATE_FORMATE)
	private LocalDateTime statusDate;
	
	public OrderHistoryResponse(OrderHistory orderHistory) {
		this.id = orderHistory.getId();
		this.orderId = orderHistory.getOrderId();
		this.status = orderHistory.getStatus();
		this.statusDate = orderHistory.getStatusDate();
	}
	
}
