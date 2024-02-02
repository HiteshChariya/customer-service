package com.bookstore.customer.response;

import com.bookstore.customer.constant.MessageConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDetailResponse {
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
    private Long quantity;
    private String image;
    private Long discount;
    private Boolean isDiscount;
    private Boolean isReturnable;
    private Long review;
    private Long weight;
    private Long heigth;
    private Long noOfPages;
    private String subTitle;

}
