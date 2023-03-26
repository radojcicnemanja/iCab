package com.example.demo.repository;

import com.example.demo.domain.Chat;
import com.example.demo.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT u FROM Message u WHERE u.chat_id = ?1")
    List<Message> findAllByChat(Integer chatId);
}
