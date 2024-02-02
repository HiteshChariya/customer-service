package com.bookstore.customer.auth;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Model representing information required for Token Deseriallizing")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenData {

	private Long id;
	private String loginKey;
	private String loginSecret;
	private UUID userUuid;
	private String email;
	private String firstName;
	private String address;
	private String phone;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String userType;
	private String token;

}