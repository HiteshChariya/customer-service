package com.bookstore.customer.entity;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.Constants;
import com.bookstore.customer.request.OrderDetailRequest;

import lombok.Getter;

@Entity
@Table(name = "order_detail", schema = Constants.DATA_REVIEW_SCHEMA)
@Getter
public class OrderDetail extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Long id;
	
	private Long customerId;
	private LocalDateTime orderDate;
	private LocalDateTime deliveryDate;
	private Long orderStatusId;
	private String bookId;
	private Long addressId;
	
	public void saveOrderDetail(OrderDetail updateOrderDetail, OrderDetailRequest orderDetailRequest,
			TokenData tokenData, String recordStatus, String source, Long addressId, Long orderStatus) {
		updateOrderDetail.customerId = tokenData.getId();
		updateOrderDetail.orderDate = LocalDateTime.now();
		updateOrderDetail.deliveryDate = LocalDateTime.now();

		updateOrderDetail.addressId = addressId;
		updateOrderDetail.orderStatusId = orderStatus;
		updateOrderDetail.bookId = orderDetailRequest.getProductIds().stream().map(m->String.valueOf(m)).collect(Collectors.joining(","));
		if(id == null)
			updateOrderDetail.create(recordStatus, tokenData.getFirstName(), source);
		else
			updateOrderDetail.update(recordStatus, tokenData.getFirstName());
	}

	public void updateOrderDetail(OrderDetail dltOrderDetail, TokenData tokenData, String recordStatus) {
		dltOrderDetail.update(recordStatus, tokenData.getFirstName());
	}

	public void updateOrderStatus(OrderDetail orderDetail, Long orderStatus, TokenData tokenData, String recordStatus) {
		orderDetail.orderStatusId = orderStatus;
		orderDetail.update(recordStatus, tokenData.getFirstName());
	}
}
