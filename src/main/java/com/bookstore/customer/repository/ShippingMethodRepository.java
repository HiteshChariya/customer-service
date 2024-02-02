package com.bookstore.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.customer.entity.ShippingMethod;

@Repository
public interface ShippingMethodRepository extends JpaRepository<ShippingMethod, Long>{

	Optional<ShippingMethod> findByIdAndRecordStatusCode(Long id, String recordStatus);

	List<ShippingMethod> findByRecordStatusCode(String recordStatus);

}
