package com.bookstore.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.customer.request.ShippingMethodRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.ShippingMethodResponse;
import com.bookstore.customer.service.ShippingMethodService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer/shipping")
@Tag(name = "Shipping Method Configuration API", description = "Shipping Method Configuration API for BookStore Managemant")
@Slf4j
public class ShippingMethodController {
	
	@Autowired
	ShippingMethodService shippingMethodService;

	@Operation(summary = "Save Shipping Method Details", description = "Save Shipping Method Details")
	@PostMapping("/method")
	public CommonResponse<String> saveShippingMethod(@RequestBody ShippingMethodRequest shippingMethodRequest) {
		log.info("Save Shipping Method {} ",shippingMethodRequest);
		return shippingMethodService.saveShippingMethod(shippingMethodRequest);
	}
	
	@Operation(summary = "Fetch Shipping Method Details", description = "Fetch Shipping Method Details")
	@GetMapping("/method")
	public CommonResponse<List<ShippingMethodResponse>> getAllShippingMethod(){
		log.info("Fetch All Shipping Method {} ");
		return shippingMethodService.getAllShippingMethod();
	}
	
	@Operation(summary = "Delete Shipping Method Details", description = "Delete Shipping Method Details")
	@PatchMapping("/method/{id}")
	public CommonResponse<String> dalateShippingMethodById(@PathVariable("id") Long id){
		log.info("Delete Shipping Method By Id {} ", id);
		return shippingMethodService.dalateShippingMethodById(id);
	}
	
}
