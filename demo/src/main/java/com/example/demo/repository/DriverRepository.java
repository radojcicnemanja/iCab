package com.example.demo.repository;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepository extends JpaRepository<Driver, Integer>  {
    @Query("SELECT u FROM Driver u WHERE u.username = ?1")
    Driver findByUsername(String username);
}
