package com.bookstore.customer.request;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
public class AddressRequest {

	private Long addressId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private Long countryId;

	public AddressRequest(OrderDetailRequest orderDetailRequest){
		this.firstName = orderDetailRequest.getFirstName();
		this.lastName = orderDetailRequest.getLastName();
		this.address = orderDetailRequest.getAddress();
		this.city = orderDetailRequest.getCity();
		this.state = orderDetailRequest.getState();
		this.zip = orderDetailRequest.getZip();
		this.phoneNumber = orderDetailRequest.getPhoneNumber();
		this.countryId = orderDetailRequest.getCountryId();
	}
}
