package com.bookstore.customer.service;

import java.util.List;

import com.bookstore.customer.request.AddressRequest;
import com.bookstore.customer.response.AddressResponse;
import com.bookstore.customer.response.CommonResponse;

public interface AddressService {

	CommonResponse<String> saveShiipingAddress(AddressRequest addressRequest);

	CommonResponse<List<AddressResponse>> getAllAddress();

	CommonResponse<String> dalateAddressById(Long id);

	CommonResponse<List<AddressResponse>> getAddressesByCustomer(Long id);

}
