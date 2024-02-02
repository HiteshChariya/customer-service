package com.bookstore.customer.service;

import com.bookstore.customer.request.ReviewRatingRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.ReviewRatingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReviewRattingService {
    CommonResponse<String> saveReviewRatting(ReviewRatingRequest reviewRatingRequest);

    CommonResponse<List<ReviewRatingResponse>> fetchReviewRatting();

    CommonResponse<List<ReviewRatingResponse>> fetchReviewRattingByProduct(Long id);

    CommonResponse<List<ReviewRatingResponse>> fetchReviewRattingByOrder(Long id);

    CommonResponse<ReviewRatingResponse> deleteReviewRattingById(Long id);
}
