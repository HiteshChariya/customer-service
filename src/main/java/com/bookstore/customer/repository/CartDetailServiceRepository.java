package com.bookstore.customer.repository;

import com.bookstore.customer.entity.Address;
import com.bookstore.customer.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailServiceRepository extends JpaRepository<CartDetail, Long> {

    Optional<CartDetail> findByIdAndRecordStatusCode(Long cartId, String recordStatus);

    List<CartDetail> findByRecordStatusCode(String shortCode);

    Optional<CartDetail> findByBookIdAndRecordStatusCode(Long id, String shortCode);

    List<CartDetail> findByCustomerIdAndRecordStatusCode(Long id, String shortCode);

    List<CartDetail> findByCustomerIdAndOrderStatusIdAndRecordStatusCode(Long id, Long orderStatusId, String shortCode);

    Optional<CartDetail> findByBookIdAndCustomerIdAndRecordStatusCode(long bookId, Long customerId, String shortCode);
}
