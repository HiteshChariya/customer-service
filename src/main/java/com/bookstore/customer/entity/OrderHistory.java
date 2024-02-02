package com.bookstore.customer.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.Constants;
import com.bookstore.customer.enums.OrderStatusCode;
import com.bookstore.customer.enums.OrderTrakerCode;
import com.bookstore.customer.request.OrderHistoryRequest;

import lombok.Getter;

@Entity
@Table(name = "order_history", schema = Constants.DATA_REVIEW_SCHEMA)
@Getter
public class OrderHistory extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_history_id")
	private Long id;
	private Long orderId;
	private Long status;
	private LocalDateTime statusDate;
	
	public void saveOrderHistory(OrderHistory updateOrderHistory, TokenData tokenData, String recordStatus,
			String source, OrderHistoryRequest orderHistoryRequest, Long tracker) {
		updateOrderHistory.orderId = orderHistoryRequest.getOrderId();
		updateOrderHistory.status = tracker;
		updateOrderHistory.statusDate = LocalDateTime.now();
		if(id == null)
			updateOrderHistory.create(recordStatus, tokenData.getFirstName(), source);
		else
			updateOrderHistory.update(recordStatus, tokenData.getFirstName());
	}

	public void deleteOrderHistory(OrderHistory dltOrderHistory, TokenData tokenData, String recordStatus) {
		dltOrderHistory.update(recordStatus, tokenData.getFirstName());
	}
	
}
