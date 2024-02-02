package com.bookstore.customer.service;

import java.util.List;

import com.bookstore.customer.request.OrderDetailRequest;
import com.bookstore.customer.response.*;
import com.bookstore.customer.searchparam.OrderSearchParams;

public interface OrderDetailService {

	CommonResponse<OrderDetailResponse> saveOrderDetail(OrderDetailRequest orderDetailRequest);

	CommonResponse<List<OrderDetailResponse>> getAllOrderDetail();

	CommonResponse<String> deleteOrderDetail(Long id);

    CommonResponse<List<OrderStatusResponse>> getAllOrderStatus();

    CommonResponse<OrderDetailResponse> getOrderById(Long id);

    CommonResponse<OrderDetailResponse> confirmOrderDetail(Long id);

    CommonResponse<OrderDetailResponse> shippedOrderDetail(Long id);

    CommonResponse<OrderDetailResponse> deliverOrderDetail(Long id);

    CommonResponse<OrderDetailResponse> placedOrderDetail(Long id);

    CommonResponse<OrderDetailResponse> cancelOrderDetail(Long id);

    CommonResponse<OrderDetailResponse> outForDeliverOrderDetail(Long id);

    CommonResponse<OrderDetailResponse> returnOrderDetail(Long id);

    CommonResponse<List<OrderTrackerStatusResponse>> getAllOrderTrackerStatus();

    PagedResponse<OrderDetailResponse> getOrderDetails(OrderSearchParams orderSearchParams);
}
