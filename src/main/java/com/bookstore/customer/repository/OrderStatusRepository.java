package com.bookstore.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.customer.entity.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long>{

	Optional<OrderStatus> findByIdAndRecordStatusCode(Long id, String recordStatus);

	List<OrderStatus> findByRecordStatusCode(String recordStatus);

}
