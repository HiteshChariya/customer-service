package com.bookstore.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.customer.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{

	List<Country> findByRecordStatusCode(String recordStatus);

	Optional<Country> findByIdAndRecordStatusCode(Long id, String status);

}
