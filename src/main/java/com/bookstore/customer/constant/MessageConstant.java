package com.bookstore.customer.constant;

public final class MessageConstant {

	private MessageConstant() {
	}
	
	public static final String NOT_VALID = "not.valid";
	public static final String INVALID_DATA = "invalid.data";
	public static final String NOT_NULL = "not.null";
	public static final String USER_TYPE = "User Type";
	public static final String NOT_VALID_ID = "not.valid.id";
	public static final String FILE_CONCAT_VAL = "FileConcatVal";
	public static final String NOT_VALID_PRODUCT_ID = "not.valid.product.id";
	public static final String SHORT_NAME_ALREADY_EXITST = "short.name.already.exitst";
	public static final String DATE_FORMATE = "MM/dd/yyyy";
	public static final String NOT_VALID_SHORT_CODE = "not.valid.short.code";

	public final class PageConstants {
		public static final String DEFAULT_PAGE_NUMBER = "0";
		public static final String DEFAULT_PAGE_SIZE = "10";
		public static final String DEFAULT_SORT_BY = "id";
		public static final String DEFAULT_SORT_DIRECTION = "asc";
	}

	public final class AuthConstants {

		public static final String MISSING_TOKEN = "authorization.token.missing";
		public static final String TOKEN_EXPIRED = "authorization.token.expired";
		public static final String MISSING_BEARER = "authorization.token.missing.bearer";
		public static final String INVALID_LOGIN_KEY = "authorization.token.invalid.login.key";

	}
}
