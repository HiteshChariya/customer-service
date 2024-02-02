package com.bookstore.customer.exception;

/**
 * When some one wanted to change add/update/delete and that particular id is
 * not exists
 *
 */
public class InvalidArgumentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidArgumentException(String message) {
		super(message);
	}
}
