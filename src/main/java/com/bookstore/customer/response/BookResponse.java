package com.bookstore.customer.response;

import java.time.LocalDate;

import com.bookstore.customer.constant.MessageConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {

	private Long bookId;
	private String bookName;
	private String bookcode;
	private String language;
	private String publisher;
	private String author;
	private String category;
	private String bookDescription;
	private String isbn;
	private Long price;
	private Long quantityAvailable;
	private String image;
	private Long discount;
	private Boolean isDiscount;
	private Boolean isReturnable;
	private Long review;
	private Long weight;
	private Long heigth;
	private Long noOfPages;
	private String subTitle;
	private Long cartItem;
}
