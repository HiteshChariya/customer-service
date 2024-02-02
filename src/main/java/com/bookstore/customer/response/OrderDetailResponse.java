package com.bookstore.customer.response;

import java.time.LocalDateTime;
import java.util.List;

import com.bookstore.customer.constant.MessageConstant;
import com.bookstore.customer.entity.OrderDetail;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class OrderDetailResponse {

	private Long id;
	private Long customerId;
	@JsonFormat(pattern = MessageConstant.DATE_FORMATE)
	private LocalDateTime orderDate;
	@JsonFormat(pattern = MessageConstant.DATE_FORMATE)
	private LocalDateTime deliveryDate;
	private Long orderStatus;

	private Long itemPrice;
	private Long discountPrice;
	private Long totalItem;
	private Long totalPrice;
	private String bookId;
	private List<BookResponse> orderItems;

	public OrderDetailResponse(OrderDetail orderDetail) {
		this.id = orderDetail.getId();
		this.customerId = orderDetail.getCustomerId();
		this.orderDate = orderDetail.getOrderDate();
		this.deliveryDate = orderDetail.getDeliveryDate();
		this.orderStatus = orderDetail.getOrderStatusId();
		this.bookId = orderDetail.getBookId();
	}

	public OrderDetailResponse(OrderDetail orderDetail, Long price, Long disPrice, Long totalItems,Long totalPrice,
							   List<BookResponse> orderItems) {
		this.id = orderDetail.getId();
		this.customerId = orderDetail.getCustomerId();
		this.orderDate = orderDetail.getOrderDate();
		this.deliveryDate = orderDetail.getDeliveryDate();
		this.orderStatus = orderDetail.getOrderStatusId();
		this.itemPrice = price; // price * quantity
		this.discountPrice = disPrice; // price * 100 /discount
		this.totalItem = totalItems;
		this.totalPrice = totalPrice; // totalprice - discountPrice
		this.orderItems = orderItems;
	}
}
