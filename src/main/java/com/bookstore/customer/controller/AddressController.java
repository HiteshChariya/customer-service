package com.bookstore.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.customer.request.AddressRequest;
import com.bookstore.customer.response.AddressResponse;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer")
@Tag(name = "Address Configuration API", description = "Address Configuration API for BookStore Managemant")
@Slf4j
public class AddressController {
	
	@Autowired
	AddressService addressService;

	@Operation(summary = "Save Address Details", description = "Save Address Details")
	@PostMapping("/address")
	public CommonResponse<String> saveShiipingAddress(@RequestBody AddressRequest addressRequest) {
		log.info("Save Address {} ",addressRequest);
		return addressService.saveShiipingAddress(addressRequest);
	}
	
	@Operation(summary = "Get Address Details", description = "Fetch Address Details")
	@GetMapping("/address")
	public CommonResponse<List<AddressResponse>> getAllAddress(){
		log.info("Fetch All Addresses {} ");
		return addressService.getAllAddress();
	}
	
	@Operation(summary = "Delete Address Details", description = "Delete Address Details")
	@PatchMapping("/address/{id}")
	public CommonResponse<String> dalateAddressById(@PathVariable("id") Long id){
		log.info("Delete Address By Id {} ", id);
		return addressService.dalateAddressById(id);
	}
	
	@Operation(summary = "Fetch Address Details By id", description = "Fetch Address Details By id")
	@GetMapping("/address/byCustomerId")
	public CommonResponse<List<AddressResponse>> getAddressesByCustomer(@RequestParam("id") Long id){
		log.info("Fetch Address By Id {} ", id);
		return addressService.getAddressesByCustomer(id);
	}
}
