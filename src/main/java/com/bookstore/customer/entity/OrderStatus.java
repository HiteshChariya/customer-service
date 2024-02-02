package com.bookstore.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.Constants;
import com.bookstore.customer.request.OrderStatusRequest;

import lombok.Getter;

@Entity
@Table(name = "order_status", schema = Constants.DATA_REVIEW_SCHEMA)
@Getter
public class OrderStatus extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_status_id")
	private Long id;
	
	@Column(length = 100)
	private String status;

	public void saveOrderStatus(OrderStatus updateOrderStatus, OrderStatusRequest orderStatusRequest,
			TokenData tokenData, String recordStatus, String source) {
		updateOrderStatus.status = orderStatusRequest.getStatus();
		if(id == null)
			updateOrderStatus.create(recordStatus, tokenData.getFirstName(), source);
		else
			updateOrderStatus.update(recordStatus, tokenData.getFirstName());
	}

	public void updateOrderStatus(OrderStatus dltOrderStatus, TokenData tokenData, String recordStatus) {
		dltOrderStatus.update(recordStatus, tokenData.getFirstName());
	}
	
}
