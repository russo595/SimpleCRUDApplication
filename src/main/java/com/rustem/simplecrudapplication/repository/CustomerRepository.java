package com.rustem.simplecrudapplication.repository;

import com.rustem.simplecrudapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
