package com.bookstore.customer.repository;

import com.bookstore.customer.entity.OrderStatus;
import com.bookstore.customer.entity.OrderTrakerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderTrackerStatusRepository extends JpaRepository<OrderTrakerStatus, Long>{

    List<OrderTrakerStatus> findByRecordStatusCode(String recordStatus);

    Optional<OrderTrakerStatus> findByOrderTrakerStatue(String status);
}
