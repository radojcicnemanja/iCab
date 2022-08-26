package com.example.demo.repository;

import com.example.demo.domain.Customer;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM Customer u WHERE u.username = ?1")
    Customer findByUsername(String username);
}
