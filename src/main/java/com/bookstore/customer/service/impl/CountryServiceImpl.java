package com.bookstore.customer.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.customer.auth.FetchTokenData;
import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.CustomerConstants;
import com.bookstore.customer.entity.Country;
import com.bookstore.customer.enums.RecordSourceCode;
import com.bookstore.customer.enums.RecordStatusCode;
import com.bookstore.customer.repository.CountryRepository;
import com.bookstore.customer.request.CountryRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.CountryResponse;
import com.bookstore.customer.service.CountryService;
import com.bookstore.customer.utils.MessageSupplier;
import com.bookstore.customer.utils.ShortCodeGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService{

	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	FetchTokenData fetchTokenData;
	
	@Autowired
	MessageSupplier messageSupplier;			
	
	@Override
	public CommonResponse<String> saveCountry(CountryRequest countryRequest) {
		log.info("Save Country Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		String source = RecordSourceCode.ECP_WB.getShortName();
		String shortCode = ShortCodeGenerator.generateShortCode(countryRequest.getCountryName());
		Country country = new Country();
		Optional<Country> optCountry = countryRepository.findById(countryRequest.getCountryId());
		if (optCountry.isPresent()) {
			country = optCountry.get();
		}

		country.saveCountry(country, countryRequest, tokenData, shortCode, recordStatus, source);
		countryRepository.save(country);
		return CommonResponse.success(messageSupplier.get(CustomerConstants.CountryConstant.COUNTRY_SAVE_SUCCESS));
	}

	@Override
	public CommonResponse<List<CountryResponse>> getAllCountry() {
		log.info("Save Country Service..");
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		List<CountryResponse> countries = countryRepository.findByRecordStatusCode(recordStatus).stream()
				.map(l -> new CountryResponse(l)).collect(Collectors.toList());
		return CommonResponse.success(messageSupplier.get(CustomerConstants.CountryConstant.COUNTRY_FETCH_SUCCESS),
				countries);
	}

	@Override
	public CommonResponse<String> dalateCountryById(Long id) {
		log.info("Delete Country Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String status = RecordStatusCode.DISABLED.getShortCode();
		Optional<Country> optCountry = countryRepository.findById(id);
		if(optCountry.isEmpty())
			return CommonResponse.failure(messageSupplier.get(CustomerConstants.CountryConstant.INVALIE_COUNTRY_ID, id));
		
		Country country = optCountry.get();
		country.deleteCountry(country,tokenData,status);
		countryRepository.save(country);
		return CommonResponse.success(messageSupplier.get(CustomerConstants.CountryConstant.COUNTRY_DELETE_SUCCESS, id));
	}

	@Override
	public CommonResponse<CountryResponse> getCountryById(Long id) {
		log.info("Get Country Service..");
		String status = RecordStatusCode.DISABLED.getShortCode();
		Optional<Country> optCountry = countryRepository.findByIdAndRecordStatusCode(id,status);
		if(optCountry.isPresent()) {
			Country country = optCountry.get();
			return CommonResponse.failure(messageSupplier.get(CustomerConstants.CountryConstant.COUNTRY_FETCH_SUCCESS, country));
		}
		return CommonResponse.failure(messageSupplier.get(CustomerConstants.CountryConstant.INVALIE_COUNTRY_ID, id));
	}
	
	

}
