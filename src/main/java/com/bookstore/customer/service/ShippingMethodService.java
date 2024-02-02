package com.bookstore.customer.service;

import java.util.List;

import com.bookstore.customer.request.ShippingMethodRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.ShippingMethodResponse;

public interface ShippingMethodService {

	CommonResponse<String> saveShippingMethod(ShippingMethodRequest shippingMethodRequest);

	CommonResponse<List<ShippingMethodResponse>> getAllShippingMethod();

	CommonResponse<String> dalateShippingMethodById(Long id);

}
