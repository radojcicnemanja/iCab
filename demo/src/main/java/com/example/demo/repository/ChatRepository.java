package com.example.demo.repository;

import com.example.demo.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query("SELECT u FROM Chat u WHERE u.name = ?1")
    Chat findByName(String name);

    @Query("SELECT u FROM Chat u WHERE u.name LIKE %:username%")
    Collection<Chat> findByPartecipant(String username);
}
