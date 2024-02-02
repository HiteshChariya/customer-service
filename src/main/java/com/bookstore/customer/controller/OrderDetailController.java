package com.bookstore.customer.controller;

import java.util.List;

import com.bookstore.customer.response.*;
import com.bookstore.customer.searchparam.OrderSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bookstore.customer.request.OrderDetailRequest;
import com.bookstore.customer.service.OrderDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer")
@Tag(name = "Order Detail Configuration API", description = "Order Detail Configuration API for BookStore Managemant")
@Slf4j
public class OrderDetailController {
	
	@Autowired
	OrderDetailService orderDetailService;

	@Operation(summary = "Save Order Detail Details", description = "Save Order Detail Details")
	@PostMapping("/order")
	public CommonResponse<OrderDetailResponse> saveOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
		log.info("Save Order Detail {} ",orderDetailRequest);
		return orderDetailService.saveOrderDetail(orderDetailRequest);
	}

	@Operation(summary = "Fetch Order Detail Details", description = "Fetch Order Detail Details")
	@GetMapping("/order")
	public CommonResponse<List<OrderDetailResponse>> getAllOrderDetail() {
		log.info("Get All Order Detail");
		return orderDetailService.getAllOrderDetail();
	}

	@Operation(summary = "Fetch Order Detail Details", description = "Fetch Order Detail Details")
	@PostMapping("/order/search")
	public PagedResponse<OrderDetailResponse> getOrderDetails(@RequestBody OrderSearchParams orderSearchParams) {
		log.info("Get All Order Detail {} ",orderSearchParams);
		return orderDetailService.getOrderDetails(orderSearchParams);
	}

	@Operation(summary = "Get Order Detail By Id", description = "Get Order Detail By Id")
	@GetMapping("/order/orderById")
	public CommonResponse<OrderDetailResponse> getOrderById(@RequestParam("id") Long id) {
		log.info("Get Order Detail By Id {} ",id);
		return orderDetailService.getOrderById(id);
	}

	@Operation(summary = "Delete Order Detail Details", description = "Delete Order Detail Details")
	@PatchMapping("/order/{id}")
	public CommonResponse<String> deleteOrderDetail(@PathVariable("id") Long id) {
		log.info("Delete Order Detail {} ",id);
		return orderDetailService.deleteOrderDetail(id);
	}

	@Operation(summary = "Fetch Order Status", description = "Fetch Order Status")
	@GetMapping("/order/status")
	public CommonResponse<List<OrderStatusResponse>> getAllOrderStatus() {
		log.info("Get All Order Status");
		return orderDetailService.getAllOrderStatus();
	}

	@Operation(summary = "Fetch Order Status", description = "Fetch Order Status")
	@GetMapping("/order/tracker")
	public CommonResponse<List<OrderTrackerStatusResponse>> getAllOrderTrackerStatus() {
		log.info("Get All Order Status");
		return orderDetailService.getAllOrderTrackerStatus();
	}

	@Operation(summary = "Confirm Order Detail", description = "Confirm Order Detail")
	@PutMapping("/order/confirm/{id}")
	public CommonResponse<OrderDetailResponse> confirmOrderDetail(@PathVariable("id") Long id) {
		log.info("Confirm Order Detail By Id {} ",id);
		return orderDetailService.confirmOrderDetail(id);
	}

	@Operation(summary = "Shipped Order Detail", description = "Shipped Order Detail")
	@PutMapping("/order/shipped/{id}")
	public CommonResponse<OrderDetailResponse> shippedOrderDetail(@PathVariable("id") Long id) {
		log.info("Shipped Order Detail By Id {} ",id);
		return orderDetailService.shippedOrderDetail(id);
	}

	@Operation(summary = "Deliver Order Detail", description = "Deliver Order Detail")
	@PutMapping("/order/deliver/{id}")
	public CommonResponse<OrderDetailResponse> deliverOrderDetail(@PathVariable("id") Long id) {
		log.info("Deliver Order Detail By Id {} ",id);
		return orderDetailService.deliverOrderDetail(id);
	}

	@Operation(summary = "Placed Order Detail", description = "Placed Order Detail")
	@PutMapping("/order/placed/{id}")
	public CommonResponse<OrderDetailResponse> placedOrderDetail(@PathVariable("id") Long id) {
		log.info("Placed Order Detail By Id {} ",id);
		return orderDetailService.placedOrderDetail(id);
	}

	@Operation(summary = "Cancel Order Detail", description = "Cancel Order Detail")
	@PutMapping("/order/cancel/{id}")
	public CommonResponse<OrderDetailResponse> cancelOrderDetail(@PathVariable("id") Long id) {
		log.info("Cancel Order Detail By Id {} ",id);
		return orderDetailService.cancelOrderDetail(id);
	}

	@Operation(summary = "OutForDeliver Order Detail", description = "OutForDeliver Order Detail")
	@PutMapping("/order/outForDeliver/{id}")
	public CommonResponse<OrderDetailResponse> outForDeliverOrderDetail(@PathVariable("id") Long id) {
		log.info("OutForDeliver Order Detail By Id {} ",id);
		return orderDetailService.outForDeliverOrderDetail(id);
	}

	@Operation(summary = "Return Order Detail", description = "Return Order Detail")
	@PutMapping("/order/return/{id}")
	public CommonResponse<OrderDetailResponse> returnOrderDetail(@PathVariable("id") Long id) {
		log.info("Return Order Detail By Id {} ",id);
		return orderDetailService.returnOrderDetail(id);
	}
}
