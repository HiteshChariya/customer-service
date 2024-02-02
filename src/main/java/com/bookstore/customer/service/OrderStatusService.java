package com.bookstore.customer.service;

import java.util.List;

import com.bookstore.customer.request.OrderStatusRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.OrderStatusResponse;

public interface OrderStatusService {

	CommonResponse<String> saveOrderStatus(OrderStatusRequest orderStatusRequest);

	CommonResponse<List<OrderStatusResponse>> getAllOrderStatus();

	CommonResponse<String> dalateOrderStatusById(Long id);

}
