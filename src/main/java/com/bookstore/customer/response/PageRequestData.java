package com.bookstore.customer.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
@SuperBuilder
@NoArgsConstructor
public class PageRequestData {

	@Schema(description = "Page Size of to be batch 10,20 ", example = "10")
	private Integer pageSize;

	@Schema(description = "Current Page Number in response ", example = "0")
    private Integer pageNumber;
	
	@Schema(description = "Sorting order in response ", example = "asc")
	private String sortOrder;
	
	@Schema(description = "Sort in response as per sort param ", example = "name")
	private String sortParam;

}
