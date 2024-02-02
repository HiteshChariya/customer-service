package com.bookstore.customer.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ReviewRatingRequest {

    private Long id;
    private String review;
    private Long rate;
    private Long orderId;
    private Long bookId;
    private String reviewStatus;
    private String reviewerName;
}
