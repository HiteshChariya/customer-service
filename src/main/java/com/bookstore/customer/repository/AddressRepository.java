package com.bookstore.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.customer.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	Optional<Address> findByIdAndRecordStatusCode(Long addressId, String recordStatus);

	List<Address> findByRecordStatusCode(String recordStatus);

    List<Address> findByCustomerIdAndRecordStatusCode(Long id, String status);
}
