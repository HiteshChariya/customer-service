package com.bookstore.customer.entity;

import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.Constants;
import com.bookstore.customer.request.ReviewRatingRequest;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "review_ratting", schema = Constants.DATA_REVIEW_SCHEMA)
@Getter
public class ReviewRatting extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_ratting_id")
    private Long id;

    @Column(length = 1000)
    private String review;

    @Column(length = 10)
    private Long rate;

    @Column(length = 20)
    private String reviewStatus;

    @Column(length = 50)
    private String reviewerName;

    private Long orderId;
    private Long bookId;

    public void saveReviewRatting(ReviewRatting reviewRatting, ReviewRatingRequest reviewRatingRequest, TokenData tokenData, String recordStatus, String recordSource) {
        reviewRatting.review = reviewRatingRequest.getReview();
        reviewRatting.rate = reviewRatingRequest.getRate();
        reviewRatting.reviewStatus = reviewRatingRequest.getReviewStatus();
        reviewRatting.reviewerName = reviewRatingRequest.getReviewerName();
        reviewRatting.orderId = reviewRatingRequest.getOrderId();
        reviewRatting.bookId = reviewRatingRequest.getBookId();
        if(id == null)
            reviewRatting.create(recordStatus, tokenData.getFirstName(),recordSource);
        else
            reviewRatting.update(recordStatus,tokenData.getFirstName());
    }
}
