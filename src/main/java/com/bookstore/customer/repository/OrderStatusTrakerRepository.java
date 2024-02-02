package com.bookstore.customer.repository;

import com.bookstore.customer.entity.OrderTrakerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusTrakerRepository extends JpaRepository<OrderTrakerStatus,Long> {

}
