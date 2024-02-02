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

import com.bookstore.customer.request.CountryRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.CountryResponse;
import com.bookstore.customer.service.CountryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer")
@Tag(name = "Country Configuration API", description = "Country Configuration API for BookStore Managemant")
@Slf4j
public class CountryController {

	@Autowired
	CountryService countryService;
	
	@Operation(summary = "Save Country Details", description = "Save Country Details")
	@PostMapping("/country")
	public CommonResponse<String> saveCountry(@RequestBody CountryRequest countryRequest) {
		log.info("Save Country {} ",countryRequest);
		return countryService.saveCountry(countryRequest);
	}
	
	@Operation(summary = "Fetch Country Details", description = "Fetch Country Details")
	@GetMapping("/country")
	public CommonResponse<List<CountryResponse>> getAllCountry(){
		log.info("Fetch All Countries {} ");
		return countryService.getAllCountry();
	}
	
	@Operation(summary = "Delete Country Details", description = "Delete Country Details")
	@PatchMapping("/country/{id}")
	public CommonResponse<String> dalateCountryById(@PathVariable("id") Long id){
		log.info("Delete Country By Id {} ", id);
		return countryService.dalateCountryById(id);
	}
	
	@Operation(summary = "Fetch Country Details By Id", description = "Fetch Country Details By Id")
	@GetMapping("/country/countryById")
	public CommonResponse<CountryResponse> getCountryById(@RequestParam Long id){
		log.info("Fetch Country By Id {} ", id);
		return countryService.getCountryById(id);
	}
}
