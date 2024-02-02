package com.bookstore.customer.response;

import com.bookstore.customer.entity.Country;

import lombok.Data;

@Data
public class CountryResponse {

	private Long countryId;
	private String countryName;
	private String countryCode;
	
	public CountryResponse(Country country) {
		this.countryId = country.getId();
		this.countryName = country.getName();
		this.countryCode = country.getCode();
	}
	
}
