package com.bookstore.customer.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagedData <T> {
	
	private PageMetaData metadata;
    private List<T> datas;

}
