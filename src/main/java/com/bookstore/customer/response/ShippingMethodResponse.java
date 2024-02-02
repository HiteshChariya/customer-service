package com.bookstore.customer.response;

import com.bookstore.customer.entity.ShippingMethod;

import lombok.Data;

@Data
public class ShippingMethodResponse {

	private Long id;
	private String methodName;
	private Long cost;
	
	public ShippingMethodResponse(ShippingMethod shippingMethod) {
		this.id = shippingMethod.getId();
		this.methodName = shippingMethod.getMethodName();
		this.cost = shippingMethod.getCost();
	}
}
