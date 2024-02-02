package com.bookstore.customer.repository;

import com.bookstore.customer.entity.OrderHistory;
import com.bookstore.customer.entity.ReviewRatting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ReviewRatingRepository extends JpaRepository<ReviewRatting, Long> {
    List<ReviewRatting> findByRecordStatusCode(String recordStatus);

    List<ReviewRatting> findByIdAndRecordStatusCode(Long id, String recordStatus);

    List<ReviewRatting> findByBookIdAndRecordStatusCode(Long id, String recordStatus);
}
