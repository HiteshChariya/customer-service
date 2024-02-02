package com.bookstore.customer.response;

import com.bookstore.customer.entity.Address;

import lombok.Data;

@Data
public class AddressResponse {

	private Long addressId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private Long contryId;
	private String customer;

	public AddressResponse(Address address) {
		this.addressId = address.getId();
		this.firstName = address.getFirstName();
		this.lastName = address.getLastName();
		this.address = address.getAddress();
		this.city = address.getCity();
		this.state = address.getState();
		this.zip = address.getZip();
		this.phoneNumber = address.getPhoneNumber();
		this.contryId = address.getContryId();
	}
}
