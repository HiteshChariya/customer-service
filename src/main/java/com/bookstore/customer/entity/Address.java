package com.bookstore.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.Constants;
import com.bookstore.customer.request.AddressRequest;

import lombok.Getter;

@Entity
@Table(name = "address", schema = Constants.DATA_REVIEW_SCHEMA)
@Getter
public class Address extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long id;

	@Column(length = 50)
	private String firstName;
	@Column(length = 50)
	private String lastName;
	@Column(length = 1000)
	private String address;
	@Column(length = 50)
	private String city;
	@Column(length = 10)
	private String state;
	@Column(length = 50)
	private String zip;
	@Column(length = 10)
	private String phoneNumber;
	private Long contryId;
	private Long customerId;
	
	public void saveAddress(Address updateAddress, AddressRequest addressRequest, TokenData tokenData,
			String recordStatus, String source) {
		updateAddress.firstName = addressRequest.getFirstName();
		updateAddress.lastName = addressRequest.getLastName();
		updateAddress.address = addressRequest.getAddress();
		updateAddress.city = addressRequest.getCity();
		updateAddress.state = addressRequest.getState();
		updateAddress.zip = addressRequest.getZip();
		updateAddress.phoneNumber = addressRequest.getPhoneNumber();
		updateAddress.contryId = addressRequest.getCountryId();
		updateAddress.customerId = tokenData.getId();
		if(id == null)
			updateAddress.create(recordStatus, tokenData.getFirstName(), source);
		else
			updateAddress.update(recordStatus, tokenData.getFirstName());
		
	}

	public void deleteAddress(Address address, TokenData tokenData, String recordStatus) {
		address.update(recordStatus, tokenData.getFirstName());
	}
}
