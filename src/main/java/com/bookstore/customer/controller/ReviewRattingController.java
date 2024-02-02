package com.bookstore.customer.controller;

import com.bookstore.customer.request.CartDetailRequest;
import com.bookstore.customer.request.ReviewRatingRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.ReviewRatingResponse;
import com.bookstore.customer.service.ReviewRattingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/")
@Tag(name = "Review Ratting Configuration API", description = "Review Ratting Configuration API for BookStore Management")
@Slf4j
@CrossOrigin("http://localhost/3000")
public class ReviewRattingController {

    @Autowired
    ReviewRattingService reviewRattingService;

    @Operation(summary = "Save Review Ratting", description = "Save Review Ratting")
    @PostMapping("reviewRate")
    public CommonResponse<String> saveReviewRatting(@RequestBody ReviewRatingRequest reviewRatingRequest) {
        log.info("Save Review Ratting {} ",reviewRatingRequest);
        return reviewRattingService.saveReviewRatting(reviewRatingRequest);
    }

    @Operation(summary = "Fetch Review Ratting", description = "Fetch Review Ratting")
    @GetMapping
    public CommonResponse<List<ReviewRatingResponse>> fetchReviewRatting() {
        log.info("Fetch Review Ratting ");
        return reviewRattingService.fetchReviewRatting();
    }

    @Operation(summary = "Fetch Review Ratting By Id", description = "Fetch Review Ratting By Id")
    @GetMapping("reviewRate/byProduct")
    public CommonResponse<List<ReviewRatingResponse>> fetchReviewRattingByProduct(@RequestParam("id") Long id) {
        log.info("Fetch Review Ratting Ratting By Id {} ", id);
        return reviewRattingService.fetchReviewRattingByProduct(id);
    }

    @Operation(summary = "Fetch Review Ratting By Id", description = "Fetch Review Ratting By Id")
    @GetMapping("reviewRate/byOrder")
    public CommonResponse<List<ReviewRatingResponse>> fetchReviewRattingByOrder(@RequestParam("id") Long id) {
        log.info("Fetch Review Ratting Ratting By Id {} ", id);
        return reviewRattingService.fetchReviewRattingByOrder(id);
    }

    @Operation(summary = "Delete Review Ratting By Id", description = "Delete Review Ratting By Id")
    @PatchMapping("reviewRate/{id}")
    public CommonResponse<ReviewRatingResponse> deleteReviewRattingById(@PathVariable("id") Long id) {
        log.info("Delete Review Ratting Ratting By Id {} ", id);
        return reviewRattingService.deleteReviewRattingById(id);
    }

}
