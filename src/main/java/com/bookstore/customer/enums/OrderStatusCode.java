package com.bookstore.customer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatusCode {

	PENDING("PENDING"), PLACED("PLACED"), DISPATCH("DISPATCH"), RECEIVE("RECEIVE");

	private String shortCode ;
}
