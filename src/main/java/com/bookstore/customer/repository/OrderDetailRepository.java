package com.bookstore.customer.repository;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.customer.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

	Optional<OrderDetail> findByIdAndRecordStatusCode(Long id, String recordStatus);

	List<OrderDetail> findByRecordStatusCode(String recordStatus);

    List<OrderDetail> findByCustomerIdAndRecordStatusCode(Long id, String recordStatus);

	@Query(value = "SELECT od FROM com.bookstore.customer.entity.OrderDetail as od "
			+ " where od.recordStatusCode = :status "
			+ " AND (:statusId IS NULL OR od.orderStatusId = :statusId ) ")
	Page<OrderDetail> findAllOrders(@Param("statusId") Long statusId, @Param("status") String status, Pageable pageable);
}
