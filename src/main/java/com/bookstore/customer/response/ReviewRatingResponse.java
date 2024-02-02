package com.bookstore.customer.response;

import com.bookstore.customer.constant.MessageConstant;
import com.bookstore.customer.entity.ReviewRatting;
import com.bookstore.customer.request.ReviewRatingRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewRatingResponse {

    private Long id;
    private String review;
    private Long rate;
    private Long orderId;
    private Long bookId;
    @JsonFormat(pattern = MessageConstant.DATE_FORMATE)
    private LocalDateTime reviewDate;
    private String reviewStatus;
    private String reviewerName;
    private Long totalReview;
    private Long totalRate;

    public ReviewRatingResponse(ReviewRatting reviewRating){
        this.id = reviewRating.getId();
        this.review = reviewRating.getReview();
        this.rate = reviewRating.getRate();
        this.orderId = reviewRating.getOrderId();
        this.bookId = reviewRating.getBookId();
        this.reviewDate = reviewRating.getCreatedAt();
        this.reviewStatus = reviewRating.getReviewStatus();
        this.reviewerName = reviewRating.getReviewerName();
    }
}
