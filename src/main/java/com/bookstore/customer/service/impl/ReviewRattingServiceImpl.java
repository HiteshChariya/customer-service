package com.bookstore.customer.service.impl;

import com.bookstore.customer.auth.FetchTokenData;
import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.CustomerConstants;
import com.bookstore.customer.entity.ReviewRatting;
import com.bookstore.customer.enums.RecordSourceCode;
import com.bookstore.customer.enums.RecordStatusCode;
import com.bookstore.customer.repository.ReviewRatingRepository;
import com.bookstore.customer.request.ReviewRatingRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.ReviewRatingResponse;
import com.bookstore.customer.service.ReviewRattingService;
import com.bookstore.customer.utils.MessageSupplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReviewRattingServiceImpl implements ReviewRattingService {

    @Autowired
    FetchTokenData fetchTokenData;

    @Autowired
    MessageSupplier messageSupplier;

    @Autowired
    ReviewRatingRepository reviewRatingRepository;

    @Override
    public CommonResponse<String> saveReviewRatting(ReviewRatingRequest reviewRatingRequest) {
        log.info("Save Review Ratting service..");
        TokenData tokenData = fetchTokenData.getTokenData();
        String recordStatus = RecordStatusCode.ENABLED.getShortCode();
        String recordSource = RecordSourceCode.ECP_WB.getShortName();
        ReviewRatting reviewRatting = new ReviewRatting();
        reviewRatting.saveReviewRatting(reviewRatting,reviewRatingRequest,tokenData,recordStatus,recordSource);
        reviewRatingRepository.save(reviewRatting);
        return CommonResponse.success(messageSupplier.get(CustomerConstants.ReviewRateConstant.REVIEW_RATE_SAVE_SUCCESS));
    }

    @Override
    public CommonResponse<List<ReviewRatingResponse>> fetchReviewRatting() {
        log.info("Fetch All Review Ratting service..");
        TokenData tokenData = fetchTokenData.getTokenData();
        String recordStatus = RecordStatusCode.ENABLED.getShortCode();
        List<ReviewRatingResponse> reviewRattings = reviewRatingRepository.findByRecordStatusCode(recordStatus).
                stream().map(m->new ReviewRatingResponse(m)).collect(Collectors.toList());
        return CommonResponse.success(messageSupplier.get(CustomerConstants.ReviewRateConstant.REVIEW_RATE_FETCH_SUCCESS),reviewRattings);
    }

    @Override
    public CommonResponse<List<ReviewRatingResponse>> fetchReviewRattingByProduct(Long id) {
        log.info("Fetch All Review Ratting By Product service..");
        TokenData tokenData = fetchTokenData.getTokenData();
        String recordStatus = RecordStatusCode.ENABLED.getShortCode();
        List<ReviewRatting> reviewRattings = reviewRatingRepository.findByBookIdAndRecordStatusCode(id,recordStatus);
        if(reviewRattings.isEmpty()){
            return CommonResponse.failure(messageSupplier.get(CustomerConstants.ReviewRateConstant.INVALID_REVIEW_RATE_ID,id));
        }

        List<ReviewRatingResponse> reviewRattingResponse = reviewRattings.stream().map(m->new ReviewRatingResponse(m)).collect(Collectors.toList());
        reviewRattingResponse.forEach(r->{
            r.setTotalReview(5l);
            r.setTotalRate(5l);
        });
        return CommonResponse.failure(messageSupplier.get(CustomerConstants.ReviewRateConstant.REVIEW_RATE_FETCH_SUCCESS),reviewRattingResponse);
    }

    @Override
    public CommonResponse<List<ReviewRatingResponse>> fetchReviewRattingByOrder(Long id) {
        return null;
    }

    @Override
    public CommonResponse<ReviewRatingResponse> deleteReviewRattingById(Long id) {
        return null;
    }

}
