package com.bookstore.customer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecordStatusCode {
	
	ENABLED("EA"), DISABLED("DA"), DELETED("DL");

	private String shortCode ;

}
