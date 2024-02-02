package com.bookstore.customer.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagedResponse<T> extends CommonResponse<PagedData<T>> {

	private Integer start;

	private Integer limit;

	private Integer total;

	protected Boolean lastPage;

	public static <T> PagedResponse<T> empty() {
		List<T> results = new ArrayList<>();
		return of(results, null);
	}

	public PagedResponse(PagedData<T> pagedData) {
		super(null, OperationStatus.SUCCESS, pagedData, true, null);
	}

	public PagedResponse(String message, List<CustomError> errors) {
		super(message, OperationStatus.FAILURE, null, false, errors);
	}

//20 4 60
	public static <T> PagedResponse<T> of(List<T> results, int start, int limit, int total) {

		int pageCount = total != 0 ? total % limit == 0 ? total / limit : (total / limit)+1 : 0;
		int pageNumber = start != 0 ? start / limit : 0;
		PageMetaData pageMetaData = PageMetaData.builder().recordCount(total).pageSize(limit).startRecord(start)
				.pageCount(pageCount).pageNumber(pageNumber).build();
		return of(results, pageMetaData);
	}

	public static <T> PagedResponse<T> of(List<T> results, PageMetaData metaData) {
		PagedData<T> pagedData = new PagedData<>();
		pagedData.setDatas(results);
		pagedData.setMetadata(metaData);
		return new PagedResponse<>(pagedData);
	}

	/**
	 * Set Page meta data
	 * 
	 * @param <T>
	 * @param results
	 * @param start
	 * @param pageNumber
	 * @param size
	 * @param totalElements
	 * @param totalPages
	 * @return
	 */
	public static <T> PagedResponse<T> of(List<T> results, int pageNumber, int size, int totalElements,
			int totalPages) {

		PageMetaData pageMetaData = PageMetaData.builder().recordCount(totalElements).pageSize(size)
				.pageCount(totalPages).pageNumber(pageNumber).build();
		return of(results, pageMetaData);
	}

	public static <T, K> PagedResponse<T> of(List<T> results, K columns, int pageNumber, int size, int totalElements,
			int totalPages) {

		PageMetaData pageMetaData = PageMetaData.builder().recordCount(totalElements).pageSize(size)
				.pageCount(totalPages).pageNumber(pageNumber).columnPreferences(columns).build();
		return of(results, pageMetaData);
	}

	public static <T, K> PagedResponse<T> of(List<T> results, K columns, int start, int limit, int total) {

		int pageCount = total != 0 ? total / limit : 0;
		int pageNumber = start != 0 ? start / limit : 0;
		PageMetaData pageMetaData = PageMetaData.builder().recordCount(total).pageSize(limit).startRecord(start)
				.pageCount(pageCount).pageNumber(pageNumber).columnPreferences(columns).build();
		return of(results, pageMetaData);
	}
	
	public static <T, K> PagedResponse<T> of(List<T> results, K columns, K attribute, int pageNumber, int size,
			int totalElements, int totalPages) {

		PageMetaData pageMetaData = PageMetaData.builder().recordCount(totalElements).pageSize(size)
				.pageCount(totalPages).pageNumber(pageNumber).columnPreferences(columns).allAttribute(attribute)
				.build();
		return of(results, pageMetaData);
	}
	
	public static <T, K> PagedResponse<T> of(List<T> results, K columns, K attribute, int start, int limit, int total) {

		int pageCount = total != 0 ? total / limit : 0;
		int pageNumber = start != 0 ? start / limit : 0;
		PageMetaData pageMetaData = PageMetaData.builder().recordCount(total).pageSize(limit).startRecord(start)
				.pageCount(pageCount).pageNumber(pageNumber).columnPreferences(columns).allAttribute(attribute).build();
		return of(results, pageMetaData);
	}
}
