package com.bookstore.customer.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageMetaData<K> {

	private Integer pageSize;

    private Integer pageNumber;

	private Integer recordCount;
    
    private Integer pageCount;
    
    private Integer startRecord; 
	
	private K columnPreferences;
	
	private K allAttribute;

}
