package com.bookstore.customer.constant;

public final class Constants {

	private Constants() {
	}

	public static final String AUTH_HEADER = "authorization";
	public static final String ORIGIN_HEADER = "origin";
	public static final String USER_AGENT_HEADER = "user-agent";
	public static final String REMOTE_IP = "remote-ip";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String AUTHORITIES = "authorities";

	public static final String DB_DRIVER = "dbDriver";
	public static final String DB_URL = "dbUrl";
	public static final String DB_USER = "dbUser";
	public static final String DB_PASSWORD = "dbPassword";
	public static final String AWS_ACCESS_KEY = "awsAccessKey";
	public static final String AWS_SECRET_KEY = "awsSecretKey";
	public static final String BUCKET_NAME = "bucketName";
	public static final String BUCKET_URL = "bucketUrl";
	public static final String API_KEY_SECRET_VALUE = "apiKeySecretValue";
	

//	public static final String DOCUMENT_SCHEMAS = "data";
//	public static final String CONF_SCHEMAS = "conf";
	public static final String CONF_REVIEW_SCHEMAS = "conf";
	public static final String PREFERENCE_SCHEMAS = "pref";
	public static final String DATA_REVIEW_SCHEMA = "data";
	public static final String AUTH_REVIEW_SCHEMA = "auth";

	public static final String AUTHORIZATION = "Authorization";

	public static final String CLIENT_CODE = "CL";
	public static final String PARTNER_CODE = "PT";
	public static final String THIRD_PARTY_CODE = "TP";

	public static final String PENDING = "Pending";

	public static final String REQUEST_ORIGIN = "rqsOrigin";
	public static final String BSINT = "bsint";
	public static final String EQ_INTEGRATION = "equabliIntegration";
	public static final String AWS_SEPARATOR = "/";

	public static final String DOMAIN_CODE = "DEBT";
	public static final String EQ_DOCS_CODE = "EQDS";
	
	public static final String CONFIG_TYPE_NATIVE = "NATIVE";
	
	public static final String S3 = "S3";
	
	public final class Application {
		private Application() {
		}

		public static final String BS_SHOP = "BS_SHOP";
	
	}

	public final class ApiKey {

		private ApiKey() {
		}

		public static final String API_KEY_HEADER_NAME = "X-API-KEY";
		public static final String API_KEY_INVALID = "api.key.invalid";
		public static final String API_KEY_MISSING = "api.key.missing";
		public static final String API_KEY_EXPIRE = "api.key.expire";
		public static final String API_KEY_GENERATE_ERROR = "api.key.generate.error";
	}
	
}