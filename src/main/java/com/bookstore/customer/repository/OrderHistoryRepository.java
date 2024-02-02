package com.bookstore.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.customer.entity.OrderHistory;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long>{

	Optional<OrderHistory> findByIdAndRecordStatusCode(Long id, String recordStatus);

	List<OrderHistory> findByRecordStatusCode(String recordStatus);

}
