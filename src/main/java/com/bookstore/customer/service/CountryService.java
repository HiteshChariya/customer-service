package com.bookstore.customer.service;

import java.util.List;

import com.bookstore.customer.request.CountryRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.CountryResponse;

public interface CountryService {

	CommonResponse<String> saveCountry(CountryRequest countryRequest);

	CommonResponse<List<CountryResponse>> getAllCountry();

	CommonResponse<String> dalateCountryById(Long id);

	CommonResponse<CountryResponse> getCountryById(Long id);

}
