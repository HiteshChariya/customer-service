package com.bookstore.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.Constants;
import com.bookstore.customer.request.ShippingMethodRequest;

import lombok.Getter;

@Entity
@Table(name = "shipping_method", schema = Constants.DATA_REVIEW_SCHEMA)
@Getter
public class ShippingMethod extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shipping_method_id")
	private Long id;
	
	@Column(length = 100)
	private String methodName;
	private Long cost;
	
	public void saveShippingMethod(ShippingMethod updateShippingMethod, TokenData tokenData, String recordStatus,
			String source, ShippingMethodRequest shippingMethodRequest) {
		updateShippingMethod.methodName = shippingMethodRequest.getMethodName();
		updateShippingMethod.cost = shippingMethodRequest.getCost();
		if(id == null)
			updateShippingMethod.create(recordStatus, tokenData.getFirstName(), source);
		else
			updateShippingMethod.update(recordStatus, tokenData.getFirstName());
	}

	public void updateShippingMethod(ShippingMethod dltShippingMethod, TokenData tokenData, String recordStatus) {
		dltShippingMethod.update(recordStatus, tokenData.getFirstName());
	}
	
}
