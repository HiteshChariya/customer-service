package com.bookstore.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bookstore.customer.request.OrderHistoryRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.OrderHistoryResponse;
import com.bookstore.customer.service.OrderHistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer/order")
@Tag(name = "Order History Configuration API", description = "Order History Configuration API for BookStore Managemant")
@Slf4j
public class OrderHistoryController {
	
	@Autowired
	OrderHistoryService orderHistoryService;

	@Operation(summary = "Save Order History Details", description = "Save Order History Details")
	@PostMapping("/history")
	public CommonResponse<String> saveOrderHistory(@RequestBody OrderHistoryRequest orderHistoryRequest) {
		log.info("Save Order History {} ",orderHistoryRequest);
		return orderHistoryService.saveOrderHistory(orderHistoryRequest);
	}
	
	@Operation(summary = "Get Order History Details", description = "Get Order History Details")
	@GetMapping("/history")
	public CommonResponse<List<OrderHistoryResponse>> getAllOrderHistory() {
		log.info("Get All Order History {} ");
		return orderHistoryService.getAllOrderHistory();
	}
	
	@Operation(summary = "Delete Order History Details", description = "Delete Order History Details")
	@PatchMapping("/history/{id}")
	public CommonResponse<String> deleteOrderHistory(@PathVariable("id") Long id) {
		log.info("Delete Order History {} ",id);
		return orderHistoryService.deleteOrderHistory(id);
	}

	@Operation(summary = "Get Order History By Id", description = "Get Order History By Id")
	@GetMapping("/history/byOrderId")
	public CommonResponse<OrderHistoryResponse> getOrderHistoryById(@RequestParam("id") Long id) {
		log.info("Get Order History By Id {} ",id);
		return orderHistoryService.getOrderHistoryById(id);
	}
}
