package com.rustem.simplecrudapplication.repository;

import com.rustem.simplecrudapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Modifying
    @Query(value = "UPDATE customers SET first_name = COALESCE(CAST(:firstName AS VARCHAR), first_name), " +
            "last_name = COALESCE(CAST(:lastName AS VARCHAR), last_name), " +
            "address =  COALESCE(CAST(:address AS VARCHAR), address), " +
            "budget = COALESCE(CAST(CAST(:budget AS VARCHAR) AS NUMERIC), budget) " +
            "where id = :id", nativeQuery = true)
    Integer update(@Param("id") Long id,
                   @Param("firstName") String firstName,
                   @Param("lastName") String lastName,
                   @Param("address") String address,
                   @Param("budget") BigInteger budget);
}
