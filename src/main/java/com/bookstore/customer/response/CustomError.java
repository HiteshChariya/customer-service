package com.bookstore.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {

//	@Schema(description = "error code", example = "")
	private int errorCode;
//	@Schema(description = "Message which generated according to success or error occured", example = "OK OR Invalid data")
	private String errorMessage;
//	@Schema(description = "error type", example = "")
	private String type;
}
