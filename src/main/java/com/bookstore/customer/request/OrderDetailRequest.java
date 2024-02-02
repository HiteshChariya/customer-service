package com.bookstore.customer.request;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDetailRequest {

	private Long id;
	private Long addressId;
	private List<Long> productIds;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private Long countryId;
}
