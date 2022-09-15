package com.example.demo.repository;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Driver;
import com.example.demo.domain.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Integer> {
    @Query("SELECT u FROM RegistrationRequest u WHERE u.username = ?1")
    RegistrationRequest findByUsername(String username);
}
