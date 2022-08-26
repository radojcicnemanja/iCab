package com.example.demo.repository;

import com.example.demo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT u FROM Customer u WHERE u.verificationCode = ?1")
    Customer findByVerificationCode(String code);
}
