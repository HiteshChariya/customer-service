package com.bookstore.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.Constants;
import com.bookstore.customer.request.CountryRequest;

import lombok.Getter;

@Entity
@Table(name = "country", schema = Constants.DATA_REVIEW_SCHEMA)
@Getter
public class Country extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private Long id;
	
	@Column(length = 50)
	private String name;
	
	@Column(length = 10)
	private String code;

	public void saveCountry(Country country, CountryRequest countryRequest, TokenData tokenData, String code,
			String status, String source) {
		country.name = countryRequest.getCountryName();
		country.code = code;
		if (id == null)
			country.create(status, tokenData.getFirstName(), source);
		else
			country.update(status, tokenData.getFirstName());
	}

	public void deleteCountry(Country country, TokenData tokenData, String status) {
		country.update(status, tokenData.getFirstName());
	}
}
