package com.bookstore.customer.service;

import java.util.List;

import com.bookstore.customer.request.OrderHistoryRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.OrderHistoryResponse;

public interface OrderHistoryService {

	CommonResponse<String> saveOrderHistory(OrderHistoryRequest orderHistoryRequest);

	CommonResponse<List<OrderHistoryResponse>> getAllOrderHistory();

	CommonResponse<String> deleteOrderHistory(Long id);

	CommonResponse<OrderHistoryResponse> getOrderHistoryById(Long id);
}
